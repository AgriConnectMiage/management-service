package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.api.ApiField;
import fr.miage.acm.managementservice.farmer.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/fields")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Field> getFieldsByFarmer(@PathVariable UUID farmerId) {
        Farmer farmer = new Farmer();
        farmer.setId(farmerId);
        return fieldService.findByFarmer(farmer);
    }

}
