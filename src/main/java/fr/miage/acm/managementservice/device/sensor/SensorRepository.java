package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    void deleteByFarmer(Farmer farmer);

    List<Sensor> findByFarmer(Farmer farmer);

    List<Sensor> findAllByIdIn(List<UUID> ids);

    // find by field
    Optional<Sensor> findByField(Field field);
}
