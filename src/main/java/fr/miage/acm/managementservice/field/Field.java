package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.farmer.Farmer;
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
public class Field {

    @Id
    @GeneratedValue
    private UUID id;

    private Pair<Integer, Integer> coordinates; // Using Pair for coordinates

    @Relationship(type = "OWNED_BY", direction = Relationship.Direction.INCOMING)
    private Farmer farmer;

    @Relationship(type = "HAS_SENSOR")
    private List<Sensor> sensors = new ArrayList<>();

    @Relationship(type = "HAS_ACTUATOR")
    private Actuator actuator;

    public Field(Pair<Integer, Integer> coordinates, Farmer farmer) {
        this.coordinates = coordinates;
        this.farmer = farmer;
        this.sensors = new ArrayList<>();
        this.actuator = null;
    }
}
