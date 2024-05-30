package fr.miage.acm.managementservice.device;

import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.farmer.Farmer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DeviceState state;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;


    public Device(Farmer farmer) {
        this.state = DeviceState.NOT_ASSIGNED;
        this.farmer = farmer;
    }

    public Device() {
        // Default constructor required by JPA
    }
}
