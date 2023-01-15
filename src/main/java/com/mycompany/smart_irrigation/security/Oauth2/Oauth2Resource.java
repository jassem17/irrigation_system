package com.mycompany.smart_irrigation.security.Oauth2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.validation.Valid;
import java.util.function.Supplier;

@ApplicationScoped
@Path("oauth2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Oauth2Resource {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private Oauth2Service service;

    @POST
    @Path("token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Oauth2Response token(@Valid Oauth2Request request) {
        switch (request.getGrandType()) {
            case PASSWORD:
                return service.token(request);
            case REFRESH_TOKEN:
                return service.refreshToken(request);
            default:
                throw new UnsupportedOperationException("There is not support to another type");
        }
    }
}
