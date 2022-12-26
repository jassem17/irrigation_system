package com.mycompany.smart_irrigation.entities;

import java.io.StringReader;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class SensorDecoder implements Decoder.Text<Sensor>{

    @Override
    public Sensor decode(String jsonMessage) throws DecodeException {

        JsonObject jsonObject = Json
                .createReader(new StringReader(jsonMessage)).readObject();
        Sensor message = new Sensor();
        message.setIdSensor(jsonObject.getString("idSensor"));
        message.setIdParcel(jsonObject.getString("idParcel"));
        message.setSensorType(SensorType.valueOf(jsonObject.getString("sensorType")));
        message.setSensorValue(jsonObject.getInt("value"));
        return message;

    }
    @Override
    public boolean willDecode(String jsonMessage) {
        try {
            // Check if incoming message is valid JSON
            Json.createReader(new StringReader(jsonMessage)).readObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig ec) {
        System.out.println("Initializing message decoder");
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed message decoder");
    }
}
