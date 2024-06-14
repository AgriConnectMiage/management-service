package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.client.MeasurementServiceClient;
import fr.miage.acm.managementservice.device.measurement.MeasurementService;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerRepository;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SensorService {

    private final FarmerRepository farmerRepository;
    private SensorRepository sensorRepository;

    private FieldRepository fieldRepository;

    private final MeasurementService measurementService;

    private final MeasurementServiceClient measurementServiceClient;

    public SensorService(SensorRepository sensorRepository, FieldRepository fieldRepository, MeasurementService measurementService, MeasurementServiceClient measurementServiceClient, FarmerRepository farmerRepository) {
        this.sensorRepository = sensorRepository;
        this.fieldRepository = fieldRepository;
        this.measurementService = measurementService;
        this.measurementServiceClient = measurementServiceClient;
        this.farmerRepository = farmerRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findById(UUID id) {
        return sensorRepository.findById(id);
    }

    public List<Sensor> findAllByIdIn(List<UUID> ids) {
        return sensorRepository.findAllByIdIn(ids);
    }

    public List<Sensor> findByFarmer(Farmer farmer) {
        return sensorRepository.findByFarmer(farmer);
    }

    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public void delete(Sensor sensor) {
        sensorRepository.delete(sensor);
    }

    public Sensor addSensor(UUID farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElseThrow();
        Sensor sensor = new Sensor(farmer);
        return sensorRepository.save(sensor);
    }

    @Transactional
    public void removeSensorsByFarmer(Farmer farmer) {
        sensorRepository.deleteByFarmer(farmer);
    }

    public void assignSensorToField(Sensor sensor, Field field) {
        // check if field already has a sensor
        if (sensorRepository.findByField(field).isPresent()) {
            throw new IllegalArgumentException("Field already has a sensor");
        }
        sensor.setField(field);

        this.changeState(sensor, DeviceState.OFF);
        sensorRepository.save(sensor);

    }

    public void unassignSensorFromField(Sensor sensor) {
        sensor.setField(null);
        this.changeState(sensor, DeviceState.NOT_ASSIGNED);
        sensorRepository.save(sensor);

    }

    public Sensor changeState(Sensor sensor, DeviceState newState) {
        DeviceState oldState = sensor.getState();
        sensor.setState(newState);
        sensorRepository.save(sensor);
        if (newState == DeviceState.ON && oldState == DeviceState.OFF) {
            System.out.println("Scheduling sensor task");
            measurementService.scheduleSensorTask(sensor.getId());
        }
        if (newState == DeviceState.OFF && oldState == DeviceState.ON) {
            measurementService.unscheduleSensorTask(sensor.getId());
        }
        return sensor;
    }

    public Sensor changeInterval(Sensor sensor, int interval) {
        sensor.setInterval(interval);
        sensorRepository.save(sensor);
        measurementServiceClient.changeSensorInterval(sensor.getId(), interval);
        return sensor;
    }

    public void changeInterval(List<Sensor> sensors, int interval) {
        sensors.forEach(sensor -> {
            sensor.setInterval(interval);
            sensorRepository.save(sensor);
            measurementServiceClient.changeSensorInterval(sensor.getId(), interval);
        });
    }

    public void updateMeasures(UUID sensorId, float temperature, float humidity) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();
            sensor.setLastTemperatureMeasured(temperature);
            sensor.setLastHumidityMeasured(humidity);
            sensor.setLastMeasurementTime(LocalDateTime.now());
            sensorRepository.save(sensor);
        } else {
            throw new IllegalArgumentException("Sensor not found");
        }
    }
}
