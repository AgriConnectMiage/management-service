package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActuatorService {

    @Autowired
    private ActuatorRepository actuatorRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Actuator insertActuator(Actuator actuator) {
        return actuatorRepository.save(actuator);
    }

    public Optional<Actuator> assignActuatorToField(UUID actuatorId, UUID fieldId) {
        Optional<Actuator> actuatorOptional = actuatorRepository.findById(actuatorId);
        Optional<Field> fieldOptional = fieldRepository.findById(fieldId);

        if (actuatorOptional.isPresent() && fieldOptional.isPresent()) {
            Actuator actuator = actuatorOptional.get();
            Field field = fieldOptional.get();

            actuator.setState(DeviceState.OFF);
            actuator.setField(field);
            field.setActuator(actuator);
            actuatorRepository.save(actuator);
            fieldRepository.save(field);
            return Optional.of(actuator);
        }
        return Optional.empty();
    }

}
