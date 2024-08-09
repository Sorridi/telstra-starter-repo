package au.com.telstra.simcardactivator.rest;

import au.com.telstra.simcardactivator.common.actuator.ActuatorResponse;
import au.com.telstra.simcardactivator.common.sim.SimCard;
import au.com.telstra.simcardactivator.common.sim.SimCardRepository;
import au.com.telstra.simcardactivator.common.sim.activation.SimActivationRequest;
import au.com.telstra.simcardactivator.common.sim.activation.SimActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/sim")
public class SimController
{
    private static final Logger logger = Logger.getLogger(SimController.class.getName());

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
        logger.log(Level.INFO, response.isSuccess() ? "Activated SIM {0}" : "Could not activate SIM {0}", part);

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
