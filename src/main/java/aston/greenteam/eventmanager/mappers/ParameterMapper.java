package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import org.springframework.stereotype.Component;

@Component
public class ParameterMapper {
    public ParameterDTO parameterToParameterDTO(Parameter parameter) {
        return ParameterDTO.builder()
                .id(parameter.getId())
                .name(parameter.getName())
                .products(parameter.getProducts())
                .values(parameter.getValues())
                .build();
    }
}
