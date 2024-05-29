package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.farmer.Farmer;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer xcoord;
    private Integer ycoord;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Sensor> sensors = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actuator_id", referencedColumnName = "id")
    private Actuator actuator;

    public Field(Integer xcoord, Integer ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.sensors = new ArrayList<>();
        this.actuator = null;
    }

    public Field() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", xcoord=" + xcoord +
                ", ycoord=" + ycoord +
                '}';
    }
}
