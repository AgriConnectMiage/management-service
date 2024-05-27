package fr.miage.acm.managementservice.device;

import java.util.UUID;
import fr.miage.acm.managementservice.farmer.Farmer;

public abstract class Device {
    private UUID id;
    private DeviceState state;
    private Farmer farmer;

    public Device(UUID id, DeviceState state, Farmer farmer) {
        this.id = id;
        this.state = state;
        this.farmer = farmer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
