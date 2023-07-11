package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Locality{
    @JsonProperty("LocalityName")
    public String localityName;
    @JsonProperty("Thoroughfare") 
    public Thoroughfare thoroughfare;
}
