package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.Product;
import aston.greenteam.eventmanager.entities.Value;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ParameterDTO {

    private Long id;

    private String name;

    private Set<Product> products = new HashSet<>();

    private Set<Value> values = new HashSet<>();
}
