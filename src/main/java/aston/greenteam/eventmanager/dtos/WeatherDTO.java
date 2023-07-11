package aston.greenteam.eventmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDTO {

    private BigDecimal temperature;

    private BigDecimal windSpeed;

    private BigDecimal humidity;

    private String description;
}
