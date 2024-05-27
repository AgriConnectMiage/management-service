package fr.miage.acm.managementservice.device;

import java.util.UUID;
import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Device {
    private UUID id;
    private DeviceState state;
    private Farmer farmer;

    public Device(UUID id, DeviceState state, Farmer farmer) {
        this.id = id;
        this.state = state;
        this.farmer = farmer;
    }
}
