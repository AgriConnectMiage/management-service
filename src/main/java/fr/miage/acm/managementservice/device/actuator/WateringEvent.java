package fr.miage.acm.managementservice.device.actuator; 

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class WateringEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    // Duration in seconds
    private float duration;
    private float humidityThreshold;

    public WateringEvent(LocalDateTime beginDate, LocalDateTime endDate, float humidityThreshold) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.duration = Timestamp.valueOf(endDate).getTime() - Timestamp.valueOf(beginDate).getTime();
        this.humidityThreshold = humidityThreshold;
    }

    public WateringEvent(LocalDateTime beginDate, LocalDateTime endDate) {
        this.beginDate = beginDate;
        this.duration = Timestamp.valueOf(endDate).getTime() - Timestamp.valueOf(beginDate).getTime();
        this.endDate = endDate;
    }

    public WateringEvent(LocalDateTime beginDate, float duration, float humidityThreshold) {
        this.beginDate = beginDate;
        this.duration = duration;
        this.endDate = beginDate.plusSeconds((long) duration);
        this.humidityThreshold = humidityThreshold;
    }

    public WateringEvent(LocalDateTime beginDate, float duration) {
        this.beginDate = beginDate;
        this.duration = duration;
        this.endDate = beginDate.plusSeconds((long) duration);
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
