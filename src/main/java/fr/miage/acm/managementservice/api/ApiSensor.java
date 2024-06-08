package fr.miage.acm.managementservice.api;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApiSensor {

    private UUID id;
    private DeviceState state;
    private float interval;
    private Field field;
    private Integer currentTemperature;
    private Integer currentHumidity;

    public ApiSensor(Sensor sensor) {
        this.id = sensor.getId();
        this.state = sensor.getState();
        this.interval = sensor.getInterval();
        this.field = sensor.getField();
        this.currentTemperature = sensor.getCurrentTemperature();
        this.currentHumidity = sensor.getCurrentHumidity();
    }
}
