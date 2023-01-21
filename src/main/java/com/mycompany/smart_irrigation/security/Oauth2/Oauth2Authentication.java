package com.mycompany.smart_irrigation.security.Oauth2;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Priorities;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Oauth2Authentication {
   /* private static final Pattern CHALLENGE_PATTERN = Pattern.compile("^Bearer *([^ ]+) *$", Pattern.CASE_INSENSITIVE);
    @Inject
    private UserTokenRepository repository;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
                                                HttpMessageContext httpMessageContext) {

        System.out.println(request.getRequestURI());
        if(!(request.getRequestURI().contains("oauth2") || request.getRequestURI().contains("signup") || request.getRequestURI().contains("channel"))    ) {
            final String authorization = request.getHeader("Authorization");
            System.out.println(authorization);
            Matcher matcher = CHALLENGE_PATTERN.matcher(Optional.ofNullable(authorization).orElse(""));
            if (!matcher.matches()) {
                System.out.println(matcher);
                return httpMessageContext.responseUnauthorized();
            }
            final String token = matcher.group(1);
            final Optional<AccessToken> optional = repository.findByAccessToken(token)
                    .flatMap(u -> u.findAccessToken(token));


            if (!optional.isPresent()) {
                return httpMessageContext.responseUnauthorized();
            }

            final AccessToken accessToken = optional.get();
            final Optional<UserJWT> optionalUserJWT = UserJWT.parse(accessToken.getToken(), accessToken.getJwtSecret());

            if (optionalUserJWT.isPresent()) {
                final UserJWT userJWT = optionalUserJWT.get();
                return httpMessageContext.notifyContainerAboutLogin(userJWT.getUser(), userJWT.getRoles());
            } else {
                return httpMessageContext.responseUnauthorized();
            }

        }else {
            return  httpMessageContext.doNothing() ;
        }
    }*/
}


