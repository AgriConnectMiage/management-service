package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.util.Pair;
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

    @Relationship(type = "HAS_FIELD")
    private List<Field> fields;

    @Relationship(type = "HAS_SENSOR")
    private List<Sensor> sensors;

    @Relationship(type = "HAS_ACTUATOR")
    private List<Actuator> actuators;

    public Farmer() {
        // Default constructor
    }

    public Farmer(UUID id, String firstName, String lastName, String email, String password, int fieldSize) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.fields = createFields(fieldSize, this);
        this.sensors = new ArrayList<>();
        this.actuators = new ArrayList<>();
    }

    // Method to create a list of fields representing a square matrix
    private List<Field> createFields(int size, Farmer farmer) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pair<Integer, Integer> coordinates = new Pair<>(i, j);
                Field field = new Field(coordinates, farmer);
                fields.add(field);
            }
        }
        return fields;
    }
}
