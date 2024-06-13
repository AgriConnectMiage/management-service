package fr.miage.acm.managementservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "measurement-service")
public interface MeasurementServiceClient {

    // Schedule sensor task with post mapping
    @PostMapping("/measurements/sensor/{sensorId}/schedule")
    void scheduleSensorTask(@PathVariable("sensorId") UUID sensorId);


    @DeleteMapping("/measurements/sensor/{sensorId}/schedule")
    void unscheduleSensorTask(@PathVariable("sensorId") UUID sensorId);

    @PutMapping("/measurements/sensor/{sensorId}/interval")
    void changeSensorInterval(@PathVariable("sensorId") UUID sensorId, @RequestParam int interval);
}
