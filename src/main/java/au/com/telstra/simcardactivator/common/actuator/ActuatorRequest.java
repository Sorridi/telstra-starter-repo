package au.com.telstra.simcardactivator.common.actuator;

import au.com.telstra.simcardactivator.common.sim.activation.SimActivationRequest;

public class ActuatorRequest
{
    private final String iccid;

    public ActuatorRequest(SimActivationRequest request)
    {
        this.iccid = request.getIccid();
    }

    public String getIccid()
    {
        return this.iccid;
    }
}
