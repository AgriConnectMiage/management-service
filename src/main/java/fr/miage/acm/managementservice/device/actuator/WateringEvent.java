package fr.miage.acm.managementservice.device.actuator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.ws.rs.container.PreMatching;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class WateringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public WateringEvent() {
        // Default constructor required by JPA
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
