package com.mycompany.smart_irrigation.mqtt;


import com.mycompany.smart_irrigation.entities.Field;
import com.mycompany.smart_irrigation.entities.Parcel;
import com.mycompany.smart_irrigation.entities.Sensor;
import com.mycompany.smart_irrigation.entities.SensorType;
import com.mycompany.smart_irrigation.repositories.FieldRepository;
import com.mycompany.smart_irrigation.repositories.ParcelRepository;
import com.mycompany.smart_irrigation.repositories.SensorRepository;
import com.mycompany.smart_irrigation.resources.WebsocketResource;
import jakarta.inject.Inject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;


import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;


@Singleton
@Startup
public class MqttConnection {

    @Inject
    private FieldRepository fieldRepository;

    @Inject
    private ParcelRepository parcelRepository;

    @Inject
    private SensorRepository sensorRepository;

    public void sendmsg(MqttClient client,String msg,String topic) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        client.publish(topic,message);
    }

    @PostConstruct
    public void start() {
        try {
            System.out.println("MQTT start");

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


            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("client lost connection " + cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    // System.out.println(new String(message.getPayload()));
                    System.out.println("We have message from  " + topic);
                    if (topic.equals("Field/Parcel/Sensor")) {
                        try {
                            System.out.println("Sensor :"+ message+" is successfully added");
                            JSONObject obj = new JSONObject(new String(message.getPayload()));
                            Sensor sensor =new Sensor();
                            sensor.setIdParcel("1");
                            sensor.setIdSensor(obj.getString("idSensor"));
                            sensor.setSensorType(SensorType.valueOf(obj.getString("sensorType")));
                            sensor.setSensorValue(obj.getInt("sensorValue"));
                            WebsocketResource.broadcastMessage(sensor);
                            //sensorRepository.save(sensor);

                        }
                        catch (Exception e ) {
                        }
                    }
                }


                @Override
                // Called when an outgoing publish is complete
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("delivery complete " + token);
                }
            });

            client.subscribe("Field/Parcel/Sensor", 1);
        } catch (MqttException e) {

        }
    }
}

