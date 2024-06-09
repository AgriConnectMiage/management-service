package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuators/{actuatorId}/watering-schedulers")
public class WateringSchedulerController {

    @GetMapping("/{schedulerId}")
    public String getWateringScheduler(@PathVariable Long actuatorId, @PathVariable Long schedulerId) {
        return "Watering Scheduler " + schedulerId + " for Actuator " + actuatorId;
    }

}
