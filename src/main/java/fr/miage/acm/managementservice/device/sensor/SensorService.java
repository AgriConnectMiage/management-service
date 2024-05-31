package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.farmer.Farmer;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findById(UUID id) {
        return sensorRepository.findById(id);
    }

    public List<Sensor> findByFarmer(Farmer farmer) {
        return sensorRepository.findByFarmer(farmer);
    }

    public void delete(Sensor sensor) {
        sensorRepository.delete(sensor);
    }

    public Sensor addSensor(Farmer farmer) {
        Sensor sensor = new Sensor(farmer);
        return sensorRepository.save(sensor);
    }

    @Transactional
    public void removeSensorsByFarmer(Farmer farmer) {
        sensorRepository.deleteByFarmer(farmer);
    }

    public Sensor assignSensorToField(Sensor sensor, Field field) {
        sensor.setField(field);
        sensor.setState(DeviceState.OFF);
        return sensorRepository.save(sensor);
    }

    public Sensor unassignSensorFromField(Sensor sensor) {
        sensor.setField(null);
        sensor.setState(DeviceState.NOT_ASSIGNED);
        return sensorRepository.save(sensor);
    }

    public Sensor changeSensorState(Sensor sensor, DeviceState newState) {
        if ((newState == DeviceState.OFF || newState == DeviceState.ON) && sensor.getField() == null) {
            throw new IllegalStateException("Cannot change state to " + newState + " of actuator without field");
        }
        if (newState == DeviceState.NOT_ASSIGNED && sensor.getField() != null) {
            throw new IllegalStateException("Cannot change state to " + newState + " of actuator assigned to a field");
        }
        sensor.setState(newState);
        sensorRepository.save(sensor);
        return sensor;
    }


}
