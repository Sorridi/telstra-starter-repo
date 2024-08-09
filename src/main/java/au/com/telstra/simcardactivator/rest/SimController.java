package au.com.telstra.simcardactivator.rest;

import au.com.telstra.simcardactivator.common.actuator.ActuatorResponse;
import au.com.telstra.simcardactivator.common.sim.SimCard;
import au.com.telstra.simcardactivator.common.sim.SimCardRepository;
import au.com.telstra.simcardactivator.common.sim.activation.SimActivationRequest;
import au.com.telstra.simcardactivator.common.sim.activation.SimActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
public class SimController
{
    private SimActivationService simActivationService;
    private SimCardRepository simCardRepository;

    @Autowired
    public void setup(SimActivationService service, SimCardRepository repository)
    {
        this.simActivationService = service;
        this.simCardRepository = repository;
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

        SimCard card = new SimCard(request, response);
        simCardRepository.save(card);

        return response;
    }

    @GetMapping("/getfrom")
    public SimCard getSimCardFrom(long simCardId)
    {
        return simCardRepository.findSimCardById(simCardId);
    }
}
