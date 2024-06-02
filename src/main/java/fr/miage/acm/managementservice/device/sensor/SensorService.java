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

    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
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

    public Sensor changeState(Sensor sensor, DeviceState state) {
        sensor.setState(state);
        return sensorRepository.save(sensor);
    }

    public Sensor changeInterval(Sensor sensor, int interval) {
        sensor.setInterval(interval);
        return sensorRepository.save(sensor);
    }

    public void changeInterval(List<Sensor> sensors, int interval) {
        sensors.forEach(sensor -> sensor.setInterval(interval));
        sensorRepository.saveAll(sensors);
    }
}
