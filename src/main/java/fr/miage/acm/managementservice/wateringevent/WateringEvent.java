package fr.miage.acm.managementservice.wateringevent;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
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
}
