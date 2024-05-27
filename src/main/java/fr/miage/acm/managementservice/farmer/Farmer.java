package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;

import java.util.UUID;
import java.util.List;

public class Farmer {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Field> fields;
    private List<Sensor> sensors;
    private List<Actuator> actuators;

    public Farmer(UUID id, String firstName, String lastName, String email, String password, List<Field> fields, List<Sensor> sensors, List<Actuator> actuators) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.fields = fields;
        this.sensors = sensors;
        this.actuators = actuators;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(List<Actuator> actuators) {
        this.actuators = actuators;
    }
}
