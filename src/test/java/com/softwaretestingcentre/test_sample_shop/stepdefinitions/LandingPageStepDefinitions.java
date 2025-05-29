package com.softwaretestingcentre.test_sample_shop.stepdefinitions;

import com.softwaretestingcentre.test_sample_shop.helpers.LandingPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;

public class LandingPageStepDefinitions {
    @When("{actor} opens the shop website")
    public void OpensTheShopWebsite(Actor actor) {
        actor.wasAbleTo(Open.browserOn().the(LandingPage.class));
    }

    @Then("{actor} sees the welcome banner")
    public void sheSeesTheWelcomeBanner(Actor actor) {
        Ensure.that(LandingPage.WELCOME_BANNER.resolveFor(actor).getText()).equals("Welcome to the atsea shop");
    }
}
