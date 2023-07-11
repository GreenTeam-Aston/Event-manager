package aston.greenteam.eventmanager.dtos;

import lombok.Data;
@Data
public class CoordinatesDTO {
    private String longitude;
    private String latitude;

    public CoordinatesDTO(String coord) {
        String[] split = coord.split(" ");
        this.longitude = split[0];
        this.latitude = split[1];
    }
}
