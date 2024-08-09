package au.com.telstra.simcardactivator.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActuatorResponse
{
    private final boolean success;

    @JsonCreator
    public ActuatorResponse(@JsonProperty("success") boolean success)
    {
        this.success = success;
    }

    public boolean isSuccess()
    {
        return this.success;
    }
}
