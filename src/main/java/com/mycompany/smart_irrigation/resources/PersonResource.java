package com.mycompany.smart_irrigation.resources;

import com.mycompany.smart_irrigation.entities.RoleDTO;
import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.repositories.UserRepository;
import com.mycompany.smart_irrigation.security.SecurityService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    @RolesAllowed({"ADMIN","USER"})
    @Path("/single/{username}")
    public User findById(@PathParam("username") String username) {
        return repository.findByUsername(username).orElseThrow(NOT_FOUND);
    }
/*
    @POST
    public void save(User user) {

        repository.save(user);

    }*/

    @Path("/signup")
    @POST
    @PermitAll
    public void save(User user) {

        service.create(user);

    }
/*
    @POST
    @Path("/login/{username}")
    public User login(@PathParam("username") String username, String password){
        return service.login(username,password);
    }
*/
    @PUT
    @Path("/{id}")
    @RolesAllowed("USER")
    public void update(@PathParam("id") String id, User user) {
        repository.save(user);
    }

    @PUT
    @Path("/role/{id}")
    @RolesAllowed("ADMIN")
    public void updateRole(@PathParam("id") String id, RoleDTO role) {
        service.addRole(id,role);
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") String id) {
        service.delete(id);
    }

    @Path("/{username}")
    @PUT
    @RolesAllowed("USER")
    public void updatePassword(@PathParam("username") String username,User user){
        service.updatePassword(username,user);
    }
}
