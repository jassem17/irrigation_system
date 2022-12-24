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
    private String idParcel;

    @Column("field")
    private String idField;

   /* private List<Float> location;
    @Column
    private String plants;
*/

    public Parcel() {
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public Parcel(String idParcel) {
        this.idParcel=idParcel;


    }
/*
    public List<Float> getLocation() {
        return location;
    }

    public String getPlants() {
        return plants;
    }
*/
    public String getIdParcel() {
        return idParcel;
    }

    public void setIdParcel(String idParcel) {
        this.idParcel = idParcel;
    }
/*
    public void setLocation(List<Float> location) {
        this.location = location;
    }

    public void setPlants(String plants) {
        this.plants = plants;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return  idParcel.equals(parcel.idParcel) ;//&& location.equals(parcel.location) && plants.equals(parcel.plants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParcel);// location, plants);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "idParcel=" + idParcel +
               // ", location=" + location +
                //", plants='" + plants + '\'' +
                '}';
    }
}
