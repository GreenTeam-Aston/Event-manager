package aston.greenteam.eventmanager.dtos;

import lombok.Data;

@Data
public class AddressEventDTO {
    private String streetType;
    private String streetName;
    private String homeNumber;
    private String city;

    public AddressEventDTO(String address) {
        String [] split = address.split("_");
        this.city = split[0];
        this.streetType = split[1];
        this.streetName = split[2];
        this.homeNumber = split[3];
    }
}
