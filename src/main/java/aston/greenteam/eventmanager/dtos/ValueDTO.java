package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.Parameter;
import lombok.Data;

import java.util.Set;

@Data
public class ValueDTO {

    private Long id;

    private String name;

    private Set <Parameter> parameter;

}
