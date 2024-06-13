package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.actuator.ActuatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WateringSchedulerService {

    private WateringSchedulerRepository wateringSchedulerRepository;
    private ActuatorRepository actuatorRepository;

    public WateringSchedulerService(WateringSchedulerRepository wateringSchedulerRepository, ActuatorRepository actuatorRepository) {
        this.wateringSchedulerRepository = wateringSchedulerRepository;
        this.actuatorRepository = actuatorRepository;
    }

    public void deleteWateringScheduler(WateringScheduler wateringScheduler) {
        wateringSchedulerRepository.delete(wateringScheduler);
    }

    public void deleteWateringSchedulerByActuator(Actuator actuator) {
        wateringSchedulerRepository.deleteByActuator(actuator);
    }

    public Optional<WateringScheduler> findByActuator(Actuator actuator) {
        return wateringSchedulerRepository.findByActuator(actuator);
    }

    public Optional<WateringScheduler> findByActuator(UUID actuatorId) {
        Optional<Actuator> actuator = actuatorRepository.findById(actuatorId);
        if (actuator.isPresent()) {
            return wateringSchedulerRepository.findByActuator(actuator.get());
        }
        else {
            return Optional.empty();
        }
    }

}