package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.entities.Value;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring", uses = {ValueMapper.class, ProductMapper.class })
public interface ParameterMapper {

    ParameterMapper INSTANCE = Mappers.getMapper(ParameterMapper.class); //for test

    ParameterDTO  parameterToParameterDTO(Parameter parameter);

    //Parameter ParameterDTOToParameter(ParameterDTO parameterDTO);
}
