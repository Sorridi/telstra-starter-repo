package au.com.telstra.simcardactivator.common.sim;

import au.com.telstra.simcardactivator.common.actuator.ActuatorResponse;
import au.com.telstra.simcardactivator.common.sim.activation.SimActivationRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String iccid;
    private String customerEmail;
    private boolean active;

    public SimCard() { }

    public SimCard(String iccid, String customerEmail, boolean active)
    {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public SimCard(SimActivationRequest request, ActuatorResponse response)
    {
        this(request.getIccid(), request.getCustomerEmail(), response.isSuccess());
    }

    public Long getId()
    {
        return id;
    }

    public String getIccid()
    {
        return iccid;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public boolean isActive()
    {
        return active;
    }
}
