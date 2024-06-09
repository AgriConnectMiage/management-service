package fr.miage.acm.managementservice.device.measurement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeasurementRepository extends JpaRepository<Measurement, UUID> {
}
