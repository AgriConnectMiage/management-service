package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import java.util.Optional;

@Getter
@Setter
public class Actuator extends Device {
    private Optional<WateringEvent> wateringEvent;

    public Actuator(UUID id, DeviceState state, Farmer farmer, WateringEvent wateringEvent) {
        super(state, farmer);
        this.wateringEvent = Optional.ofNullable(wateringEvent);
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "id=" + getId() +
                ", state=" + getState() +
                ", farmer=" + getFarmer() +
                ", wateringEvent=" + wateringEvent +
                '}';
    }
}
