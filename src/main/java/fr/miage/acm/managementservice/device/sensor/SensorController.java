package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.api.ApiSensor;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerRepository;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final FarmerRepository farmerRepository;
    private final SensorRepository sensorRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public SensorController(SensorService sensorService, FarmerRepository farmerRepository, SensorRepository sensorRepository, FieldRepository fieldRepository) {
        this.sensorService = sensorService;
        this.farmerRepository = farmerRepository;
        this.sensorRepository = sensorRepository;
        this.fieldRepository = fieldRepository;
    }

    @PostMapping
    public Sensor createSensor(@RequestParam UUID farmerId) {
        return sensorService.addSensor(farmerId);
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable UUID id) {
        Optional<Sensor> sensor = sensorService.findById(id);
        sensor.ifPresent(sensorService::delete);
    }

    @PostMapping("/{id}/assign")
    public void assignSensorToField(@PathVariable UUID id, @RequestParam UUID fieldId) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            Optional<Field> optionalField = fieldRepository.findById(fieldId);
            if (optionalField.isEmpty()) {
                return;
            }
            Field field = optionalField.get();
            sensorService.assignSensorToField(sensor.get(), field);
        }
    }

    @PostMapping("/{id}/unassign")
    public void unassignSensorFromField(@PathVariable UUID id) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            sensorService.unassignSensorFromField(sensor.get());
        }
    }

    @PostMapping("/{id}/state")
    public Sensor changeSensorState(@PathVariable UUID id, @RequestParam DeviceState state) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            return sensorService.changeState(sensor.get(), state);
        }
        return null;
    }

    @PostMapping("/{id}/interval")
    public Sensor changeSensorInterval(@PathVariable UUID id, @RequestParam int interval) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            return sensorService.changeInterval(sensor.get(), interval);
        }
        return null;
    }

    // update sensor temp, humidity, measure date
    @PostMapping("/{id}/measure")
    public void updateMeasures(@PathVariable UUID id, @RequestParam float temperature, @RequestParam float humidity) {
        sensorService.updateMeasures(id, temperature, humidity);
    }
}
