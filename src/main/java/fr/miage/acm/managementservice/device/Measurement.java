package fr.miage.acm.managementservice.device;

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

    private Float humidity; // Using Float to allow null values
    private Float temperature; // Using Float to allow null values
    private Float duration; // Using Float to allow null values

    public Measurement(UUID id, LocalDateTime dateTime, Device device, Float humidity, Float temperature, Float duration) {
        this.id = id;
        this.dateTime = dateTime;
        this.device = device;
        this.humidity = humidity;
        this.temperature = temperature;
        this.duration = duration;
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
                ", duration=" + duration +
                '}';
    }
}
