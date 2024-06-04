package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FarmerServiceTest {

    private final FarmerService farmerService;

    public FarmerServiceTest(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    public FarmerServiceTest(FarmerService farmerService, Sensor sensor, Actuator actuator) {
        this.farmerService = farmerService;
    }

    public void createFarmers() {
        farmerService.createFarmer("John", "Doe", "johndoe@gmail.com", "password", 3);
        farmerService.createFarmer("Jack", "Doe", "jackdoe@gmail.com", "password", 3);
        farmerService.createFarmer("Jane", "Doe", "janedoe@gmail.com", "password", 3);
    }

    public void getFarmers() {
        farmerService.findAll().forEach(farmer -> System.out.println(farmer.toString()));
    }

    // remove all farmers
    public void removeFarmers() {
        List<Farmer> farmers = farmerService.findAll();
        farmers.forEach(farmerService::removeFarmer);
    }

    public void editPassword() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.editPassword(farmer.getId(), "newPassword");
            System.out.println("Password edited: " + farmer.getEmail());
        }
    }

}