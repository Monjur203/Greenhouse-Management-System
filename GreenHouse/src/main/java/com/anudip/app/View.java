package com.anudip.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.anudip.app.dao.DeviceDaompl;
import com.anudip.app.dao.GreenhouseConfigurationDaompl;
import com.anudip.app.dao.LogDaompl;
import com.anudip.app.dao.MQTTSubscriberThread;
import com.anudip.app.dao.SensorDaompl;
import com.anudip.app.dao.UserDaompl;
import com.anudip.app.entities.Device;
import com.anudip.app.entities.GreenhouseConfiguration;
import com.anudip.app.entities.Log;
import com.anudip.app.entities.LogType;
import com.anudip.app.entities.SensorData;
import com.anudip.app.entities.User;

public class View {
	
	public View(int uid) {
		

		MQTTSubscriberThread mqttSubscriberThread = new MQTTSubscriberThread();
        mqttSubscriberThread.start();
        boolean exitMenu = false;
	    while (!exitMenu) {
        Scanner scanner = new Scanner(System.in);
		 	System.out.println("1. View Device");
	        System.out.println("2. View User");
	        System.out.println("3. View Greenhouse Configuration");
	        System.out.println("4. View Sensor Data");
	        System.out.println("5. View Log");
	        System.out.println("6. Exit");
	        System.out.println("Enter your choice:");
	        int choice;

	        int duid = uid;
	        choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                System.out.println("Viewing Device...");
	                
	                DeviceDaompl ddao = new DeviceDaompl();
	                List<Device> devices = ddao.getAllDeviceDetails();
	                for(Device device:devices) {
	                	System.out.println(device.toString());
	                }
	                
	                
	                
	                // Exiting the program
	                System.out.println("Exiting...");
	                break;
	            case 2:
	                System.out.println("Viewing User...");
	                // Implement logic for viewing user
	                UserDaompl udao = new UserDaompl();
	                List<User> users = udao.getAllUserDetails();
	                for(User user:users) {
	                	System.out.println(user.toString());
	                }
	                break;
	            case 3:
	                System.out.println("Viewing Greenhouse Configuration...");
	                // Implement logic for viewing greenhouse configuration
	                GreenhouseConfigurationDaompl gcdao = new GreenhouseConfigurationDaompl();
	                List<GreenhouseConfiguration> configuration = gcdao.getAllGreenhouseConfigurationDetails();
	                for(GreenhouseConfiguration device:configuration) {
	                	System.out.println(device.toString());
	                }
	                break;
	            case 4:
	                System.out.println("Viewing Sensor Data...");
	                // Implement logic for viewing sensor data
	                Sensor_Menu();
	                break;
	            case 5:
	                System.out.println("Viewing Log...");
	                // Implement logic for viewing log
	                Log_Menu();
	                break;
	            case 6:
	                System.out.println("Exiting...");
	                
	                exitMenu = true; // Set flag to exit the menu loop
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	        
	        
	}
	public static void Sensor_Menu() {
		Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            
            System.out.println("1. View");
            System.out.println("2. View By Time");
            
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            SensorDaompl sdao =new SensorDaompl();
            switch(choice) {
            case 1:
            	
            	List<SensorData>datas=sdao.getAllSensorDetails();
            	System.out.println(datas.size());
            	for(SensorData data:datas)
            		System.out.println(data);
            	break;
            case 2:
            	System.out.print("Enter StartDate: ");
                String startDate = scanner.nextLine();
                System.out.print("Enter EndDate: ");
                String endDate = scanner.nextLine();
            	
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date sdate;
				try {
					sdate = dateFormat.parse(startDate);
				
				Date edate = dateFormat.parse(endDate);
		List<SensorData>datas1=sdao.getSensorDataByDateRange(sdate, edate);
    	System.out.println(datas1.size());
    	for(SensorData data:datas1)
    		System.out.println(data.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	break;
            case 3:
            	System.out.println("Logging out...");
                
            	return;
            default:
                System.out.println("Invalid choice. Please try again.");
        
            }
    	
    }
	}

	
	public static void Log_Menu() {
		Scanner scanner = new Scanner(System.in);
        int choice;

         LogDaompl ldao1 =new LogDaompl();
        while (true) {
            
            System.out.println("1. View");
            System.out.println("2. View By Time");
            System.out.println("3. View By LogType");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
            case 1:
            	List<Log>logs=ldao1.getAllLog();
            	
            	for(Log log:logs)
            		System.out.println(log);
            	
            	break;
            case 2:
            	
            	System.out.print("Enter StartDate: ");
            	
                String startDate = scanner.nextLine();
                
                System.out.print("Enter EndDate: ");
                String endDate = scanner.nextLine();
            	
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					Date sdate = dateFormat.parse(startDate);
				
				Date edate = dateFormat.parse(endDate);
            	
            	List<Log>logs1=ldao1.getSensorDataByDateRange(sdate, edate);
            	
            	for(Log log:logs1)
            		System.out.println(log);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	break;
            case 3:
            	System.out.println("1. info");
                System.out.println("2. warning");
                System.out.println("3. error");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
            	int l =scanner.nextInt();
            	List<Log> Logs = null;
                switch (l) {
                
                case 1:
                	 Logs = ldao1.getLogByLogType(LogType.info);
                    
                    
                	break;
                case 2:
                	 Logs = ldao1.getLogByLogType(LogType.warning);
                    
                    
                    
                	break;
                case 3:
                	 Logs = ldao1.getLogByLogType(LogType.error);
                    
                    
                    
                	break;
                	
                	
                    
                
                case 4:
                	return;
                
                }
                System.out.println("Logs of type 'info':");
                for (Log log : Logs) {
                    System.out.println(log);}
            
            	break;
            	
            case 4:
            	return;
            default:
                System.out.println("Invalid choice. Please try again.");
        
            		
    }

}
	}
}
