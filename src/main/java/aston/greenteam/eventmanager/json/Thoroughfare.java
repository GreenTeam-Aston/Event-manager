package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Thoroughfare{
    @JsonProperty("ThoroughfareName")
    public String thoroughfareName;
    @JsonProperty("Premise") 
    public Premise premise;
}
