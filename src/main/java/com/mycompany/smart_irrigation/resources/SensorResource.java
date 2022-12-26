package com.mycompany.smart_irrigation.resources;

import com.mycompany.smart_irrigation.entities.Parcel;
import com.mycompany.smart_irrigation.entities.Sensor;
import com.mycompany.smart_irrigation.repositories.SensorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
@Path("sensor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private SensorRepository repository;
    @GET
    public List<Sensor> findAll() {
        return repository.findAll()
                .collect(toList());
    }
    @GET
    @Path("/{id}")
    public Sensor findById(@PathParam("id") String id) {
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }



    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Sensor sensor) {
        repository.save(sensor);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") String id) {
        repository.deleteById(id);
    }
}

