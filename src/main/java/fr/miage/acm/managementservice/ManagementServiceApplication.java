package fr.miage.acm.managementservice;


import fr.miage.acm.managementservice.device.actuator.ActuatorServiceTest;
import fr.miage.acm.managementservice.device.sensor.SensorServiceTest;
import fr.miage.acm.managementservice.farmer.FarmerServiceTest;
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

//        farmerServiceTest.getFarmers();
//        sensorServiceTest.getSensors();
//        actuatorServiceTest.getActuators();
//        fieldServiceTest.getFields();

//        farmerServiceTest.removeFarmers();
//        farmerServiceTest.createFarmers();
//        farmerServiceTest.editPassword();
//        sensorServiceTest.addSensor();
//        actuatorServiceTest.addActuator();
//        actuatorServiceTest.assignActuatorToField();
//        sensorServiceTest.assignSensorToField();
        actuatorServiceTest.unassignActuatorToField();
//        sensorServiceTest.unassignSensorToField();
//        actuatorServiceTest.removeActuator();
//        sensorServiceTest.removeSensor();
//        actuatorServiceTest.changeActuatorState();
//        sensorServiceTest.changeSensorState();
    }
}