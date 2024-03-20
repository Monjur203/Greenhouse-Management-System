package com.anudip.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.anudip.app.dao.MQTTSubscriberThread;



public class Server {
    public static void main(String[] args) throws InterruptedException {
    	
    	//Logger hibernateLogger = Logger.getLogger("org.hibernate");
        //hibernateLogger.setLevel(Level.SEVERE);
    	
    	//MQTTSubscriber su = new MQTTSubscriber();
    	//su.MQTTS();
      MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
        System.out.println("s");
       
    }
    	
    	
    
}
