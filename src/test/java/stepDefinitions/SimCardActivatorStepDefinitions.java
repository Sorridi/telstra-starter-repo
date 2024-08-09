package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.common.sim.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions
{
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String API_ENDPOINT = "http://localhost:8080/sim/";
    private static final String WORKING_ICCID = "1255789453849037777";
    private static final String NOT_WORKING_ICCID = "8944500102198304826";

    private SimCard simCard;

    @Given("a valid SIM Card")
    public void aValidSIMCard()
    {
        simCard = new SimCard(WORKING_ICCID, "me@example.com", false);
    }

    @When("a request is submitted for a valid SIM Card")
    public void aRequestIsSubmittedForAValidSIMCard()
    {
        restTemplate.postForEntity(API_ENDPOINT + "activate", simCard, String.class);
    }

    @Then("the SIM Card gets activated and the outcome is saved in the database")
    public void theSIMCardGetsActivatedAndTheOutcomeIsSavedInTheDatabase()
    {
        String url = API_ENDPOINT + "getfrom?simCardId=1";
        assert restTemplate.getForObject(url, Boolean.class);
    }

    @Given("an invalid SIM Card")
    public void anInvalidSIMCard()
    {
        simCard = new SimCard(NOT_WORKING_ICCID, "other@example.com", false);
    }

    @When("a request is submitted for an invalid SIM Card")
    public void aRequestIsSubmittedForAnInvalidSIMCard()
    {
        restTemplate.postForEntity(API_ENDPOINT + "activate", simCard, String.class);
    }

    @Then("the SIM Card does not get activated and the outcome is saved in the database")
    public void theSIMCardDoesNotGetActivatedAndTheOutcomeIsSavedInTheDatabase()
    {
        String url = API_ENDPOINT + "getfrom?simCardId=2";
        assert !restTemplate.getForObject(url, Boolean.class);
    }
}