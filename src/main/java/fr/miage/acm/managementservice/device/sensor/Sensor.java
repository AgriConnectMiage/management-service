package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import java.util.UUID;

public class Sensor extends Device {
    private float interval;

    public Sensor(UUID id, DeviceState state, Farmer farmer, float interval) {
        super(id, state, farmer);
        this.interval = interval;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }
}
