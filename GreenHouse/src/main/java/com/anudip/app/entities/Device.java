package com.anudip.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Device {
	
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		
		private int DeviceID;
		@NotBlank(message = "Location cannot be blank")
		private String Greenhouse;

		public Device() {
			super();
	    }
		
		
		public Device ( String Greenhouse) {
			super();
			
			this.Greenhouse = Greenhouse;
			
		}



		public int getDeviceID() {
			return DeviceID;
		}



		public void setDeviceID(int deviceID) {
			DeviceID = deviceID;
		}



		public String getLocation() {
			return Greenhouse;
		}



		public void setLocation(String location) {
			Greenhouse = location;
		}



		@Override
		public String toString() {
			return "Device [DeviceID=" + DeviceID + ", Greenhouse=" + Greenhouse + "]";
		}
		
		
		
		

}
