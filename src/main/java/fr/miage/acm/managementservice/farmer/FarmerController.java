package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.api.ApiFarmer;
import fr.miage.acm.managementservice.form.FarmerBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    @Autowired
    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @GetMapping
    public List<ApiFarmer> getAllFarmers() {
        return farmerService.findAll().stream()
                .map(ApiFarmer::new)
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<ApiFarmer> getFarmerById(@PathVariable UUID id) {
        return farmerService.findById(id).map(ApiFarmer::new);
    }

    @GetMapping("/email")
    public ApiFarmer getFarmerByEmail(@RequestParam String email) {
        return new ApiFarmer(farmerService.findByEmail(email));
    }

    @PostMapping
    public Farmer createFarmer(@RequestBody FarmerBody farmerBody) {
        return farmerService.createFarmer(farmerBody.firstName, farmerBody.lastName, farmerBody.email, farmerBody.password, farmerBody.fieldSize);
    }

    @PutMapping("/{id}/password")
    public void updateFarmerPassword(@PathVariable UUID id, @RequestParam String password) {
        farmerService.editPassword(id, password);
    }

    @DeleteMapping("/{id}")
    public void deleteFarmerById(@PathVariable UUID id) {
        farmerService.removeFarmerById(id);
    }

}
