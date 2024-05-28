package fr.miage.acm.managementservice.field;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import java.util.UUID;

public interface FieldRepository extends Neo4jRepository<Field, UUID> {
}
