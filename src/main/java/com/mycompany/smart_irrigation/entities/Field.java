package com.mycompany.smart_irrigation.entities;


import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Field implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idField;
    @Column
    private List<Float> location;
    @Column
    protected Parcel[] parcels;

    public Field(){
    }

    public Field(Long idField, List<Float> location,Parcel[] parcels) {
        this.idField=idField;
        this.location=location;
        this.parcels=parcels;
    }

    public List<Float> getLocation() {
        return location;
    }

    public Parcel[] getParcels() {
        return parcels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return idField.equals(field.idField) && location.equals(field.location) && Arrays.equals(parcels, field.parcels);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idField, location);
        result = 31 * result + Arrays.hashCode(parcels);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "idField=" + idField +
                ", location=" + location +
                ", parcels=" + Arrays.toString(parcels) +
                '}';
    }
}
