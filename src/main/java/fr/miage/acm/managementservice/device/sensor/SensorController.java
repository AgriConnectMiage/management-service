package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.api.ApiSensor;
import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerRepository;
import fr.miage.acm.managementservice.field.Field;
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

    @Autowired
    public SensorController(SensorService sensorService, FarmerRepository farmerRepository, SensorRepository sensorRepository) {
        this.sensorService = sensorService;
        this.farmerRepository = farmerRepository;
        this.sensorRepository = sensorRepository;
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
        if(optionalSensor.isPresent()) {
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

    @PostMapping
    public Sensor createSensor(@RequestBody Farmer farmer) {
        return sensorService.addSensor(farmer);
    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable UUID id) {
        Optional<Sensor> sensor = sensorService.findById(id);
        sensor.ifPresent(sensorService::delete);
    }

    @PostMapping("/{id}/assign")
    public void assignSensorToField(@PathVariable UUID id, @RequestBody Field field) {
        Optional<Sensor> sensor = sensorService.findById(id);
        if (sensor.isPresent()) {
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

    @PostMapping("/interval")
    public void changeSensorsInterval(@RequestBody List<UUID> sensorIds, @RequestParam int interval) {
        List<Sensor> sensors = sensorService.findAllByIdIn(sensorIds);
        sensorService.changeInterval(sensors, interval);
    }

    // update sensor temp, humidity, measure date
    @PostMapping("/{id}/measure")
    public void updateMeasures(@PathVariable UUID id, @RequestParam float temperature, @RequestParam float humidity) {
        sensorService.updateMeasures(id, temperature, humidity);
    }
}
