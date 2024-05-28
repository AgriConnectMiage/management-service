package fr.miage.acm.managementservice.device;

import fr.miage.acm.managementservice.farmer.Farmer;
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
public abstract class Device {
    @Id
    @GeneratedValue
    private UUID id;
    private DeviceState state;
    private Farmer farmer;

    @Relationship(type = "HAS_MEASUREMENT")
    private List<Measurement> measurements;

    public Device(DeviceState state, Farmer farmer) {
        this.state = state;
        this.farmer = farmer;
        this.measurements = new ArrayList<>();
    }
}
