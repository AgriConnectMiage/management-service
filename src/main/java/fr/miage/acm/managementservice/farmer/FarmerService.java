package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

    public Optional<Farmer> findById(UUID id) {
        return farmerRepository.findById(id);
    }

    public Farmer save(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public void deleteById(UUID id) {
        farmerRepository.deleteById(id);
    }

    public Optional<Farmer> updatePassword(UUID id, String newPassword) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.setPassword(newPassword);
            farmerRepository.save(farmer);
            return Optional.of(farmer);
        }
        return Optional.empty();
    }

    public Optional<Farmer> addSensor(UUID id, Sensor sensor) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getSensors().add(sensor);
            farmerRepository.save(farmer);
            return Optional.of(farmer);
        }
        return Optional.empty();
    }

    // Add actuators
    public Optional<Farmer> addActuator(UUID id, Actuator actuator) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getActuators().add(actuator);
            farmerRepository.save(farmer);
            return Optional.of(farmer);
        }
        return Optional.empty();
    }
}
