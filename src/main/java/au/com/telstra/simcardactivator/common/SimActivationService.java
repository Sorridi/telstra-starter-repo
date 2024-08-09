package au.com.telstra.simcardactivator.common;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimActivationService
{
    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";

    private final RestTemplate restTemplate = new RestTemplate();

    public ActuatorResponse activate(SimActivationRequest activationRequest)
    {
        ActuatorRequest actuatorRequest = new ActuatorRequest(activationRequest);
        return restTemplate.postForObject(ACTUATOR_URL, actuatorRequest, ActuatorResponse.class);
    }
}
