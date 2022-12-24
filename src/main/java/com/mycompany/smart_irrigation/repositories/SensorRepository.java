package com.mycompany.smart_irrigation.repositories;

import com.mycompany.smart_irrigation.entities.Sensor;
import jakarta.nosql.mapping.Repository;

import java.util.stream.Stream;

public interface SensorRepository extends Repository<Sensor, String> {
    Stream<Sensor> findAll();

    Sensor findByIdSensor(String idSensor);



}
