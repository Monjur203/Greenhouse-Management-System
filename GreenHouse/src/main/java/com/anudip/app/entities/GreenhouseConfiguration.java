package com.anudip.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "GreenhouseConfiguration")
public class GreenhouseConfiguration {

    @Override
	public String toString() {
		return "GreenhouseConfiguration [configurationID=" + configurationID + ", configurationName="
				+ configurationName + ", value=" + value + ", description=" + description + ", user=" + user
				+ ", device=" + device + "]";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConfigurationID")
    private Integer configurationID;

    @Column(name = "ConfigurationName")
    private String configurationName;

    @Column(name = "Value")
    private String value;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DeviceID")
    private Device device;

    // Getters and setters

    public Integer getConfigurationID() {
        return configurationID;
    }

    public void setConfigurationID(Integer configurationID) {
        this.configurationID = configurationID;
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}

