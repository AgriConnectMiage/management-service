package fr.miage.acm.managementservice.api;

import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApiField {

    public UUID id;
    public Integer xcoord;
    public Integer ycoord;
    public ApiFarmer farmer;

    public ApiField(Field field) {
        this.id = field.getId();
        this.xcoord = field.getXcoord();
        this.ycoord = field.getYcoord();
        this.farmer = new ApiFarmer(field.getFarmer());
    }
}
