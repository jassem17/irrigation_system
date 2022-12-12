package com.mycompany.smart_irrigation.security.Exception;

import jakarta.ws.rs.core.Response;

public class UserForbiddenException extends RuntimeException {

    public Response toResponse(UserForbiddenException exception) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
