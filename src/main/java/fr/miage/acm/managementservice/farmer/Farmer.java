package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
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


}
