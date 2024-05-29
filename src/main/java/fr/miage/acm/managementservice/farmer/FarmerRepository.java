package fr.miage.acm.managementservice.farmer;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface FarmerRepository extends Neo4jRepository<Farmer, UUID> {

    Farmer findByEmail(String email);
}
