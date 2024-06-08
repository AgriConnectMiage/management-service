package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import org.springframework.stereotype.Service;

@Service
public class WateringSchedulerService {

    private WateringSchedulerRepository wateringSchedulerRepository;

    public WateringSchedulerService(WateringSchedulerRepository wateringSchedulerRepository) {
        this.wateringSchedulerRepository = wateringSchedulerRepository;
    }

    public void deleteWateringScheduler(WateringScheduler wateringScheduler) {
        wateringSchedulerRepository.delete(wateringScheduler);
    }

    public void deleteWateringSchedulerByActuator(Actuator actuator) {
        wateringSchedulerRepository.deleteByActuator(actuator);
    }
}