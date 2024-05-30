package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActuatorServiceTest {
    private final ActuatorService actuatorService;
    private final FarmerService farmerService;


    @Autowired
    public ActuatorServiceTest(ActuatorService actuatorService, FarmerService farmerService) {
        this.actuatorService = actuatorService;
        this.farmerService = farmerService;
    }

    public void getActuators() {
        actuatorService.findAll().forEach(actuator -> System.out.println(actuator.toString()));
    }

    public void addActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        actuatorService.addActuator(farmer);
    }
}
