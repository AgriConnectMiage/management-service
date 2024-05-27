package fr.miage.acm.managementservice.device;

public enum DeviceState {
    NOT_ASSIGNED("not assigned"),
    OFF("off"),
    ON("on");

    private final String state;

    DeviceState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
