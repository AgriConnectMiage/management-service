package fr.miage.acm.managementservice.device.measurement;

import fr.miage.acm.managementservice.client.MeasurementServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MeasurementService {

    MeasurementServiceClient measurementServiceClient;

    public MeasurementService(MeasurementServiceClient measurementServiceClient) {
        this.measurementServiceClient = measurementServiceClient;
    }

    public void scheduleSensorTask(UUID sensorId) {
        measurementServiceClient.scheduleSensorTask(sensorId);
    }

    public void unscheduleSensorTask(UUID sensorId) {
        measurementServiceClient.unscheduleSensorTask(sensorId);
    }
}
