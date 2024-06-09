package fr.miage.acm.managementservice.device.measurement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "measurement-service")
public interface MeasurementClient {

    // Schedule sensor task with post mapping
    @PostMapping("/measurements/sensor/{sensorId}/schedule")
    void scheduleSensorTask(@PathVariable("sensorId") UUID sensorId);


    @DeleteMapping("/measurements/sensor/{sensorId}/schedule")
    void unscheduleSensorTask(@PathVariable("sensorId") UUID sensorId);
}
