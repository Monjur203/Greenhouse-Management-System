package com.anudip.app;

import java.util.List;
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
import java.util.logging.*;

public class MainFile {
	
	
	
	public static void main(String[] args) {
    	Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.SEVERE);
        
        
        Scanner scanner = new Scanner(System.in);

        int choice;
        int loggedInUserID ;

        while (true) {
        	MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
            mqttSubscriberThread.start();
        	System.out.println("╔═╗┬─┐┌─┐┌─┐┌┐┌┬ ┬┌─┐┬ ┬┌─┐┌─┐  ╔╦╗┌─┐┌┐┌┌─┐┌─┐┌─┐┌┬┐┌─┐┌┐┌┌┬┐  ╔═╗┬ ┬┌─┐┌┬┐┌─┐┌┬┐");
            System.out.println("║ ╦├┬┘├┤ ├┤ │││├─┤│ ││ │└─┐├┤   ║║║├─┤│││├─┤│ ┬├┤ │││├┤ │││ │   ╚═╗└┬┘└─┐ │ ├┤ │││");
            System.out.println("╚═╝┴└─└─┘└─┘┘└┘┴ ┴└─┘└─┘└─┘└─┘  ╩ ╩┴ ┴┘└┘┴ ┴└─┘└─┘┴ ┴└─┘┘└┘ ┴   ╚═╝ ┴ └─┘ ┴ └─┘┴ ┴");
        
            
        	
            //System.out.println("Welcome to the Green House management system!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                	loggedInUserID = login();
                   
                    break;
                case 2:
                    
                        register();
                    
                     
                    break;
                case 3:
                	
                    System.out.println("Exiting...");
                    
                    System.exit(0);
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }

    public static int  login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login Page");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        UserDaompl ud =new UserDaompl();
        User user = ud.authenticateUser(username, password);
        if (user != null) {
            System.out.println("Login successful!");
            
            // Check if the user is an administrator
            boolean isAdmin = user.getRole() == UserRole.admin;
            System.out.println("Is Administrator: " + isAdmin);
            if (isAdmin) {
            loggedInMenu(user.getUserID() );
            return user.getUserID();
            } else {
            	loggedInMenuForGuest(user.getUserID());
            	return user.getUserID();
            }
            
            
        } else {
            System.out.println("Invalid username or password. Please try again.");
            
            return 0;
        }

        
        
    }

    public static void loggedInMenu(int loggedInUserID) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Logged in successfully!");
            System.out.println("1. View");
            System.out.println("2. Create");
            System.out.println("3. Update");
            System.out.println("4. Remove");
            System.out.println("5. Action");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Viewing...");
                    View v = new View(loggedInUserID);
                    
                    break;
                case 2:
                    System.out.println("Creating...");
				
				Create c = new Create(loggedInUserID);
                    break;
                case 3:
                    System.out.println("Updating...");
                    Update u = new Update(loggedInUserID);
                    break;
                case 4:
                    System.out.println("Removing...");
                    Remove r = new Remove(loggedInUserID);
                    break;
                case 5:
                    System.out.println("Removing...");
                    Action a = new Action(loggedInUserID);
                    
                    break;
                case 6:
                    System.out.println("Logging out...");
                    
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
    
    public static void loggedInMenuForGuest(int loggedInUserID) {
    	boolean exitMenu = false;

        while (!exitMenu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Device");
        
        System.out.println("2. View Greenhouse Configuration");
        System.out.println("3. View Sensor Data");
        
        System.out.println("4. Exit");
        System.out.println("Enter your choice:");
        int choice;

        int duid = loggedInUserID;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Viewing Device...");
                
                DeviceDaompl ddao = new DeviceDaompl();
                List<Device> devices = ddao.getAllDeviceDetails();
                for(Device device:devices) {
                	System.out.println(device.toString());
                }
                
                
                
               
                
                break;
            case 2:
                System.out.println("Viewing Greenhouse Configuration...");
                // Implement logic for viewing greenhouse configuration
                GreenhouseConfigurationDaompl gcdao = new GreenhouseConfigurationDaompl();
                List<GreenhouseConfiguration> configuration = gcdao.getAllGreenhouseConfigurationDetails();
                for(GreenhouseConfiguration device:configuration) {
                	System.out.println(device.toString());
                }
                break;
            case 3:
                System.out.println("Viewing Sensor Data...");
                // Implement logic for viewing sensor data
                View.Sensor_Menu();
                break;
            
            case 4:
                System.out.println("Exiting...");
                
                exitMenu = true; // Set flag to exit the menu loop
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        }
    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        LogDaompl ld = new LogDaompl();
        Log l= new Log();

        System.out.println("Register Page");
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
        u.setRole(UserRole.user);
        
        UserDaompl ud = new UserDaompl();
        ud.registerUserDetails(u);
        l.setLogMessage("");
        l.setLogType(LogType.warning);
        ld.registerLog(l);
     /*   Have to implementing the registration page.....*/
        System.out.println("Registration successful!");
        
    }
    
//    static class MQTTSubscriberThread extends Thread {
//        @Override
//        public void run() {
//            MQTTSubscriber mqttSubscriber = new MQTTSubscriber();
//            mqttSubscriber.MQTTS();
//        }
//
//    }
    
}
