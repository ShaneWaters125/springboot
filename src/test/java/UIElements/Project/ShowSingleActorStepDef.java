package UIElements.Project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ScenarioScope
public class ShowSingleActorStepDef {

    @Autowired
    ActorRepository actorRepository;

    int actorid;
    Actor actor;
//    Actor actor = mock(Actor.class);


    @Given("an actor exists with id {int}")
    public void an_actor_exists_with_id(int id){
        this.actorid = id;
        this.actorRepository.findById(actorid).orElseThrow(() -> new ResourceAccessException("Actor with ID: " + actorid + " does not exist."));
    }

    @When("i request an actors details")
    public void i_request_an_actors_details(){
        actor = this.actorRepository.findById(actorid).orElseThrow(() -> new ResourceAccessException("Actor with ID: " + actorid + " does not exist."));
        Assertions.assertNotEquals(null, actor, "Actor was not retrieved");
    }

    @Then("the webpage should show the actors {string} and {string}")
    public void the_webpage_should_show_the_actors_firstname_and_lastname(String firstname, String lastname){
        String correctdetails = firstname + " " + lastname;
        String testdetails = actor.getFirstName() + " " + actor.getLastName();
        Assertions.assertEquals(correctdetails, testdetails, "Actor details do not match");
    }

//    @Then("the webpage should show the actors {string} and {string}")
//    public void the_webpage_should_show_the_actors_firstname_and_lastname(String firstname, String lastname){
//        String correctdetails = firstname + " " + lastname;
//        String testdetails = "";
//
//        Actor testActor = mock(Actor.class);
//        when(testActor.getFirstName()).thenReturn(firstname);
//        when(testActor.getLastName()).thenReturn(lastname);
//
//        testdetails += testActor.getFirstName() + " ";
//        testdetails += testActor.getLastName();
//
//
//        Assertions.assertEquals(correctdetails, testdetails, "Actor details do not match");
//    }

}
