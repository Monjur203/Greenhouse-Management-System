package com.anudip.app.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "SensorData")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ReadingID")
    private Integer readingId;

    
    @ManyToOne
    @JoinColumn(name = "DeviceID")
    private Device device;

    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @Column(name = "AirHumidity")
    private Float airHumidity;

    @Column(name = "Temperature")
    private Float temperature;

    @Column(name = "SoilHumidity")
    private Float soilHumidity;

    @PrePersist
    protected void onCreate() {
        timestamp = new Timestamp(new Date().getTime());
    }
    
    
    // Getters and setters

    @Override
	public String toString() {
		return "SensorData [readingId=" + readingId + ", deviceId=" + device + ", timestamp=" + timestamp
				+ ", airHumidity=" + airHumidity + ", temperature=" + temperature + ", soilHumidity=" + soilHumidity
				+ "]";
	}


	public Integer getReadingId() {
        return readingId;
    }

    public void setReadingId(Integer readingId) {
        this.readingId = readingId;
    }

    public Device getDeviceId() {
        return device;
    }

    public void setDeviceId(Device device) {
        this.device = device;
    }

   
   

    public Float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Float airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(Float soilHumidity) {
        this.soilHumidity = soilHumidity;
    }
}
