package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Actuator extends Device {

    @OneToOne
    @JoinColumn(name = "watering_event_id", referencedColumnName = "id")
    private WateringEvent wateringEvent;

    public Actuator(DeviceState state) {
        super(state);
        this.wateringEvent = null;
    }

    public Actuator() {
        // Default constructor required by JPA
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
