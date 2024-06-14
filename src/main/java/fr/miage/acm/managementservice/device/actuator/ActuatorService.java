package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.actuator.watering.scheduler.WateringSchedulerRepository;
import fr.miage.acm.managementservice.device.actuator.watering.scheduler.WateringSchedulerService;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerRepository;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActuatorService {

    private final FarmerRepository farmerRepository;
    private ActuatorRepository actuatorRepository;

    private FieldRepository fieldRepository;

    private WateringSchedulerService wateringSchedulerService;

    public ActuatorService(ActuatorRepository actuatorRepository, FieldRepository fieldRepository, WateringSchedulerService wateringSchedulerService, FarmerRepository farmerRepository) {
        this.actuatorRepository = actuatorRepository;
        this.fieldRepository = fieldRepository;
        this.wateringSchedulerService = wateringSchedulerService;
        this.farmerRepository = farmerRepository;
    }

    public List<Actuator> findAll() {
        return actuatorRepository.findAll();
    }

    public Actuator save(Actuator actuator) {
        return actuatorRepository.save(actuator);
    }


    public Optional<Actuator> findById(UUID id) {
        return actuatorRepository.findById(id);
    }

    public List<Actuator> findByFarmer(Farmer farmer) {
        return actuatorRepository.findByFarmer(farmer);
    }

    public Optional<Actuator> findByField(UUID fieldId) {
        return actuatorRepository.findByField(fieldRepository.findById(fieldId).orElseThrow());
    }

    public Actuator addActuator(UUID farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElseThrow();
        Actuator actuator = new Actuator(farmer);
        return actuatorRepository.save(actuator);
    }

    @Transactional
    public void removeActuator(Actuator actuator) {
        wateringSchedulerService.deleteWateringSchedulerByActuator(actuator);
        actuatorRepository.delete(actuator);
    }

    @Transactional
    public void removeActuatorsByFarmer(Farmer farmer) {
        actuatorRepository.deleteByFarmer(farmer);
    }

    public Actuator assignActuatorToField(Actuator actuator, UUID fieldId) {
        Optional<Field> optionalField = fieldRepository.findById(fieldId);
        if(optionalField.isEmpty()) {
            throw new IllegalArgumentException("Field not found");
        }
        Field field = optionalField.get();
        if(actuatorRepository.findByField(field).isPresent()) {
            throw new IllegalArgumentException("Field already has an actuator assigned");
        }
        actuator.setField(field);
        actuator.setState(DeviceState.OFF);

        return actuatorRepository.save(actuator);
    }

    @Transactional
    public Actuator unassignActuatorFromField(Actuator actuator) {
        actuator.setField(null);
        actuator.setState(DeviceState.NOT_ASSIGNED);
        wateringSchedulerService.deleteWateringSchedulerByActuator(actuator);
        return actuatorRepository.save(actuator);
    }

    public Actuator changeState(Actuator actuator, DeviceState state) {
        actuator.setState(state);
        return actuatorRepository.save(actuator);

    }
}