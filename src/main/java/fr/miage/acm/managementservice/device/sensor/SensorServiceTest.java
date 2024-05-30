package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import org.springframework.stereotype.Component;

@Component
public class SensorServiceTest {
    private final SensorService sensorService;
    private final FarmerService farmerService;

    public SensorServiceTest(SensorService sensorService, FarmerService farmerService) {
        this.sensorService = sensorService;
        this.farmerService = farmerService;
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
}
