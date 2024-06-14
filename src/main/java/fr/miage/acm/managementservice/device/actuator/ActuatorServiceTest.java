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
import java.util.List;

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

    public void addActuator(Farmer farmer) {
        actuatorService.addActuator(farmer.getId());
    }

    public void addActuators(int nbActuators) {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        for (int i = 0; i < nbActuators; i++) {
            actuatorService.addActuator(farmer.getId());
        }
    }

    public void assignActuatorToField(Farmer farmer, Field field) {
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        actuatorService.assignActuatorToField(actuator, field.getId());
    }

    public void assignActuatorsToFields(int nbActuators) {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        List<Field> fields = fieldService.findByFarmer(farmer);
        List<Actuator> actuators = actuatorService.findByFarmer(farmer);
        for (int i = 0; i < nbActuators; i++) {
            Field field = fields.get(i);
            Actuator actuator = actuators.get(i);
            actuatorService.assignActuatorToField(actuator, field.getId());
        }
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

    public void changeActuatorState(DeviceState state) {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        Actuator actuator = actuatorService.findByFarmer(farmer).get(0);
        actuatorService.changeState(actuator, state);
        actuatorService.save(actuator);
    }
}
