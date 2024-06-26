package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import fr.miage.acm.managementservice.api.ApiWateringScheduler;
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

    // find by actuator
    @GetMapping
    public ApiWateringScheduler findByActuator(@PathVariable UUID actuatorId) {
        Optional<WateringScheduler> optionalWateringScheduler = wateringSchedulerService.findByActuator(actuatorId);
        if (optionalWateringScheduler.isPresent()) {
            WateringScheduler wateringScheduler = optionalWateringScheduler.get();
            ApiWateringScheduler apiWateringScheduler = new ApiWateringScheduler(wateringScheduler);
            return apiWateringScheduler;
        }
        return null;
    }

}
