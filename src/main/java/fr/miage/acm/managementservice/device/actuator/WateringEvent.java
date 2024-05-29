package fr.miage.acm.managementservice.device.actuator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
@Node
public class WateringEvent {
    @Id
    @GeneratedValue
    private UUID id;
    private Timestamp beginDate;
    private Timestamp endDate;
    private float duration;
    private float humidityThreshold;

    public WateringEvent(Timestamp beginDate, Timestamp endDate, float duration, float humidityThreshold) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.duration = duration;
        this.humidityThreshold = humidityThreshold;
    }

    @Override
    public String toString() {
        return "WateringEvent{" +
                "id=" + id +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", humidityThreshold=" + humidityThreshold +
                '}';
    }
}
