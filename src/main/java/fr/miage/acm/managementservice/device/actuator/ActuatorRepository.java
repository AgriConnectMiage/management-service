package fr.miage.acm.managementservice.device.actuator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActuatorRepository extends JpaRepository<Actuator, UUID> {
}
