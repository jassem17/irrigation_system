package com.mycompany.smart_irrigation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

/**
 * Configures JAX-RS for the application.
 * @author Adam M. Gamboa G
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {
    
}
