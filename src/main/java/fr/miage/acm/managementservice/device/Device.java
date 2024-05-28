package fr.miage.acm.managementservice.device;

import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

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

    public Device(DeviceState state, Farmer farmer) {
        this.state = state;
        this.farmer = farmer;
    }
}
