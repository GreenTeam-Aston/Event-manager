package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.services.impl.ValuesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValueControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ValuesServiceImpl valuesServiceImpl;

    @BeforeEach
    public void setUp() {

        mockMvc = MockMvcBuilders.
                standaloneSetup(new ValueController(valuesServiceImpl)).build();
    }

    @Test
    void findByValueId() throws Exception {
        ValueDTO valueDTO = new ValueDTO();
        valueDTO.setId(1L);
        when(valuesServiceImpl.findById(1L)).thenReturn(valueDTO);
        mockMvc.perform(get("/api/v1/product/parameter/value/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void findAll() throws Exception {
        ValueDTO valueDTO = new ValueDTO();
        valueDTO.setId(1L);
        List<ValueDTO> valueDTOList = new ArrayList<>();
        valueDTOList.add(valueDTO);
        when(valuesServiceImpl.findAll()).thenReturn(valueDTOList);
        mockMvc.perform(get("/api/v1/product/parameter/value"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        ValueDTO valueDTO = new ValueDTO();
        valueDTO.setId(1L);
        valueDTO.setName("test");

        when(valuesServiceImpl.createValue(any(ValueDTO.class))).thenReturn(valueDTO);
        mockMvc.perform(post("/api/v1/product/parameter/value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(valueDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    void update() throws Exception {
        Value value = new Value();
        value.setId(1L);
        value.setName("Value");

        ValueDTO valueDTO = new ValueDTO();
        valueDTO.setId(1L);
        valueDTO.setName("updatedValue");

        when(valuesServiceImpl.updateValue(any(Value.class))).thenReturn(valueDTO);
        mockMvc.perform(put("/api/v1/product/parameter/value")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(value)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("updatedValue"));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(valuesServiceImpl).deleteValue(anyLong());

        mockMvc.perform(delete("/api/v1/product/parameter/value/1"))
                .andExpect(status().isOk());
    }
}