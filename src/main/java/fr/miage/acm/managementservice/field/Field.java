package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Field {
    private UUID id;
    private Pair<Integer, Integer> coord;
    private Farmer farmer;
    private List<Sensor> sensors;
    private Actuator actuator;

    public Field(UUID id, Pair<Integer, Integer> coord, Farmer farmer, List<Sensor> sensors, Actuator actuator) {
        this.id = id;
        this.coord = coord;
        this.farmer = farmer;
        this.sensors = sensors;
        this.actuator = actuator;
    }
}
