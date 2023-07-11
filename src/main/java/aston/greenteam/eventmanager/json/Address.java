package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Address{
    public String country_code;
    public String formatted;
    @JsonProperty("Components")
    public ArrayList<Component> components;
}
