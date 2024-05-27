package fr.miage.acm.managementservice.farmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Farmer> getFarmerById(@PathVariable UUID id) {
        return farmerService.findById(id);
    }

    @PostMapping
    public Farmer createFarmer(@RequestBody Farmer farmer) {
        return farmerService.save(farmer);
    }

    @DeleteMapping("/{id}")
    public void deleteFarmer(@PathVariable UUID id) {
        farmerService.deleteById(id);
    }
}