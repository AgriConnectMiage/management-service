package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SensorServiceTest {
    private final SensorService sensorService;
    private final FarmerService farmerService;
    private final FieldService fieldService;

    public SensorServiceTest(SensorService sensorService, FarmerService farmerService, FieldService fieldService) {
        this.sensorService = sensorService;
        this.farmerService = farmerService;
        this.fieldService = fieldService;
    }

    public void getSensors() {
        sensorService.findAll().forEach(sensor -> System.out.println(sensor.toString()));
    }

    // Print all sensors
    public void removeSensors() {
        sensorService.findAll().forEach(sensor -> System.out.println(sensor.toString()));
    }

    public void addSensor() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        sensorService.addSensor(farmer);
    }

    public void assignSensorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Field field = fieldService.findByFarmer(farmer).get(0);
        Sensor sensor = sensorService.findByFarmer(farmer).get(0);
        sensorService.assignSensorToField(sensor, field);
    }

}