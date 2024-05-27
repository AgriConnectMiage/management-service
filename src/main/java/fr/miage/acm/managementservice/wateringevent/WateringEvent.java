package fr.miage.acm.managementservice.wateringevent;

import java.util.UUID;
import java.sql.Timestamp;

public class WateringEvent {
    private UUID id;
    private Timestamp beginDate;
    private Timestamp endDate;
    private float duration;
    private float humidityThreshold;

    public WateringEvent(UUID id, Timestamp beginDate, Timestamp endDate, float duration, float humidityThreshold) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.duration = duration;
        this.humidityThreshold = humidityThreshold;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getHumidityThreshold() {
        return humidityThreshold;
    }

    public void setHumidityThreshold(float humidityThreshold) {
        this.humidityThreshold = humidityThreshold;
    }
}
