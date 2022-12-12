package com.mycompany.smart_irrigation.security.Oauth2;

import com.mycompany.smart_irrigation.entities.RemoveUser;
import com.mycompany.smart_irrigation.entities.RemoveToken;

import com.mycompany.smart_irrigation.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;


@ApplicationScoped
public class Oauth2Observes {
    @Inject
    private UserTokenRepository repository;

    public void observe(@Observes RemoveUser removeUser) {
        final User user = removeUser.getUser();
        repository.deleteById(user.getUsername());
    }

    public void observe(@Observes RemoveToken removeToken) {
        final User user = removeToken.getUser();
        final String token = removeToken.getToken();
        UserToken userToken = repository.findById(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User was not found: " + user.getUsername()));
        userToken.remove(token);
        repository.save(userToken);
    }

}
