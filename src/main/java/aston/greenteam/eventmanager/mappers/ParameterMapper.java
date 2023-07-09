//package aston.greenteam.eventmanager.mappers;
//
//import aston.greenteam.eventmanager.dtos.ParameterDTO;
//import aston.greenteam.eventmanager.entities.Parameter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ParameterMapper {
//    public ParameterDTO parameterToParameterDTO(Parameter parameter) {
//        return ParameterDTO.builder()
//                .id(parameter.getId())
//                .name(parameter.getName())
//                .products(parameter.getProducts())
//                .values(parameter.getValues())
//                .build();
//    }
//}

package aston.greenteam.eventmanager.mappers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ValueMapper.class, ProductMapper.class})
public interface ParameterMapper {

    ParameterMapper INSTANCE = Mappers.getMapper(ParameterMapper.class); //for test

    ParameterDTO parameterToParameterDTO(Parameter parameter);

}
