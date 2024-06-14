package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.farmer.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldServiceTest {
    private final FieldService fieldService;

    public FieldServiceTest(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public void getFields() {
        System.out.println("Fields: " + fieldService.findAll());
    }

    public Field getFieldOfFarmer(Farmer farmer) {
        return fieldService.findByFarmer(farmer).get(0);
    }

}
