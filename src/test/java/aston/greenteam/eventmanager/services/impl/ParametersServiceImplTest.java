package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.mappers.ParameterMapper;
import aston.greenteam.eventmanager.mappers.ValueMapper;
import aston.greenteam.eventmanager.repositories.ParametersRepository;
import aston.greenteam.eventmanager.repositories.ValuesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParametersServiceImplTest {


    @Mock
    private ParametersRepository parametersRepository;

    @Mock
    private ValuesRepository valuesRepository;

    @Mock
    private ParameterMapper parameterMapper;

    @Mock
    private ValueMapper valueMapper;

    @InjectMocks
    private ParametersServiceImpl parametersServiceImpl;

    private ParameterDTO parameterDTO;

    private Parameter parameter;

    private Value value;

    @BeforeEach
    public void setUp() {

        parametersServiceImpl = new ParametersServiceImpl(parametersRepository, valuesRepository, parameterMapper, valueMapper);

        parameter = new Parameter();
        parameter.setId(1L);
        parameter.setName("parameter");

        parameterDTO = new ParameterDTO();
        parameterDTO.setId(1L);
        parameterDTO.setName("parameterDTO");

        value = new Value();
        value.setId(2L);
        value.setName("value");

    }

    @Test
    public void testFindById() {
        parametersRepository.findById(parameter.getId());
        Mockito.verify(parametersRepository).findById(parameter.getId());
    }

    @Test
    public void testFindAll() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter);

        List<ParameterDTO> parameterDTOS = new ArrayList<>();
        parameterDTOS.add(parameterDTO);

        when(parametersRepository.findAll()).thenReturn(parameters);
        when(parameterMapper.parameterToParameterDTO(parameter)).thenReturn(parameterDTO);

        List<ParameterDTO> results = parametersServiceImpl.findAll();

        Assertions.assertEquals(results.size(), 1);
        Assertions.assertEquals(results.get(0).getId(), parameterDTOS.get(0).getId());
        Assertions.assertEquals(results.get(0).getName(), parameterDTOS.get(0).getName());
    }

    @Test
    public void testCreateParameter() {
        when(parameterMapper.parameterToParameterDTO(any(Parameter.class))).thenReturn(parameterDTO);
        when(parametersRepository.save(any(Parameter.class))).thenReturn(parameter);
        ParameterDTO result = parametersServiceImpl.createParameter(parameterDTO);
        Assertions.assertEquals(result.getId(), parameterDTO.getId());
        Assertions.assertEquals(result.getName(), parameterDTO.getName());
    }

    @Test
    public void testUpdateParameter() {
        when(parameterMapper.parameterToParameterDTO(parameter)).thenReturn(parameterDTO);
        when(parametersRepository.save(parameter)).thenReturn(parameter);

        ParameterDTO result = parametersServiceImpl.updateParameter(parameter);

        Assertions.assertEquals(result.getId(), parameterDTO.getId());
        Assertions.assertEquals(result.getName(), parameterDTO.getName());
    }

    @Test
    void deleteParameter() {
        doNothing().when(parametersRepository).deleteById(anyLong());

        parametersServiceImpl.deleteParameter(1L);
    }

    @Test
    public void testAssignValueToParameter() {
        Set<Value> values = new HashSet<>();
        values.add(value);

        when(parametersRepository.findById(anyLong())).thenReturn(Optional.of(parameter));
        when(valuesRepository.findById(anyLong())).thenReturn(Optional.of(value));
        when(parameterMapper.parameterToParameterDTO(parameter)).thenReturn(parameterDTO);

        ParameterDTO result = parametersServiceImpl.assignValueToParameter(1L, 2L);

        Assertions.assertEquals(result.getId(), parameterDTO.getId());
        Assertions.assertEquals(result.getName(), parameterDTO.getName());
    }

}