package com.mycompany.smart_irrigation.entities;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class SensorEncoder implements Encoder.Text<Sensor> {

    @Override
    public String encode(Sensor message) throws EncodeException {

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("idSensor", message.getIdSensor())
                .add("idParcel", message.getIdParcel())
                .add("sensorType", message.getSensorType())
                .add("sensorValue", message.getSensorValue())
                .build();
        return jsonObject.toString();

    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("Initializing message encoder");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying encoder...");
    }
}
