package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.farmer.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActuatorRepository extends JpaRepository<Actuator, UUID> {

    // Delete by farmer
    void deleteByFarmer(Farmer farmer);
}
