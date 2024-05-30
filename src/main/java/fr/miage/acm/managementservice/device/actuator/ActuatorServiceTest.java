package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActuatorServiceTest {
    private final ActuatorService actuatorService;
    private final FarmerService farmerService;
    private final FieldService fieldService;


    @Autowired
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

    public void removeActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        System.out.println(actuator);
        actuatorService.delete(actuator);
    }
}
