package aston.greenteam.eventmanager.dtos;

import aston.greenteam.eventmanager.entities.Bucket;
import aston.greenteam.eventmanager.entities.Parameter;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO {

    private Long id;

    private String title;

    private String description;

    private String image;

    private BigDecimal price;

    private Integer quantity;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    private List<Bucket> buckets;

    private Set<Parameter> parameters=new HashSet<>();

}
