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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private Integer fieldSize;

    @Relationship(type = "HAS_FIELD")
    private List<Field> fields;

    @Relationship(type = "HAS_SENSOR")
    private List<Sensor> sensors;

    @Relationship(type = "HAS_ACTUATOR")
    private List<Actuator> actuators;

    public Farmer(String firstName, String lastName, String email, String password, int fieldSize) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sensors = new ArrayList<>();
        this.actuators = new ArrayList<>();
        this.fieldSize = fieldSize;
        this.fields = new ArrayList<>();
        for(int x = 0; x < fieldSize; x++) {
            for(int y = 0; y < fieldSize; y++) {
                Field field = new Field(x+1, y+1, this);
                fields.add(field);
            }
        }
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fieldSize=" + fieldSize +
                ", fields=" + fields +
                ", sensors=" + sensors +
                ", actuators=" + actuators +
                '}';
    }
}
