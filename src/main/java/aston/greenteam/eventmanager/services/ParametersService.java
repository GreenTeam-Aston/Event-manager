package aston.greenteam.eventmanager.services;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;

import java.util.List;

public interface ParametersService {

    ParameterDTO findById(Long id);

    List<ParameterDTO> findAll();

    ParameterDTO createParameter(ParameterDTO parameterDTO);

    ParameterDTO updateParameter(Parameter parameter);

    void deleteParameter(Long id);

    ParameterDTO assignValueToParameter(Long parameterId, Long valueId);

}
