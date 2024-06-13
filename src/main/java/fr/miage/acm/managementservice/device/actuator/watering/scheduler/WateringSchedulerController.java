package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/actuators/{actuatorId}/watering-scheduler")
public class WateringSchedulerController {

    private final WateringSchedulerService wateringSchedulerService;

    public WateringSchedulerController(WateringSchedulerService wateringSchedulerService) {
        this.wateringSchedulerService = wateringSchedulerService;
    }

    @GetMapping("/{schedulerId}")
    public String getWateringScheduler(@PathVariable Long actuatorId, @PathVariable Long schedulerId) {
        return "Watering Scheduler " + schedulerId + " for Actuator " + actuatorId;
    }

    // find by actuator
    @GetMapping
    public Optional<WateringScheduler> findByActuator(@PathVariable UUID actuatorId) {
        return wateringSchedulerService.findByActuator(actuatorId);
    }

}
