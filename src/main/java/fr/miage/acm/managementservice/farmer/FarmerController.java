package fr.miage.acm.managementservice.farmer;

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
    public List<Farmer> getAllFarmers() {
        return farmerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Farmer> getFarmerById(@PathVariable UUID id) {
        return farmerService.findById(id);
    }

    @GetMapping("/email")
    public Farmer getFarmerByEmail(@RequestParam String email) {
        return farmerService.findByEmail(email);
    }

    @PostMapping
    public Farmer createFarmer(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam int fieldSize) {
        return farmerService.createFarmer(firstName, lastName, email, password, fieldSize);
    }

    @PutMapping("/{id}/password")
    public void updateFarmerPassword(@PathVariable UUID id, @RequestParam String password) {
        farmerService.editPassword(id, password);
    }

    @DeleteMapping("/{id}")
    public void deleteFarmerById(@PathVariable UUID id) {
        farmerService.deleteById(id);
    }


    @DeleteMapping("")
    public void removeFarmer(@RequestBody Farmer farmer) {
        farmerService.removeFarmer(farmer);
    }
}
