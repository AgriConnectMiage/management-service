package fr.miage.acm.managementservice;


import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.device.actuator.ActuatorServiceTest;
import fr.miage.acm.managementservice.device.measurement.MeasurementServiceTest;
import fr.miage.acm.managementservice.device.sensor.SensorServiceTest;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerServiceTest;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class ManagementServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ManagementServiceApplication.class, args);

        FarmerServiceTest farmerServiceTest = context.getBean(FarmerServiceTest.class);
        FieldServiceTest fieldServiceTest = context.getBean(FieldServiceTest.class);
        SensorServiceTest sensorServiceTest = context.getBean(SensorServiceTest.class);
        ActuatorServiceTest actuatorServiceTest = context.getBean(ActuatorServiceTest.class);
        MeasurementServiceTest measurementServiceTest = context.getBean(MeasurementServiceTest.class);

//        farmerServiceTest.getFarmers();
//        sensorServiceTest.getSensors();
//        actuatorServiceTest.getActuators();
//        fieldServiceTest.getFields();

//        farmerServiceTest.removeFarmers();
        farmerServiceTest.createFarmer();

        Farmer farmer = farmerServiceTest.getFarmer();
        Field field = fieldServiceTest.getFieldOfFarmer(farmer);

//        farmerServiceTest.createFarmers();
//        farmerServiceTest.editPassword();
        sensorServiceTest.addSensor(farmer);
//        sensorServiceTest.addSensors(1);
        actuatorServiceTest.addActuator(farmer);
//        actuatorServiceTest.addActuators(5);
        actuatorServiceTest.assignActuatorToField(farmer, field);
//        actuatorServiceTest.assignActuatorsToFields(5);
        sensorServiceTest.assignSensorToField(farmer, field);
//        sensorServiceTest.assignAllSensorsToField();
//        actuatorServiceTest.unassignActuatorToField();
//        sensorServiceTest.unassignSensorToField();
//        actuatorServiceTest.removeActuator();
//        sensorServiceTest.removeSensor();
//        actuatorServiceTest.changeActuatorState();
//        sensorServiceTest.changeSensorState(DeviceState.ON);
        sensorServiceTest.changeAllSensorsState(DeviceState.ON);

        // Delay 10 seconds
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        sensorServiceTest.changeAllSensorsState(DeviceState.OFF);
//        measurementServiceTest.getMeasurements();
    }
}