package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocoderMetaData{
    public String precision;
    public String text;
    public String kind;
    @JsonProperty("Address")
    public Address address;
    @JsonProperty("AddressDetails") 
    public AddressDetails addressDetails;
}
