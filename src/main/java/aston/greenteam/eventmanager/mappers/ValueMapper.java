package aston.greenteam.eventmanager.mappers;


import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueMapper {
    public ValueDTO valueToValueDTO(Value value) {
        return ValueDTO.builder()
                .id(value.getId())
                .name(value.getName())
                .build();
    }
}
