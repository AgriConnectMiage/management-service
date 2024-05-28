package fr.miage.acm.managementservice.device.actuator;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface ActuatorRepository extends Neo4jRepository<Actuator, UUID> {
}
