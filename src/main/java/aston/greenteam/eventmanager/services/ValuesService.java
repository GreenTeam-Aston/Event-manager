package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ValuesService {

    ValueDTO findById(Long id);
    List<ValueDTO> findAll();

    ValueDTO createValue(ValueDTO valueDTO);

    ValueDTO updateValue (Value value);

    void deleteValue(Long id);








}
