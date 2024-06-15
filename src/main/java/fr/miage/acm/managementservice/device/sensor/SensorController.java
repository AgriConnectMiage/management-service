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


    @GetMapping
    public List<ApiSensor> getAllSensors() {
        return sensorService.findAll().stream()
                .map(ApiSensor::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ApiSensor getSensorById(@PathVariable UUID id) {
        Optional<Sensor> optionalSensor = sensorService.findById(id);
        // create ApiSensor from Sensor
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            ApiSensor apiSensor = new ApiSensor(sensor);
            System.out.println("apisensor envoyé :");
            System.out.println(apiSensor);
            return apiSensor;
        }
        return null;
    }

    @GetMapping("/farmer/{farmerId}")
    public List<ApiSensor> getSensorsByFarmer(@PathVariable UUID farmerId) {
        Optional<Farmer> farmer = farmerRepository.findById(farmerId);
        if (farmer.isPresent()) {
            return sensorService.findByFarmer(farmer.get()).stream()
                    .map(ApiSensor::new)
                    .toList();
        } else {
            System.out.println("No farmer found for id " + farmerId);
            return List.of();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable UUID id) {
        Optional<Sensor> sensor = sensorService.findById(id);
        sensor.ifPresent(sensorService::delete);
    }

    @PostMapping("/{id}/assign")
    public Sensor assignSensorToField(@PathVariable UUID id, @RequestParam UUID fieldId) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            Optional<Field> optionalField = fieldRepository.findById(fieldId);
            if (optionalField.isEmpty()) {
                return null;
            }
            Field field = optionalField.get();
            sensorService.assignSensorToField(sensor.get(), field);
            return sensor.get();
        }
        return null;
    }

    @PostMapping("/{id}/unassign")
    public Sensor unassignSensorFromField(@PathVariable UUID id) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
            sensorService.unassignSensorFromField(sensor.get());
            return sensor.get();
        }
        return null;
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
