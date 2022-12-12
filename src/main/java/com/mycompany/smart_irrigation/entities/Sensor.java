package com.mycompany.smart_irrigation.entities;


import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbVisibility;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Sensor extends Thing implements Serializable {

    @Id
    private Long idSensor;

    @Column
    private SensorType sensorType;

    public Sensor(){

    }
    public Sensor(Long idSensor, SensorType sensorType){
        this.idSensor=idSensor;
        this.sensorType=sensorType;
    }

    public Long getIdSensor() {
        return idSensor;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(idSensor, sensor.idSensor) && sensorType == sensor.sensorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSensor, sensorType);
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "idSensor=" + idSensor +
                ", sensorType=" + sensorType +
                '}';
    }

}
