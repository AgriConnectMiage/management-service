package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Optional;

@Getter
@Setter
@Node
public class Actuator extends Device {
    @Relationship(type = "HAS_WATERING_EVENT")
    private WateringEvent wateringEvent;

    public Actuator(DeviceState state) {
        super(state);
        this.wateringEvent = null;
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "id=" + getId() +
                ", state=" + getState() +
                ", wateringEvent=" + wateringEvent +
                '}';
    }
}
