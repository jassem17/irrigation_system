package com.mycompany.smart_irrigation;

import jakarta.enterprise.context.ApplicationScoped;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Collections;


@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {
    @Override
    public int priority() {

        return 10;

    }

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {

            UsernamePasswordCredential user = UsernamePasswordCredential
                    .class.cast(credential);

            switch (user.getCaller()) {
                case "admin":
                    return new CredentialValidationResult("admin", Collections.singleton("ADMIN"));
                case "user":
                    return new CredentialValidationResult("admin", Collections.singleton("USER"));
                default:
                    return INVALID_RESULT;
            }
        }

        return INVALID_RESULT;

    }

}
