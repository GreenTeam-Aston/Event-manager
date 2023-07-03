package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.exceptions.MyEntityNotFoundException;
import aston.greenteam.eventmanager.mappers.ParameterMapper;
import aston.greenteam.eventmanager.repositories.ParametersRepository;
import aston.greenteam.eventmanager.repositories.ValuesRepository;
import aston.greenteam.eventmanager.services.ParametersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParametersServiceImpl implements ParametersService {

    private final ParametersRepository parametersRepository;

    private final ValuesRepository valuesRepository;

    private final ParameterMapper mapper;


    @Override
    public ParameterDTO findById(Long id) {
        return parametersRepository.findById(id)
                .map(mapper::parameterToParameterDTO)
                .orElseThrow(() -> new MyEntityNotFoundException(id));
    }

    @Override
    public List<ParameterDTO> findAll() {
        return parametersRepository.findAll()
                .stream()
                .map(parameter -> {
                    ParameterDTO parameterDTO = mapper.parameterToParameterDTO(parameter);
                    return parameterDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public ParameterDTO createParameter(ParameterDTO parameterDTO) {
        Parameter newParameter = new Parameter();
        newParameter.setName(parameterDTO.getName());
        parametersRepository.save(newParameter);

        return mapper.parameterToParameterDTO(newParameter);
    }

    @Override
    public ParameterDTO updateParameter(Parameter parameter) {
        parametersRepository.save(parameter);
        return mapper.parameterToParameterDTO(parameter);
    }

    @Override
    public void deleteParameter(Long id) {
        parametersRepository.deleteById(id);

    }

    @Override
    public ParameterDTO assignValueToParameter(Long parameterId, Long valueId) {
        Set <Value> valueSet = null;
        Parameter parameter = parametersRepository.findById(parameterId).get();
        Value value = valuesRepository.findById(valueId).get();
        valueSet = parameter.getValues();//Set value
        valueSet.add(value);
        parameter.setValues(valueSet);
        parametersRepository.save(parameter);

        return mapper.parameterToParameterDTO(parameter);
    }

}
