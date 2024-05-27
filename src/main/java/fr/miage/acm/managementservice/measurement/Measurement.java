package fr.miage.acm.managementservice.measurement;

import fr.miage.acm.managementservice.device.Device;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
public class Measurement {
    private UUID id;
    private LocalDateTime dateTime;
    private UUID deviceId;
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
}
