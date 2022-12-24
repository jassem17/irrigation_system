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
    private String idField;
    @Column
    private String name;
    /*@Column
    protected Parcel[] parcels;*/

    public Field(){
    }

    public Field(String idField, String name) {
        this.idField=idField;
        this.name=name;
    }

    public String getIdField() {
        return idField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return idField.equals(field.idField) && name.equals(field.name) ;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idField, name);
        result = 31 * result; //+ Arrays.hashCode(parcels);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "idField=" + idField +
                ", name=" + name +
                //", parcels=" + Arrays.toString(parcels) +
                '}';
    }
}
