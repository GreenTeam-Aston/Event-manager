package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{
    @JsonProperty("GeoObjectCollection")
    public GeoObjectCollection geoObjectCollection;
}
