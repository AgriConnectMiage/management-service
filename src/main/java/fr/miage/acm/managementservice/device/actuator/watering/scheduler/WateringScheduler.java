package fr.miage.acm.managementservice.device.actuator.watering.scheduler;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class WateringScheduler {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "actuator_id", referencedColumnName = "id")
    private Actuator actuator;

    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    // Duration in seconds
    private float duration;

    @Column(nullable = true)
    private Integer humidityThreshold;


    public WateringScheduler(LocalDateTime beginDate, LocalDateTime endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.duration = (endDate != null && beginDate != null) ? Timestamp.valueOf(endDate).getTime() - Timestamp.valueOf(beginDate).getTime() : 0;
    }

    public WateringScheduler(LocalDateTime beginDate, float duration) {
        this.beginDate = beginDate;
        this.duration = duration;
        this.endDate = beginDate.plusSeconds((long) duration);
    }

    public WateringScheduler(Integer humidityThreshold, float duration) {
        this.humidityThreshold = humidityThreshold;
        this.duration = duration;
    }

    public WateringScheduler() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "WateringScheduler{" +
                "id=" + id +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", humidityThreshold=" + humidityThreshold +
                '}';
    }
}
