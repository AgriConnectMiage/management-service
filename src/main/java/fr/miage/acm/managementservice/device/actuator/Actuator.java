package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.Device;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
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

    @OneToOne
    @JoinColumn(name = "field_id")
    private Field field;

    public Actuator(Farmer farmer) {
        super(farmer);
        this.wateringEvent = null;
        this.field = null;
    }

    public Actuator() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "Actuator{" +
                "id=" + getId() +
                ", state=" + getState() +
                ", wateringEvent=" + getWateringEvent() +
                ", field=" + getField() +
                ", farmer=" + getFarmer() +
                '}';
    }

    public void setState(DeviceState newState) {
        if ((newState == DeviceState.OFF || newState == DeviceState.ON) && this.getField() == null) {
            throw new IllegalStateException("Cannot change state to " + newState + " of actuator without field");
        }
        if (newState == DeviceState.NOT_ASSIGNED && this.getField() != null) {
            throw new IllegalStateException("Cannot change state to " + newState + " of actuator assigned to a field");
        }
        this.state = newState;
        return;
    }
}
