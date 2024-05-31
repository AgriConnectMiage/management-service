package fr.miage.acm.managementservice.device.watering.event;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WateringEventService {

    @Autowired
    private WateringEventRepository wateringEventRepository;

    public List<WateringEvent> findAll() {
        return wateringEventRepository.findAll();
    }

    // Add watering event
    public WateringEvent addWateringEvent(WateringEvent wateringEvent) {
        return wateringEventRepository.save(wateringEvent);
    }
}
