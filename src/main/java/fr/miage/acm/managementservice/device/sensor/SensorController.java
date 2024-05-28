package fr.miage.acm.managementservice.device.sensor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SensorController {
    @RequestMapping("/sensors")
    public String getSensors() {
        return "All sensors";
    }
}
