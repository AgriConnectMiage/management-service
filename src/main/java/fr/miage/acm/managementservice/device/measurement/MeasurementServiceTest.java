package fr.miage.acm.managementservice.device.measurement;

import org.springframework.stereotype.Component;

@Component
public class MeasurementServiceTest {

    private final MeasurementService measurementService;

    public MeasurementServiceTest(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    public void getMeasurements() {
        measurementService.findAll().forEach(measurement -> System.out.println(measurement.toString()));
    }
}
