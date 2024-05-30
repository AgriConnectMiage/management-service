package fr.miage.acm.managementservice.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldServiceTest {
    private final FieldService fieldService;

    @Autowired
    public FieldServiceTest(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    public void getFields() {
        System.out.println("Fields: " + fieldService.findAll());
    }
}
