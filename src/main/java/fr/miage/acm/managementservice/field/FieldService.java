package fr.miage.acm.managementservice.field;

import fr.miage.acm.managementservice.farmer.Farmer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FieldService {

    private FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    public void delete(Field field) {
        fieldRepository.delete(field);
    }

    public Optional<Field> findById(UUID id) {
        return fieldRepository.findById(id);
    }
    
    public List<Field> findByFarmer(Farmer farmer) {
        return fieldRepository.findByFarmer(farmer);
    }

    @Transactional
    public void deleteFieldsByFarmer(Farmer farmer) {
        fieldRepository.deleteByFarmer(farmer);
    }
}
