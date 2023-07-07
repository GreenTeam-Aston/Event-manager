package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParameterDTO {

    private Long id;

    private String name;

    private Set<ProductDTO> products = new HashSet<>();

    private Set<ValueDTO> values = new HashSet<>();
}
