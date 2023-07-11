package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdministrativeArea{
    @JsonProperty("AdministrativeAreaName")
    public String administrativeAreaName;
    @JsonProperty("Locality") 
    public Locality locality;
}
