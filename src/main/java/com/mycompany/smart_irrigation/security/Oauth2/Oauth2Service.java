package com.mycompany.smart_irrigation.security.Oauth2;

import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.security.Exception.UserNotAuthorizedException;
import com.mycompany.smart_irrigation.security.SecurityService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

@ApplicationScoped
public class Oauth2Service {
    static final int EXPIRE_IN = 3600;

    static final Duration EXPIRES = Duration.ofSeconds(EXPIRE_IN);

    @Inject
    private SecurityService securityService;

    @Inject
    private UserTokenRepository repository;

    @Inject
    private Validator validator;

    public Oauth2Response token(Oauth2Request request) {

        final Set<ConstraintViolation<Oauth2Request>> violations = validator.validate(request, Oauth2Request
                .GenerateToken.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final User user = securityService.findBy(request.getUsername(), request.getPassword());
        final UserToken userToken = repository.findByUsername(request.getUsername()).orElse(new UserToken(user.getUsername()));

        final Token token = Token.generate();

        final String jwt = UserJWT.createToken(user, token, EXPIRES);

        AccessToken accessToken = new AccessToken(jwt, token.get(), EXPIRES);
        RefreshToken refreshToken = new RefreshToken(Token.generate(), accessToken);
        userToken.add(refreshToken);
        repository.save(userToken);
        return Oauth2Response.of(accessToken, refreshToken, EXPIRE_IN);
    }

    public Oauth2Response refreshToken(Oauth2Request request) {
        final Set<ConstraintViolation<Oauth2Request>> violations = validator.validate(request, Oauth2Request
                .RefreshToken.class);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        final UserToken userToken = repository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new UserNotAuthorizedException("Invalid Token"));
        final User user = securityService.findBy(userToken.getUsername(), request.getPassword());
        final Token token = Token.generate();
        final String jwt = UserJWT.createToken(user, token, EXPIRES);
        AccessToken accessToken = new AccessToken(token.get(), jwt, EXPIRES);
        RefreshToken refreshToken = userToken.update(accessToken, request.getRefreshToken(), repository);

        return Oauth2Response.of(accessToken, refreshToken, EXPIRE_IN);
    }

}
