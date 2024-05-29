package fr.miage.acm.managementservice.farmer;

import fr.miage.acm.managementservice.device.actuator.Actuator;
import fr.miage.acm.managementservice.device.sensor.Sensor;
import fr.miage.acm.managementservice.field.Field;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer fieldSize;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Field> fields;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Sensor> sensors;

    @OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Actuator> actuators;

    public Farmer(String firstName, String lastName, String email, String password, int fieldSize) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sensors = new ArrayList<>();
        this.actuators = new ArrayList<>();
        this.fieldSize = fieldSize;
        this.fields = new ArrayList<>();
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                Field field = new Field(x + 1, y + 1);
                field.setFarmer(this);
                this.fields.add(field);
            }
        }
    }

    public Farmer() {
        // Default constructor required by JPA
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fieldSize=" + fieldSize +
                ", fields=" + fields +
                ", sensors=" + sensors +
                ", actuators=" + actuators +
                '}';
    }
}
