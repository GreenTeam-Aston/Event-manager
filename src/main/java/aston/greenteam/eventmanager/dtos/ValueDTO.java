package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValueDTO {

    private Long id;

    private String name;

    private Set <Parameter> parameter;

}
