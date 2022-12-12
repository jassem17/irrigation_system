package com.mycompany.smart_irrigation.entities;

import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbVisibility;
import java.io.Serializable;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public abstract class Thing implements Serializable {

    @Id
    private Long idThing;

    @Column
    private String name;

    @Column
    private String topic;



    public Long getIdThing() {
        return idThing;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public Thing(){

    }

    public Thing(Long idThing, String name, String topic){
        this.idThing=idThing;
        this.topic=topic;
        this.name=name;
    }

    public static ThingBuilder builder() {
        return new Thing.ThingBuilder();
    }

    public static class ThingBuilder {

        private Long idThing;

        private String name;
        private String topic;


        private ThingBuilder() {
        }
        public ThingBuilder( Long idThing, String name, String topic) {
            this.idThing=idThing;
            this.topic=topic;
            this.name=name;
        }

        public ThingBuilder withThingId(Long idThing) {
            this.idThing = idThing;
            return this;
        }
        public ThingBuilder withName(String name) {
            this.name = name;
            return this;
        }


        public ThingBuilder withTopic(String topic) {
            this.topic = topic;
            return this;
        }



    }
}
