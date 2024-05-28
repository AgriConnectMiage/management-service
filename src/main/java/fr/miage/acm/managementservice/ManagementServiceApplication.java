package fr.miage.acm.managementservice;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.farmer.FarmerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class ManagementServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ManagementServiceApplication.class, args);

        FarmerService farmerService = context.getBean(FarmerService.class);

//        Farmer farmer = new Farmer("Jean", "Dupont",
//                "jean.dupont@gmail.com","password", 3);
//
//        farmerService.save(farmer);

    }
}
