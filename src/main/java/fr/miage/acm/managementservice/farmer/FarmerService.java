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
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            Actuator actuator = new Actuator(DeviceState.NOT_ASSIGNED, farmer);
            farmer.getActuators().add(actuator);
            farmerRepository.save(farmer);
        }
    }

    public Optional<Farmer> addSensor(UUID farmerId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            Sensor sensor = new Sensor(DeviceState.NOT_ASSIGNED, farmer);
            farmer.getSensors().add(sensor);
            farmerRepository.save(farmer);
            return Optional.of(farmer);
        }
        return Optional.empty();
    }

    public void removeActuator(UUID farmerId, UUID actuatorId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        Optional<Actuator> actuatorOptional = actuatorService.findById(actuatorId);
        if (farmerOptional.isPresent() && actuatorOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            Actuator actuator = actuatorOptional.get();
            if (actuator.getState() == DeviceState.ON || actuator.getState() == DeviceState.OFF) {
                actuator.setState(DeviceState.NOT_ASSIGNED);
                actuator.setField(null);
                if (actuator.getField() != null) {
                    actuator.getField().setActuator(null);
                    fieldService.save(actuator.getField());
                }
            }
            farmer.getActuators().removeIf(a -> a.getId().equals(actuatorId));
            actuator.setFarmer(null);
            farmerRepository.save(farmer);
            actuatorService.delete(actuator);
        }
    }

    public void removeSensor(UUID farmerId, UUID sensorId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        Optional<Sensor> sensorOptional = sensorService.findById(sensorId);
        if (farmerOptional.isPresent() && sensorOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            Sensor sensor = sensorOptional.get();
            if (sensor.getState() == DeviceState.ON || sensor.getState() == DeviceState.OFF) {
                sensor.setState(DeviceState.NOT_ASSIGNED);
                sensor.setField(null);
            }
            farmer.getSensors().removeIf(s -> s.getId().equals(sensorId));
            sensor.setFarmer(null);
            farmerRepository.save(farmer);
            sensorService.delete(sensor);
        }
    }

    public Optional<Actuator> assignActuatorToField(UUID farmerId, UUID actuatorId, UUID fieldId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        Optional<Actuator> actuatorOptional = actuatorService.findById(actuatorId);
        Optional<Field> fieldOptional = fieldService.findById(fieldId);
        if (farmerOptional.isPresent() && actuatorOptional.isPresent() && fieldOptional.isPresent()) {
            Actuator actuator = actuatorOptional.get();
            Field field = fieldOptional.get();
            if (actuator.getState() == DeviceState.NOT_ASSIGNED) {
                actuator.setState(DeviceState.OFF);
                actuator.setField(field);
                actuatorService.save(actuator);
                return Optional.of(actuator);
            } else {
                System.out.println("Actuator is already assigned to a field");
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public Optional<Sensor> assignSensorToField(UUID farmerId, UUID sensorId, UUID fieldId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        Optional<Sensor> sensorOptional = sensorService.findById(sensorId);
        Optional<Field> fieldOptional = fieldService.findById(fieldId);
        if (farmerOptional.isPresent() && sensorOptional.isPresent() && fieldOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            Field field = fieldOptional.get();
            if (sensor.getState() == DeviceState.NOT_ASSIGNED) {
                sensor.setState(DeviceState.OFF);
                sensor.setField(field);
                sensorService.save(sensor);
                return Optional.of(sensor);
            } else {
                System.out.println("Sensor is already assigned to a field");
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
