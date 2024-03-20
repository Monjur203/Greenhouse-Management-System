package com.anudip.app.dao;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import org.json.JSONObject;

import com.anudip.app.Initialize;
import com.anudip.app.MainFile;
import com.anudip.app.entities.Device;
import com.anudip.app.entities.SensorData;
import java.util.logging.*;

public class MQTTSubscriberThread extends Thread   {
	
	@Override
    public void run () {
        String broker = "tcp://broker.hivemq.com:1883"; 
        String clientId = "JavaMy_p"; 
        String topic = "Monjur_SensorData"; 
        //System.out.println(run1);
       
        try (MqttClient mqttClient = new MqttClient(broker, clientId, new MemoryPersistence())) {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            //System.out.println("Connecting to MQTT broker: " + broker);
            mqttClient.connect(connOpts);
            //System.out.println("Connected");
            
            mqttClient.subscribe(topic, (topic1, message) -> {
                String payload = new String(message.getPayload());
                //System.out.println("Received message: " + payload);
                
            

            //System.out.println("Subscribed to topic: " + topic);
            
                // Parse the JSON payload
                JSONObject jsonPayload = new JSONObject(payload);
                
                // Extract values from the JSON object
                int deviceId = jsonPayload.getInt("device_id");
                float temperatureValue = (float) jsonPayload.getDouble("Temperature_value");
                float humidityValue = (float) jsonPayload.getDouble("Humidity_value");
                float soilHumidityValue = (float) jsonPayload.getDouble("Soil_Humidity_value");
                //System.out.println(Initialize.run1);
                
                Device device = new Device();
                device.setDeviceID(deviceId);
                
                SensorData sd = new SensorData();
                
                sd.setDeviceId(device);
                sd.setTemperature(temperatureValue);
                sd.setAirHumidity(humidityValue);
                sd.setSoilHumidity(soilHumidityValue);
                
                SensorDaompl se = new SensorDaompl();
                se.registerUserDetails(sd);
                
            
            });
            
            while (true) {
                Thread.sleep(1000); 
            }

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

    
    


