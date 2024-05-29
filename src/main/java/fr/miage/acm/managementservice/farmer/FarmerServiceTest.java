package fr.miage.acm.managementservice.farmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmerServiceTest {

    private final FarmerService farmerService;

    @Autowired
    public FarmerServiceTest(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    public void createFarmers() {
        createFarmer("John", "Doe", "johndoe@gmail.com", "password", 3);
        createFarmer("Jack", "Doe", "jackdoe@gmail.com", "password", 3);
        createFarmer("Jane", "Doe", "janedoe@gmail.com", "password", 3);
    }

    public void createFarmer(String firstName, String lastName, String email, String password, int fieldSize) {
        Farmer farmerSearch = farmerService.findByEmail(email);
        if (farmerSearch == null) {
            Farmer farmer = new Farmer(firstName, lastName, email, password, fieldSize);
            farmerService.save(farmer);
            System.out.println("Farmer created: " + farmer.getEmail());
        }
    }

    public void removeFarmers() {
        removeFarmerByEmail("johndoe@gmail.com");
        removeFarmerByEmail("janedoe@gmail.com");
        removeFarmerByEmail("jackdoe@gmail.com");
    }

    private void removeFarmerByEmail(String email) {
        Farmer farmer = farmerService.findByEmail(email);
        if (farmer != null) {
            farmerService.delete(farmer);
            System.out.println("Farmer removed: " + farmer.getEmail());
        }
    }

    public void editPassword() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.editPassword(farmer.getId(), "newPassword");
            System.out.println("Password edited: " + farmer.getEmail());
        }
    }
}