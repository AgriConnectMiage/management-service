package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldService;
import org.springframework.stereotype.Component;
import fr.miage.acm.managementservice.device.DeviceState;

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

    public void removeSensors() {
        sensorService.findAll().forEach(sensor -> System.out.println(sensor.toString()));
    }


    public void addSensor() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        sensorService.addSensor(farmer);
    }

    public void addSensors(int number) {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        for (int i = 0; i < number; i++) {
            sensorService.addSensor(farmer);
        }
    }


    public void assignSensorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Field field = fieldService.findByFarmer(farmer).get(0);
        Sensor sensor = sensorService.findByFarmer(farmer).get(0);
        sensorService.assignSensorToField(sensor, field);
    }

    // assign all sensors to the first field of the first farmer
    public void assignAllSensorsToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Field field = fieldService.findByFarmer(farmer).get(0);
        List<Sensor> sensors = sensorService.findByFarmer(farmer);
        sensors.forEach(sensor -> sensorService.assignSensorToField(sensor, field));
    }

    public void unassignSensorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Sensor sensor = sensorService.findByFarmer(farmer).get(0);
        sensorService.unassignSensorFromField(sensor);
    }


    public void removeSensor() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Sensor sensor = sensorService.findByFarmer(farmer).get(0);
        sensorService.delete(sensor);
    }

    public void changeSensorState(DeviceState state) {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Sensor sensor = sensorService.findByFarmer(farmer).get(0);
        sensorService.changeState(sensor, state);
        sensorService.save(sensor);
    }

    public void changeAllSensorsState(DeviceState state) {
        List<Sensor> sensors = sensorService.findAll();
        sensors.forEach(sensor -> {
            sensorService.changeState(sensor, state);
            sensorService.save(sensor);
        });
    }

}
