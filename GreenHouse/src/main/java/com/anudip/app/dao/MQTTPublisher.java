package com.anudip.app.dao;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTPublisher {
	
	public MQTTPublisher(String a) {
		
		String broker = "tcp://broker.hivemq.com:1883"; 
        String clientId = "JavaMy_p006";
        String topic = "Monjur_CommandRequest"; 
        String message ="{\"device_id\":\"1\", \"type\":\"Light\", \"value\":\"" + a + "\"}";

        try (MqttClient mqttClient = new MqttClient(broker, clientId, new MemoryPersistence())) {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to MQTT broker: " + broker);
            mqttClient.connect(connOpts);
            System.out.println("Connected");

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0); 

            mqttClient.publish(topic, mqttMessage);
            System.out.println("Message published successfully");

            mqttClient.disconnect();
            System.out.println("Disconnected");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    
	}

}
