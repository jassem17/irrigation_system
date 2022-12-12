package com.mycompany.smart_irrigation.entities;

import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Parcel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idParcel;

    private List<Float> location;
    @Column
    private String plants;
    @Column
    private float moistureLevel;
    @Column
    private float temperature;

    public Parcel() {
    }

    public Parcel(Long idParcel, List<Float> location,String plants, float moistureLevel , float temperature) {
        this.idParcel=idParcel;
        this.location=location;
        this.plants=plants;
        this.moistureLevel=moistureLevel;
        this.temperature=temperature;
    }

    public List<Float> getLocation() {
        return location;
    }

    public String getPlants() {
        return plants;
    }

    public float getMoistureLevel() {
        return moistureLevel;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Float.compare(parcel.moistureLevel, moistureLevel) == 0 && Float.compare(parcel.temperature, temperature) == 0 && idParcel.equals(parcel.idParcel) && location.equals(parcel.location) && plants.equals(parcel.plants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParcel, location, plants, moistureLevel, temperature);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "idParcel=" + idParcel +
                ", location=" + location +
                ", plants='" + plants + '\'' +
                ", moistureLevel=" + moistureLevel +
                ", temperature=" + temperature +
                '}';
    }
}
