package fr.miage.acm.managementservice.device;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum DeviceState {
    NOT_ASSIGNED("not assigned"),
    OFF("off"),
    ON("on");

    private final String state;

    DeviceState(String state) {
        this.state = state;
    }
}
