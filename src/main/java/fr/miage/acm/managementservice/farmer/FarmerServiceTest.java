package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
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
        farmerService.createFarmer("John", "Doe", "johndoe@gmail.com", "password", 3);
        farmerService.createFarmer("Jack", "Doe", "jackdoe@gmail.com", "password", 3);
        farmerService.createFarmer("Jane", "Doe", "janedoe@gmail.com", "password", 3);
    }

    public void getFarmers() {
        farmerService.findAll().forEach(farmer -> System.out.println(farmer.toString()));
    }

    public void removeFarmers() {
        farmerService.findAll().forEach(farmer -> {
            farmerService.delete(farmer);
            System.out.println("Farmer removed: " + farmer.getEmail());
        });
    }

    public void editPassword() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.editPassword(farmer.getId(), "newPassword");
            System.out.println("Password edited: " + farmer.getEmail());
        }
    }

    public void addActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.addActuator(farmer.getId());
            System.out.println("Actuator added: " + farmer.getEmail());
        }
    }

    public void addSensor() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.addSensor(farmer.getId());
            System.out.println("Sensor added: " + farmer.getEmail());
        }
    }

    public void removeSensor() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.removeSensor(farmer.getId(), farmer.getSensors().get(0).getId());
            System.out.println("Sensor removed: " + farmer.getEmail());
        }
    }

    public void removeActuator() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
            farmerService.removeActuator(farmer.getId(), farmer.getActuators().get(0).getId());
            System.out.println("Actuator removed: " + farmer.getEmail());
        }
    }

    public void assignActuatorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
//            farmerService.assignActuatorToField(farmer.getId(), farmer.getActuators().get(0).getId(), farmer.getFields().get(0).getId());
            System.out.println("Actuator assigned to field: " + farmer.getEmail());
        }
    }

    public void assignSensorToField() {
        Farmer farmer = farmerService.findByEmail("johndoe@gmail.com");
        if (farmer != null) {
//            farmerService.assignSensorToField(farmer.getId(), farmer.getSensors().get(0).getId(), farmer.getFields().get(0).getId());
            System.out.println("Sensor assigned to field: " + farmer.getEmail());
        }
    }

}