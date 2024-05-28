package fr.miage.acm.managementservice.device;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
public class Measurement {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime dateTime;
    private UUID deviceId;

    @Relationship("MEASURED_BY")
    private Device source;

    private Float humidity; // Using Float to allow null values
    private Float temperature; // Using Float to allow null values
    private Float duration; // Using Float to allow null values

    public Measurement(UUID id, LocalDateTime dateTime, UUID deviceId, Device source, Float humidity, Float temperature, Float duration) {
        this.id = id;
        this.dateTime = dateTime;
        this.deviceId = deviceId;
        this.source = source;
        this.humidity = humidity;
        this.temperature = temperature;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", deviceId=" + deviceId +
                ", source=" + source +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", duration=" + duration +
                '}';
    }
}
