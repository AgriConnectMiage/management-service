package fr.miage.acm.managementservice.device.watering.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WateringEventRepository extends JpaRepository<WateringEvent, UUID> {
}
