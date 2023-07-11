package aston.greenteam.eventmanager.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoundedBy{
    @JsonProperty("Envelope")
    public Envelope envelope;
}
