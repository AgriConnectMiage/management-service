package fr.miage.acm.managementservice.device.actuator;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActuatorRepository extends JpaRepository<Actuator, UUID> {

    void deleteByFarmer(Farmer farmer);

    List<Actuator> findByFarmer(Farmer farmer);

    Optional<Actuator> findByField(Field field);
}
