package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.FieldService;
import fr.miage.acm.managementservice.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private FieldService fieldService;

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

    public void deleteById(UUID id) {
        farmerRepository.deleteById(id);
    }

    public void delete(Farmer farmer) {
        farmerRepository.delete(farmer);

        // delete farmer's fields, sensors and actuators
        for (int i = 0; i < farmer.getFieldSize(); i++) {
            fieldService.delete(farmer.getFields().get(i));
        }
        for (int i = 0; i < farmer.getSensors().size(); i++) {
            farmer.getSensors().remove(i);
        }
        for (int i = 0; i < farmer.getActuators().size(); i++) {
            farmer.getActuators().remove(i);
        }

        // use for each loop to delete farmer's fields, sensors and actuators
        for (Field field : farmer.getFields()) {
            fieldService.delete(field);
        }
        for (Sensor sensor : farmer.getSensors()) {
            farmer.getSensors().remove(sensor);}
        for (Actuator actuator : farmer.getActuators()) {
            farmer.getActuators().remove(actuator);
        }

    }

    public void editPassword(UUID id , String password) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.setPassword(password);
            farmerRepository.save(farmer);
        }
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
