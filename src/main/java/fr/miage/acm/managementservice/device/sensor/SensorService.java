package fr.miage.acm.managementservice.device.sensor;

import fr.miage.acm.managementservice.device.DeviceState;
import fr.miage.acm.managementservice.field.Field;
import fr.miage.acm.managementservice.field.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private FieldRepository fieldRepository;

    // save method
    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor insertSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Optional<Sensor> findById(UUID id) {
        return sensorRepository.findById(id);
    }

    public void delete(Sensor sensor) {
        sensorRepository.delete(sensor);
    }

    @Transactional
    public Optional<Sensor> assignSensorToField(UUID sensorId, UUID fieldId) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);
        Optional<Field> fieldOptional = fieldRepository.findById(fieldId);

        if (sensorOptional.isPresent() && fieldOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            Field field = fieldOptional.get();

            sensor.setState(DeviceState.ON);
            field.getSensors().add(sensor);
            sensorRepository.save(sensor);
            fieldRepository.save(field);
            return Optional.of(sensor);
        }
        return Optional.empty();
    }

    // Method to change the state of a sensor
    public Optional<Sensor> changeSensorState(UUID sensorId, DeviceState newState) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);

        if (sensorOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            sensor.setState(newState);
            sensorRepository.save(sensor);
            return Optional.of(sensor);
        }
        return Optional.empty();
    }

    // Method to change the interval of a sensor
    public Optional<Sensor> changeSensorInterval(UUID sensorId, float newInterval) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);

        if (sensorOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            sensor.setInterval(newInterval);
            sensorRepository.save(sensor);
            return Optional.of(sensor);
        }
        return Optional.empty();
    }

    // Remove sensor

}
