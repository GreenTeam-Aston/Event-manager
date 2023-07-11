package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.exceptions.MyEntityNotFoundException;
import aston.greenteam.eventmanager.mappers.ValueMapper;
import aston.greenteam.eventmanager.repositories.ParametersRepository;
import aston.greenteam.eventmanager.repositories.ValuesRepository;
import aston.greenteam.eventmanager.services.ValuesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ValuesServiceImpl implements ValuesService {

    private final ValuesRepository valuesRepository;

    private final ParametersRepository parametersRepository;

    private final ValueMapper mapper;

    @Override
    public ValueDTO findById(Long id) {
        return valuesRepository.findById(id)
                .map(mapper::valueToValueDTO)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    public List<ValueDTO> findAll() {
        return valuesRepository.findAll()
                .stream()
                .map(value -> {
                    ValueDTO valueDTO = mapper.valueToValueDTO(value);
                    return valueDTO;
                }).collect(Collectors.toList());
    }
    @Override
    public ValueDTO createValue(ValueDTO valueDTO){

        Value newValue=new Value();
        newValue.setName(valueDTO.getName());
        valuesRepository.save(newValue);

        return mapper.valueToValueDTO(newValue);
    }

    @Override
    public ValueDTO updateValue(Value value) {
        valuesRepository.save(value);
        return mapper.valueToValueDTO(value);
    }

    @Override
    public void deleteValue(Long id) {
        valuesRepository.deleteById(id);
    }
}