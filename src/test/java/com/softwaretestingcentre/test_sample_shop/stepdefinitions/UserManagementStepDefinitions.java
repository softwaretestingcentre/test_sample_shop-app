package com.softwaretestingcentre.test_sample_shop.stepdefinitions;

import com.softwaretestingcentre.test_sample_shop.helpers.UserManagement;
import com.softwaretestingcentre.test_sample_shop.helpers.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class UserManagementStepDefinitions {

    @Given("{actor} creates a user account")
    public void createsAUserAccount(Actor actor) {
        actor.wasAbleTo(Open.browserOn().the(LandingPage.class));
        actor.wasAbleTo(Click.on(LandingPage.NAVBAR_LINK.of("Create User")));
    }

    @When("{actor} enters {string} and {string} login details")
    public void entersLoginDetails(Actor actor, String username, String userPassword) {
        actor.attemptsTo(UserManagement.createUser(username, userPassword));
    }

    @Then("A new account is created for {actor}")
    public void aNewAccountIsCreated(Actor actor) {
        actor.attemptsTo(UserManagement.checkUserWasCreated());
    }

}
