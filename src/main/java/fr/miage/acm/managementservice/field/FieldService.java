package fr.miage.acm.managementservice.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public void delete(Field field) {
        fieldRepository.delete(field);
    }
}
