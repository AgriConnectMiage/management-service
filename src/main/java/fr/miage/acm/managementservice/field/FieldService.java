package fr.miage.acm.managementservice.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    public void delete(Field field) {
        fieldRepository.delete(field);
    }
}
