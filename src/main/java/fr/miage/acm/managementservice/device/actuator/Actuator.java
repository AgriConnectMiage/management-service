package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.wateringevent.WateringEvent;
import fr.miage.acm.managementservice.farmer.Farmer;
import java.util.UUID;

import java.util.Optional;

public class Actuator extends Device {
    private Optional<WateringEvent> wateringEvent;

    public Actuator(UUID id, DeviceState state, Farmer farmer, WateringEvent wateringEvent) {
        super(id, state, farmer);
        this.wateringEvent = Optional.ofNullable(wateringEvent);
    }

    public Optional<WateringEvent> getWateringEvent() {
        return wateringEvent;
    }

    public void setWateringEvent(WateringEvent wateringEvent) {
        this.wateringEvent = Optional.ofNullable(wateringEvent);
    }
}
