package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.util.Pair;
import java.util.List;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pair<Integer, Integer> getCoord() {
        return coord;
    }

    public void setCoord(Pair<Integer, Integer> coord) {
        this.coord = coord;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Actuator getActuator() {
        return actuator;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }
}
