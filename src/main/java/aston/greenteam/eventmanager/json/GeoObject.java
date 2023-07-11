package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoObject{
    public MetaDataProperty metaDataProperty;
    public String name;
    public String description;
    public BoundedBy boundedBy;
    @JsonProperty("Point")
    public Point point;
}
