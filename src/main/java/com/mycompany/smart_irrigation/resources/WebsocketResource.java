package com.mycompany.smart_irrigation.resources;


import com.mycompany.smart_irrigation.entities.Sensor;
import com.mycompany.smart_irrigation.entities.SensorDecoder;
import com.mycompany.smart_irrigation.entities.SensorEncoder;
import com.mycompany.smart_irrigation.mqtt.MqttConnection;
import jakarta.json.JsonObject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/channel",
        encoders = {SensorEncoder.class},
        decoders = {SensorDecoder.class}
        )
public class WebsocketResource {

    MqttClient client; // Persistence

    private static Session session;
    private static Set<Session> sessions = new HashSet<>();

    {
        try {
            System.out.println("MQTT started");

            //CLIENT CONNECTION OPTIONS
            MqttClient client = new MqttClient(
                    "wss://mqtt.smart-irrigation.me:8083",
                    MqttClient.generateClientId(),
                    new MemoryPersistence());

            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setUserName("jassem");
            mqttConnectOptions.setPassword("jassem".toCharArray());
            mqttConnectOptions.setSocketFactory(SSLSocketFactory.getDefault());

            client.connect(mqttConnectOptions);


        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void open(Session session) {
        MqttConnection connect = new MqttConnection();
        connect.start();

        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Sensor sensor, Session session) {
        System.out.println("Stock information received: " + sensor + " from " + session.getId());
        try {
            session.getBasicRemote().sendObject(sensor);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }

    }

    public static void broadcastMessage(Sensor sensor) {
        for (Session session : sessions) {
            try {
                System.out.println("DONEEEEEE!!!!!");
                session.getBasicRemote().sendObject(sensor);

            } catch (IOException | EncodeException e) {
                System.out.println("BYYYYYYYYYE!!!!!");
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("WebSocket error for " + session.getId() + " " + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("WebSocket closed for " + session.getId()
                + " with reason " + closeReason.getCloseCode());
        sessions.remove(session);
    }
}
