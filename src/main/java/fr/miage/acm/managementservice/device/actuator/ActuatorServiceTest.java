package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ActuatorServiceTest {
    private final ActuatorService actuatorService;
    private final FarmerService farmerService;
    private final FieldService fieldService;


    public ActuatorServiceTest(ActuatorService actuatorService, FarmerService farmerService, FieldService fieldService) {
        this.actuatorService = actuatorService;
        this.farmerService = farmerService;
        this.fieldService = fieldService;
    }

    public void getActuators() {
        actuatorService.findAll().forEach(actuator -> System.out.println(actuator.toString()));
    }

    public void addActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        actuatorService.addActuator(farmer);
    }

    public void assignActuatorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Field field = fieldService.findByFarmer(farmer).get(0);
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        actuatorService.assignActuatorToField(actuator, field);
    }

    public void unassignActuatorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        actuatorService.unassignActuatorFromField(actuator);
    }

    public void removeActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        System.out.println(actuator);
        actuatorService.removeActuator(actuator);
    }

    public void changeActuatorState() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        actuatorService.changeState(actuator, actuator.getState().equals(DeviceState.ON) ? DeviceState.OFF : DeviceState.ON);
        actuatorService.save(actuator);
    }
}
