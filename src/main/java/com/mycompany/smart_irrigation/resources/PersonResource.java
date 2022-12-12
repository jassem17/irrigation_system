package com.mycompany.smart_irrigation.resources;

import com.mycompany.smart_irrigation.entities.RoleDTO;
import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.repositories.UserRepository;
import com.mycompany.smart_irrigation.security.SecurityService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.util.List;
import java.util.function.Supplier;

@ApplicationScoped
@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private UserRepository repository;

    @Inject
    private SecurityService service;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    /*
    @GET
    public List<User> findAll() {
        return repository.findAll()
                .collect(toList());
    }*/

    @Path("/all")
    @GET
    @RolesAllowed("ADMIN")
    public List<User> findAll() {
        return service.getUsers();
    }

    @GET
    @Path("/single/{id}")
    public User findById(@PathParam("id") String id) {
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }
/*
    @POST
    public void save(User user) {

        repository.save(user);

    }*/

    @POST
    public void save(User user) {

        service.create(user);

    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, User user) {
        repository.save(user);
    }

    @PUT
    @Path("/role/{id}")
    public void updateRole(@PathParam("id") String id, RoleDTO role) {
        service.addRole(id,role);
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") String id) {
        service.delete(id);
    }

    @Path("/{id}")
    @PUT
    public void updatePassword(@PathParam("id") String id, User user){
        service.updatePassword(id,user);
    }
}
