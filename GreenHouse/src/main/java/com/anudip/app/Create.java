package com.anudip.app;

import java.util.Scanner;

import com.anudip.app.dao.DeviceDaompl;
import com.anudip.app.dao.GreenhouseConfigurationDaompl;
import com.anudip.app.dao.LogDaompl;
import com.anudip.app.dao.MQTTSubscriberThread;
import com.anudip.app.dao.UserDaompl;
import com.anudip.app.entities.Device;
import com.anudip.app.entities.GreenhouseConfiguration;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.User;
import com.anudip.app.entities.UserRole;

public class Create {
	
	public Create(Integer loggedInUserID) {
		MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
        boolean exitMenu = false;
	    while (!exitMenu) {
        Scanner scanner = new Scanner(System.in);
		System.out.println("1. Create User");
        System.out.println("2. Create Device");
        System.out.println("3. Create Greenhouse Configuration");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    

        LogDaompl ld = new LogDaompl();
        Log l= new Log();
        User u1 = new User();
    	int choice;
    	choice = scanner.nextInt();
    	scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Creating User...");
                u1.setUserID(loggedInUserID);
                l.setLogMessage("Creating User");
                l.setLogType(LogType.info);
                l.setUser(u1);
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                User u = new User();
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);
                System.out.println("1: Admin" );
                System.out.println("2:User" );
                int role =scanner.nextInt();
                scanner.nextLine();
                switch (role) {
                case 1:
                	u.setRole(UserRole.admin);
                	break;
                case 2:
                	u.setRole(UserRole.user);
                	break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
                
                
                UserDaompl ud = new UserDaompl();
                ud.registerUserDetails(u);
                ld.registerLog(l);
                break;
            case 2:
                System.out.println("Creating Device...");
                // Implement logic for creating device
                System.out.println("Enter Device Location");
                String L = scanner.nextLine();
                Device d = new Device();
                d.setLocation(L);
                u1.setUserID(loggedInUserID);
                l.setLogMessage("Creating Device");
                l.setLogType(LogType.info);
                l.setUser(u1);
                DeviceDaompl ddao = new DeviceDaompl();
                ddao.registerDevice(d);
                ld.registerLog(l);
                break;
            case 3:
                System.out.println("Creating Greenhouse Configuration...");
                
                System.out.print("Enter ConfigurationName: ");
                String ConfigurationName = scanner.nextLine();
                System.out.print("Enter Value: ");
                String Value = scanner.nextLine();
                System.out.print("Enter Description: ");
                String Description = scanner.nextLine();
                System.out.print("Enter DeviceId: ");
                int deviceId = scanner.nextInt();
                scanner.nextLine();
                u1.setUserID(loggedInUserID);
                l.setLogMessage("Creating Configuration");
                l.setLogType(LogType.info);
                l.setUser(u1);
                GreenhouseConfigurationDaompl gcdao = new GreenhouseConfigurationDaompl();
                GreenhouseConfiguration gc = new GreenhouseConfiguration();
                Device device = new Device();
                device.setDeviceID(deviceId);
                User user = new User();
                user.setUserID(loggedInUserID);
                gc.setConfigurationName(ConfigurationName);
                gc.setDevice(device);
                gc.setValue(Value);
                gc.setDescription(Description);
                gc.setUser(user);
                gcdao.registerGreenhouseConfigurationDetails(gc);
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
