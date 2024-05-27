package fr.miage.acm.managementservice.measurement;

import fr.miage.acm.managementservice.device.Device;
import java.util.UUID;
import java.time.LocalDateTime;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public Device getSource() {
        return source;
    }

    public void setSource(Device source) {
        this.source = source;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }
}
