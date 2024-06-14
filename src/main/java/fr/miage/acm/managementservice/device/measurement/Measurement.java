package fr.miage.acm.managementservice.device.measurement;

import fr.miage.acm.managementservice.device.Device;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Measurement {

    private UUID id;

    private LocalDateTime dateTime;

    private UUID farmerId;
    private String fieldCoord;
    private UUID deviceId;

    private Float humidity;
    private Float temperature;
    private Float wateringDuration;

    public Measurement(UUID id, LocalDateTime dateTime, Device device, Float humidity, Float temperature, Float wateringDuration) {
        this.id = id;
        this.dateTime = dateTime;
        this.deviceId = device.getId();
        this.farmerId = device.getFarmer().getId();
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
                ", sourceId=" + deviceId +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", duration=" + wateringDuration +
                '}';
    }
}
