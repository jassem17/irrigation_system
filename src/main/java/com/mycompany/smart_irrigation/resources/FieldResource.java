package com.mycompany.smart_irrigation.resources;

import com.mycompany.smart_irrigation.entities.Field;
import com.mycompany.smart_irrigation.entities.Parcel;
import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.repositories.FieldRepository;
import com.mycompany.smart_irrigation.repositories.ParcelRepository;
import com.mycompany.smart_irrigation.repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
@Path("field")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FieldResource {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private FieldRepository repository;

    @Inject
    private ParcelRepository parcelRepository;

    @GET
    public List<Field> findAll() {
        return repository.findAll()
                .collect(toList());
    }

    @GET
    @Path("/{id}")
    public Field findById(@PathParam("id") String id) {
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }

    @GET
    @Path("/parcels/{id}")
    public List<Parcel> findByIdField(@PathParam("id") String id) {
        return parcelRepository.findByIdField(id);
    }

    @POST
    public void save(Field field) {
        repository.save(field);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Field field) {
        repository.save(field);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") String name) {
        repository.deleteById(name);
    }
}
