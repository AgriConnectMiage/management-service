package fr.miage.acm.managementservice.device.measurement;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MeasurementService {

    MeasurementClient measurementClient;
    MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementClient measurementClient, MeasurementRepository measurementRepository) {
        this.measurementClient = measurementClient;
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public void scheduleSensorTask(UUID sensorId) {
        measurementClient.scheduleSensorTask(sensorId);
    }

    public void unscheduleSensorTask(UUID sensorId) {
        measurementClient.unscheduleSensorTask(sensorId);
    }
}
