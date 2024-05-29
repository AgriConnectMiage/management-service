package fr.miage.acm.managementservice.device.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}
