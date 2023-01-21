package com.mycompany.smart_irrigation.security;

import java.io.IOException;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {


    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext cres) throws IOException {
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Request-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Accept", "*");
        cres.getHeaders().add("status_header", "200");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE ,OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Request-Method", "GET, POST, PUT, DELETE ,OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
        cres.getHeaders().add("Origin", "*");


    }

}
