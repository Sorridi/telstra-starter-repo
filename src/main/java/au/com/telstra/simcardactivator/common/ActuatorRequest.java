package au.com.telstra.simcardactivator.common;

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
