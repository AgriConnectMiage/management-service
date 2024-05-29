package fr.miage.acm.managementservice;


import fr.miage.acm.managementservice.farmer.FarmerService;
import fr.miage.acm.managementservice.farmer.FarmerServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class ManagementServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ManagementServiceApplication.class, args);

        FarmerServiceTest farmerServiceTest = context.getBean(FarmerServiceTest.class);

//        farmerServiceTest.removeFarmers();
        farmerServiceTest.createFarmers();
//        farmerServiceTest.editPassword();
//        farmerServiceTest.addActuator();
//        farmerServiceTest.addSensor();
//        farmerServiceTest.removeSensor();
//        farmerServiceTest.removeActuator();
//         farmerServiceTest.assignActuatorToField();
    }
}