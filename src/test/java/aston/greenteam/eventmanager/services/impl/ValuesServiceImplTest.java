package aston.greenteam.eventmanager.services.impl;

import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValuesServiceImplTest {

    @Mock
    private ValuesRepository valuesRepository;

    @Mock
    private ParametersRepository parametersRepository;

    @Mock
    private ValueMapper mapper;

    @InjectMocks
    private ValuesServiceImpl valuesServiceImpl;

    private Value value;

    private ValueDTO valueDTO;

    @BeforeEach
    public void setUp() {

        valuesServiceImpl = new ValuesServiceImpl(valuesRepository, parametersRepository, mapper);

        value = new Value();
        value.setId(1L);
        value.setName("value");

        valueDTO = new ValueDTO();
        valueDTO.setId(1L);
        valueDTO.setName("valueDTO");

    }

    @Test
    public void testFindById() {
        valuesRepository.findById(value.getId());
        Mockito.verify(valuesRepository).findById(value.getId());

    }

    @Test
    public void testFindAll() {
        List<Value> values = new ArrayList<>();
        values.add(value);

        List<ValueDTO> valueDTOS = new ArrayList<>();
        valueDTOS.add(valueDTO);

        when(valuesRepository.findAll()).thenReturn(values);
        when(mapper.valueToValueDTO(value)).thenReturn(valueDTO);

        List<ValueDTO> results = valuesServiceImpl.findAll();

        Assertions.assertEquals(results.size(), 1);
        Assertions.assertEquals(results.get(0).getId(), valueDTOS.get(0).getId());
        Assertions.assertEquals(results.get(0).getName(), valueDTOS.get(0).getName());
    }

    @Test
    public void testCreateValue() {
        when(mapper.valueToValueDTO(any(Value.class))).thenReturn(valueDTO);
        when(valuesRepository.save(any(Value.class))).thenReturn(value);
        ValueDTO result = valuesServiceImpl.createValue(valueDTO);
        Assertions.assertEquals(result.getId(), valueDTO.getId());
        Assertions.assertEquals(result.getName(), valueDTO.getName());
    }

    @Test
    public void testUpdateValue() {
        when(mapper.valueToValueDTO(value)).thenReturn(valueDTO);
        when(valuesRepository.save(value)).thenReturn(value);

        ValueDTO result = valuesServiceImpl.updateValue(value);

        Assertions.assertEquals(result.getId(), valueDTO.getId());
        Assertions.assertEquals(result.getName(), valueDTO.getName());
    }

    @Test
    public void testDeleteValue() {
        doNothing().when(valuesRepository).deleteById(anyLong());

        valuesServiceImpl.deleteValue(1L);
    }
}