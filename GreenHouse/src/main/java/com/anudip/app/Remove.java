package com.anudip.app;

import java.util.Scanner;

import com.anudip.app.dao.DeviceDaompl;
import com.anudip.app.dao.GreenhouseConfigurationDaompl;
import com.anudip.app.dao.LogDaompl;
import com.anudip.app.dao.MQTTSubscriberThread;
import com.anudip.app.dao.UserDaompl;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.User;

public class Remove {
	
	public Remove(int uid) {
		MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;
	    while (!exitMenu) {
		System.out.println("1. Remove User");
		System.out.println("2. Remove Device");
		System.out.println("3. Remove Configuration");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int duserid = uid;
        int choice;
        LogDaompl ld = new LogDaompl();
        Log l= new Log();
        User u = new User();
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Removing User...");
                
                u.setUserID(duserid);
                
                l.setLogType(LogType.warning);
                l.setUser(u);
                
                UserDaompl userDao = new UserDaompl();
                System.out.print("Enter User Id: ");
                int userid = scanner.nextInt() ;
                userDao.removeUserById(userid);
                l.setLogMessage("Removing User :"+userid);
                ld.registerLog(l);
                break;
            case 2:
            	u.setUserID(duserid);
                
                l.setLogType(LogType.warning);
                l.setUser(u);
            	DeviceDaompl ddao = new DeviceDaompl();
                System.out.print("Enter User Id: ");
                int deviceid = scanner.nextInt() ;
                ddao.removeDeviceById(deviceid);
                l.setLogMessage("Removing Device :"+deviceid);
                ld.registerLog(l);
            	break;
            case 3:
            	u.setUserID(duserid);
                
                l.setLogType(LogType.warning);
                l.setUser(u);
            	GreenhouseConfigurationDaompl gcdao = new GreenhouseConfigurationDaompl();
                System.out.print("Enter User ConfigurationId: ");
                int Configurationid = scanner.nextInt() ;
                gcdao.removeGreenhouseConfigurationById(Configurationid);
                l.setLogMessage("Removing Configuration :"+Configurationid);
                ld.registerLog(l);
            	break;
            case 4:
                System.out.println("Exiting...");
                //mqttSubscriberThread.stopThread();
                exitMenu = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
	}
	}

}
