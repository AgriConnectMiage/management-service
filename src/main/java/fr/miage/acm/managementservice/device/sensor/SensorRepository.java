package fr.miage.acm.managementservice.device.sensor;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.UUID;

public interface SensorRepository extends Neo4jRepository<Sensor, UUID> {
}
