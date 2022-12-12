package com.mycompany.smart_irrigation;

import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.Optional;

@ApplicationScoped
public class MongoDBIdentity implements IdentityStore {

    @Inject
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof UsernamePasswordCredential) {

            UsernamePasswordCredential userCredential = UsernamePasswordCredential
                    .class.cast(credential);

            final Password userPassword = userCredential.getPassword();
            final Optional<User> userOptional = repository.findById(userCredential.getCaller());
            if (userOptional.isPresent()) {

                final User user = userOptional.get();
                if (passwordHash.verify(userPassword.getValue(), user.getPassword())) {
                    return new CredentialValidationResult(user.getUsername(), user.getRoles());
                }
            }

        }

        return INVALID_RESULT;

    }
}
