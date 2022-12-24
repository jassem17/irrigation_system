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
    private String idSensor;

    @Column
    private String idParcel;
    @Column
    private SensorType sensorType;

    @Column
    private double sensorValue;

    public Sensor(){

    }

    public String getIdParcel() {
        return idParcel;
    }

    public void setIdParcel(String idParcel) {
        this.idParcel = idParcel;
    }

    public Sensor(String idSensor, SensorType sensorType, String idParcel,double sensorValue){
        this.idSensor=idSensor;
        this.sensorType=sensorType;
        this.idParcel = idParcel;
        this.sensorValue=sensorValue;
    }

    public double getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }

    public String getIdSensor() {
        return idSensor;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
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
                "idParcel=" + idParcel +
                "idSensor=" + idSensor +
                ", sensorType=" + sensorType +
                '}';
    }

}
