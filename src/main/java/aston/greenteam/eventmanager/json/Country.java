package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country{
    @JsonProperty("AddressLine")
    public String addressLine;
    @JsonProperty("CountryNameCode") 
    public String countryNameCode;
    @JsonProperty("CountryName") 
    public String countryName;
    @JsonProperty("AdministrativeArea") 
    public AdministrativeArea administrativeArea;
}
