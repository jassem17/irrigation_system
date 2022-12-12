package com.mycompany.smart_irrigation.security.Exception;

import com.mycompany.smart_irrigation.entities.ErrorMessage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class BeanValConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> cv = e.getConstraintViolations();
        final List<String> errors = cv.stream().map(c -> c.getPropertyPath() + " " + c.getMessage()).collect(Collectors.toList());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(errors))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
