package com.tbleier.essensplanung.acceptanceTests.steps;

import com.tbleier.essensplanung.acceptanceTests.AcceptanceTestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

//We are at test implementation Layer now (presumably) - here the driver classes should be called
//Maybe each given / when / then deserves its own class? Think about reuse maybe
public class HelloWorldStep extends AcceptanceTestContext {

    private boolean worldIsHappy = false;

    @Given("^the world exists$")
    public void worldExists() {
        //yeah well, nothing to do here
    }

    @When("^a hello is said$")
    public void aHelloIsSaid() {
        System.out.println("HELLOOOOOOO!");
        worldIsHappy = true;
    }

    @Then("^the world should be happy$")
    public void assert_that_the_world_should_be_happy() {
        Assertions.assertTrue(worldIsHappy, "We all want that don't we :)");
    }

}
