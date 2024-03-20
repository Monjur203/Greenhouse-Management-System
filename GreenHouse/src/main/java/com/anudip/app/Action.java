package com.anudip.app;

import java.util.Scanner;

import com.anudip.app.dao.LogDaompl;
import com.anudip.app.dao.MQTTPublisher;
import com.anudip.app.dao.MQTTSubscriberThread;
import com.anudip.app.dao.UserDaompl;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.User;

public class Action {
	public Action(int uid) {
		MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
		Scanner scanner = new Scanner(System.in);
        int choice;
        int duid= uid;
        LogDaompl ld = new LogDaompl();
        Log l= new Log();
        User u = new User();

        boolean exitMenu = false;
	    while (!exitMenu) {
        	System.out.println("Water Pump");
            System.out.println("1. ON");
            System.out.println("2. OFF");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Water Pump On...");
                    MQTTPublisher m = new MQTTPublisher("1");
                    
                   u.setUserID(duid);
                    l.setLogMessage("Water Pump On");
                    l.setLogType(LogType.info);
                    l.setUser(u);
                    ld.registerLog(l);
                    break;
                case 2:
                    System.out.println("Water Pump Off...");
                    MQTTPublisher m1 = new MQTTPublisher("0");
                    u.setUserID(duid);
                    l.setLogMessage("Water Pump Off");
                    l.setLogType(LogType.info);
                    l.setUser(u);
                    ld.registerLog(l);
                    break;
                case 3:
                    System.out.println("Log Out...");
                    //mqttSubscriberThread.stopThread();
                    exitMenu = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
	}

}
}
