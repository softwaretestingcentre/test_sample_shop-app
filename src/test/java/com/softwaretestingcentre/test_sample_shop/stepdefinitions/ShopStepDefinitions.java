package com.softwaretestingcentre.test_sample_shop.stepdefinitions;

import com.softwaretestingcentre.test_sample_shop.helpers.LandingPage;
import com.softwaretestingcentre.test_sample_shop.helpers.Shop;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;

public class ShopStepDefinitions {

    @Given("{actor} adds {int} item/items of {string} to her basket")
    public void bettyAddsToHerBasket(Actor actor, int itemCount, String itemName) {
        actor.wasAbleTo(Open.browserOn().the(LandingPage.class));
        actor.attemptsTo(Shop.addItemsToBasket(itemCount, itemName));
    }

    @When("{actor} checks her basket")
    public void sheChecksHerBasket(Actor actor) {
        actor.attemptsTo(Shop.openBasket());
    }

    @Then("{actor} can see her basket contains only {int} item/items of {string}")
    public void sheCanSeeHerBasketContainsOnly(Actor actor, int itemCount, String itemName) {
        actor.attemptsTo(Shop.checkBasketContainsOnly(itemCount, itemName));
    }
}
