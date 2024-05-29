package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Setter
@Node
public class Sensor extends Device {
    // Interval between two measurements in milliseconds
    private float interval;

    public Sensor(DeviceState state) {
        super(state);
        this.interval = 1000;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "interval=" + interval +
                ", state=" + getState() +
                '}';
    }
}
