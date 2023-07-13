package aston.greenteam.eventmanager.mappers;


import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper (componentModel = "spring", uses = ParameterMapper.class)
public interface ValueMapper {

    ValueMapper INSTANCE = Mappers.getMapper(ValueMapper.class);//for test

    ValueDTO valueToValueDTO(Value value);
}

