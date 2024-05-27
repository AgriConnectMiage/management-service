package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Sensor extends Device {
    private float interval;

    public Sensor(UUID id, DeviceState state, Farmer farmer, float interval) {
        super(id, state, farmer);
        this.interval = interval;
    }
}
