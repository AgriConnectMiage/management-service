package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.api.ApiActuator;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/actuators")
public class ActuatorController {

    private final ActuatorService actuatorService;

    @Autowired
    public ActuatorController(ActuatorService actuatorService) {
        this.actuatorService = actuatorService;
    }

    @PostMapping
    public Actuator createActuator(@RequestParam UUID farmerId) {
        return actuatorService.addActuator(farmerId);
    }

    @DeleteMapping("/{id}")
    public void deleteActuator(@PathVariable UUID id) {
        Optional<Actuator> actuator = actuatorService.findById(id);
        actuator.ifPresent(actuatorService::removeActuator);
    }

    @PostMapping("/{id}/assign")
    public Actuator assignActuatorToField(@PathVariable UUID id, @RequestParam UUID fieldId) {
        Optional<Actuator> actuator = actuatorService.findById(id);
        if (actuator.isPresent()) {
            return actuatorService.assignActuatorToField(actuator.get(), fieldId);
        }
        return null;
    }

        @GetMapping
    public List<ApiActuator> getAllActuators() {
        return actuatorService.findAll().stream().map(ApiActuator::new).toList();
    }

    @GetMapping("/{id}")
    public Optional<Actuator> getActuatorById(@PathVariable UUID id) {
        return actuatorService.findById(id);
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Actuator> getActuatorsByFarmer(@PathVariable UUID farmerId) {
        Farmer farmer = new Farmer();
        farmer.setId(farmerId);
        return actuatorService.findByFarmer(farmer);
    }

    @PostMapping("/{id}/unassign")
    public Actuator unassignActuatorFromField(@PathVariable UUID id) {
        Optional<Actuator> actuator = actuatorService.findById(id);
        if (actuator.isPresent()) {
            return actuatorService.unassignActuatorFromField(actuator.get());
        }
        return null;
    }

    @PostMapping("/{id}/state")
    public Actuator changeActuatorState(@PathVariable UUID id, @RequestParam DeviceState state) {
        Optional<Actuator> actuator = actuatorService.findById(id);
        if (actuator.isPresent()) {
            return actuatorService.changeState(actuator.get(), state);
        }
        return null;
    }

    // find by  field
    @GetMapping("/field/{fieldId}")
    public Optional<ApiActuator> findByField(@PathVariable UUID fieldId) {
        Optional<Actuator> optionalActuator = actuatorService.findByField(fieldId);
        if(optionalActuator.isPresent()) {
            return Optional.of(new ApiActuator(optionalActuator.get()));
        }
        else{
            return Optional.empty();
        }
    }
}
