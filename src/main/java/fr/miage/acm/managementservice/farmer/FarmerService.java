package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.ActuatorService;
import fr.miage.acm.managementservice.device.sensor.SensorService;
import fr.miage.acm.managementservice.field.FieldService;
import fr.miage.acm.managementservice.field.Field;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmerService {

    private FarmerRepository farmerRepository;

    private FieldService fieldService;
    private SensorService sensorService;
    private ActuatorService actuatorService;

    public FarmerService(FarmerRepository farmerRepository, FieldService fieldService, SensorService sensorService, ActuatorService actuatorService) {
        this.farmerRepository = farmerRepository;
        this.fieldService = fieldService;
        this.sensorService = sensorService;
        this.actuatorService = actuatorService;
    }

    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

    public Optional<Farmer> findById(UUID id) {
        return farmerRepository.findById(id);
    }

    public Farmer findByEmail(String email) {
        return farmerRepository.findByEmail(email);
    }

    public Farmer save(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public void removeFarmerById(UUID id) {
        Farmer farmer = farmerRepository.findById(id).orElse(null);
        if (farmer != null) {
            removeFarmer(farmer);
        }
    }

    public void delete(Farmer farmer) {
        farmerRepository.delete(farmer);
    }


    public Farmer createFarmer(String firstName, String lastName, String email, String password, int fieldSize) {
        Farmer farmerSearch = findByEmail(email);
        System.out.println(fieldSize);
        if (farmerSearch == null) {
            Farmer farmer = new Farmer(firstName, lastName, email, password, fieldSize);
            this.save(farmer);
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++) {
                    System.out.println("Creating field " + i + " " + j);
                    Field field = new Field(i + 1, j + 1, farmer);
                    fieldService.save(field);
                }
            }
            return farmer;
        } else {
            System.out.println("Farmer already exists");
        }
        return null;
    }

    public void removeFarmer(Farmer farmer) {
        fieldService.deleteFieldsByFarmer(farmer);
        sensorService.removeSensorsByFarmer(farmer);
        actuatorService.removeActuatorsByFarmer(farmer);
        farmerRepository.delete(farmer);
    }

    public void editPassword(UUID id, String password) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.setPassword(password);
            farmerRepository.save(farmer);
        }
    }
}
