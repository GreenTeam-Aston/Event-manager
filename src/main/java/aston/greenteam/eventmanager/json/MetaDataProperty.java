package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaDataProperty{
    @JsonProperty("GeocoderResponseMetaData")
    public GeocoderResponseMetaData geocoderResponseMetaData;
    @JsonProperty("GeocoderMetaData") 
    public GeocoderMetaData geocoderMetaData;
}
