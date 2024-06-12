package fr.miage.acm.managementservice.device.measurement;

import fr.miage.acm.managementservice.device.Device;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "device_id", insertable = false, updatable = false)
    private Device device;

    @Column(columnDefinition = "NUMERIC(5,1)")
    private Float humidity;
    @Column(columnDefinition = "NUMERIC(5,1)")
    private Float temperature;
    @Column(columnDefinition = "NUMERIC(5,1)")
    private Float wateringDuration;

    public Measurement(UUID id, LocalDateTime dateTime, Device device, Float humidity, Float temperature, Float wateringDuration) {
        this.id = id;
        this.dateTime = dateTime;
        this.device = device;
        this.humidity = humidity;
        this.temperature = temperature;
        this.wateringDuration = wateringDuration;
    }

    public Measurement() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", source=" + device +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", duration=" + wateringDuration +
                '}';
    }
}
