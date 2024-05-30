package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActuatorService {

    @Autowired
    private ActuatorRepository actuatorRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public List<Actuator> findAll() {
        return actuatorRepository.findAll();
    }

    public Actuator save(Actuator actuator) {
        return actuatorRepository.save(actuator);
    }

    public void delete(Actuator actuator) {
        actuatorRepository.delete(actuator);
    }

    public Optional<Actuator> findById(UUID id) {
        return actuatorRepository.findById(id);
    }

    public List<Actuator> findByFarmer(Farmer farmer) {
        return actuatorRepository.findByFarmer(farmer);
    }

    public Actuator addActuator(Farmer farmer) {
        Actuator actuator = new Actuator(farmer);
        return actuatorRepository.save(actuator);
    }

    public Actuator assignActuatorToField(Actuator actuator, Field field) {
        actuator.setField(field);
        return actuatorRepository.save(actuator);
    }
}
