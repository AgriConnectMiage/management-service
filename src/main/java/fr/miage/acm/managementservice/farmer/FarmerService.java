package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.actuator.ActuatorService;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.device.sensor.SensorService;
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
    @Autowired
    private SensorService sensorService;
    @Autowired
    private ActuatorService actuatorService;

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
            farmer.getSensors().remove(sensor);
        }
        for (Actuator actuator : farmer.getActuators()) {
            farmer.getActuators().remove(actuator);
        }

    }


    public Farmer createFarmer(String firstName, String lastName, String email, String password, int fieldSize) {
        Farmer farmerSearch = findByEmail(email);
        if (farmerSearch == null) {
            Farmer farmer = new Farmer(firstName, lastName, email, password, fieldSize);
            this.save(farmer);
            return farmer;
        }
        return null;
    }

    public void editPassword(UUID id, String password) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(id);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.setPassword(password);
            farmerRepository.save(farmer);
        }
    }

    public void addActuator(UUID farmerId) {
        Actuator actuator = new Actuator(DeviceState.NOT_ASSIGNED);
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getActuators().add(actuator);
            farmerRepository.save(farmer);
        }
    }

    public Optional<Farmer> addSensor(UUID farmerId) {
        Sensor sensor = new Sensor(DeviceState.NOT_ASSIGNED);
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getSensors().add(sensor);
            farmerRepository.save(farmer);
            return Optional.of(farmer);
        }
        return Optional.empty();
    }

    public void removeActuator(UUID farmerId, UUID actuatorId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getActuators().removeIf(actuator -> actuator.getId().equals(actuatorId));
            actuatorService.delete(actuatorService.findById(actuatorId).get());
            farmerRepository.save(farmer);
        }
    }

    public void removeSensor(UUID farmerId, UUID sensorId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            farmer.getSensors().removeIf(sensor -> sensor.getId().equals(sensorId));
            sensorService.delete(sensorService.findById(sensorId).get());
            farmerRepository.save(farmer);
        }
    }

    // Assign actuator to field
    public Optional<Actuator> assignActuatorToField(UUID farmerId, UUID actuatorId, UUID fieldId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        Optional<Actuator> actuatorOptional = actuatorService.findById(actuatorId);
        Optional<Field> fieldOptional = fieldService.findById(fieldId);

        if (farmerOptional.isPresent() && actuatorOptional.isPresent() && fieldOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            Actuator actuator = actuatorOptional.get();
            Field field = fieldOptional.get();
            if (actuator.getState() == DeviceState.NOT_ASSIGNED) {
                System.out.println(farmer);
                System.out.println(actuator);
                System.out.println(field);
                actuator.setState(DeviceState.OFF);
                actuator.setField(field);
                field.setActuator(actuator);
                actuatorService.save(actuator);
                fieldService.save(field);
                farmerRepository.save(farmer);
                System.out.println(farmer);
                System.out.println(actuator);
                System.out.println(field);
                return Optional.of(actuator);
            } else {
                System.out.println("Actuator is already assigned to a field");
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
