package com.anudip.app;

import java.util.Scanner;

import com.anudip.app.dao.GreenhouseConfigurationDaompl;
import com.anudip.app.dao.LogDaompl;
import com.anudip.app.dao.MQTTSubscriberThread;
import com.anudip.app.dao.UserDaompl;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.User;
import com.anudip.app.entities.UserRole;

public class Update {
	
	public Update(int uid) {
		MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;
	    while (!exitMenu) {
		System.out.println("1. Update User");
        System.out.println("2. Update Greenhouse Configuration");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int duid = uid;
        int choice;
        LogDaompl ld = new LogDaompl();
        Log l= new Log();
        User u = new User();
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
            	
            	u.setUserID(duid);
                l.setLogMessage("Updating User");
                l.setLogType(LogType.warning);
                l.setUser(u);
                System.out.println("Updating User...");
                UserDaompl userDao = new UserDaompl();
                System.out.print("Enter User Id: ");
                int userid = scanner.nextInt() ;
                scanner.nextLine();
                System.out.println("1: Admin" );
                System.out.println("2:User" );
                int role =scanner.nextInt();
                switch (role) {
                case 1:
                	
                    userDao.updateUserRole(userid, UserRole.admin);
                	break;
                case 2:
                	
                    userDao.updateUserRole(userid, UserRole.user);
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
                ld.registerLog(l);
                
                break;
            case 2:
            	u.setUserID(duid);
                l.setLogMessage("Updating Configuration");
                l.setLogType(LogType.warning);
                l.setUser(u);
                System.out.println("Updating Greenhouse Configuration...");
                System.out.print("Enter ConfigurationID: ");
                int ConfigurationID = scanner.nextInt() ;
                scanner.nextLine();
                System.out.print("Enter Value: ");
                String Value = scanner.nextLine();
                
                GreenhouseConfigurationDaompl gc = new GreenhouseConfigurationDaompl();
                gc.updateGreenhouseConfiguration(ConfigurationID, Value);
                ld.registerLog(l);
                break;
            case 3:
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
