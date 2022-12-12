package com.mycompany.smart_irrigation.entities;

import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Entity;

import javax.json.bind.annotation.JsonbVisibility;
import java.io.Serializable;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Admin extends User implements Serializable {
    public Admin(){

    }
}
