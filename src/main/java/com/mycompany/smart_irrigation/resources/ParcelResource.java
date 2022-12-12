package com.mycompany.smart_irrigation.resources;


import com.mycompany.smart_irrigation.entities.Parcel;
import com.mycompany.smart_irrigation.entities.User;
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
@Path("parcel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParcelResource {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private ParcelRepository repository;

    @GET
    public List<Parcel> findAll() {
        return repository.findAll()
                .collect(toList());
    }
    @GET
    @Path("/{id}")
    public Parcel findById(@PathParam("id") String id) {
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }

    @POST
    public void save(Parcel parcel) {
        repository.save(parcel);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Parcel parcel) {
        repository.save(parcel);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") String plants) {
        repository.deleteById(plants);
    }
}
