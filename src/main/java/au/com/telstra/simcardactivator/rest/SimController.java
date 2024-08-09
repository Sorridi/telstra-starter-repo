package au.com.telstra.simcardactivator.rest;

import au.com.telstra.simcardactivator.common.ActuatorRequest;
import au.com.telstra.simcardactivator.common.ActuatorResponse;
import au.com.telstra.simcardactivator.common.SimActivationRequest;
import au.com.telstra.simcardactivator.common.SimActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimController
{
    private SimActivationService simActivationService;

    @Autowired
    public void setupSimActivationService(SimActivationService service)
    {
        this.simActivationService = service;
    }

    @PostMapping(value = "/activate", consumes = "application/json", produces = "application/json")
    public ActuatorResponse activateSim(@RequestBody SimActivationRequest request)
    {
        String part = "with ICCID `" + request.getIccid() + "`, customer email `" + request.getCustomerEmail() + "`";

        ActuatorResponse response = simActivationService.activate(request);

        if (response.isSuccess())
        {
            System.out.println("Activated SIM " + part);
        }
        else
        {
            System.out.println("Could not activate SIM " + part);
        }

        return response;
    }
}
