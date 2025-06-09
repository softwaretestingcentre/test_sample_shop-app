package com.softwaretestingcentre.test_sample_shop.stepdefinitions;

import com.softwaretestingcentre.test_sample_shop.helpers.UserManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

public class UserManagementStepDefinitions {

    @Given("{actor} creates a user account")
    public void createsAUserAccount(Actor actor) {
        actor.wasAbleTo(UserManagement.openNewUserForm());
    }

    @When("{actor} enters {string} and {string} login details")
    public void entersLoginDetails(Actor actor, String username, String userPassword) {
        actor.wasAbleTo(UserManagement.createUser(username, userPassword));
    }

    @Then("A new account is created for {actor}")
    public void aNewAccountIsCreated(Actor actor) {
        actor.attemptsTo(UserManagement.checkUserWasCreated());
    }

}
