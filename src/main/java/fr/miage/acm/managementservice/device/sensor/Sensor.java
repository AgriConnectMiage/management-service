package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sensor extends Device {
    // Interval between two measurements in milliseconds
    private float interval;

    public Sensor(DeviceState state, Farmer farmer) {
        super(state, farmer);
        this.interval = 1000;
    }

    public Sensor() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "interval=" + interval +
                ", state=" + getState() +
                '}';
    }
}
