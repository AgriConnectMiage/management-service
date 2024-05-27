package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Node
public class Farmer {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Relationship(type = "HAS_FIELD")
    private List<Field> fields;

    @Relationship(type = "HAS_SENSOR")
    private List<Sensor> sensors;

    @Relationship(type = "HAS_ACTUATOR")
    private List<Actuator> actuators;

    public Farmer() {
    }

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
