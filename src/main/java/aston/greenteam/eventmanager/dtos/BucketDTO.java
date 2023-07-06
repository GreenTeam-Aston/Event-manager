package aston.greenteam.eventmanager.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO {

    private Long id;
    private String name;
    private BigDecimal price;
}
