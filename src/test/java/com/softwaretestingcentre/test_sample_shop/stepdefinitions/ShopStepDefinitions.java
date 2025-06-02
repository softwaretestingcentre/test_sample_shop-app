package com.softwaretestingcentre.test_sample_shop.stepdefinitions;

import com.softwaretestingcentre.test_sample_shop.helpers.LandingPage;
import com.softwaretestingcentre.test_sample_shop.helpers.Shop;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;

import java.util.List;
import java.util.Map;


public class ShopStepDefinitions {

    @Given("{actor} adds items to her basket")
    public void bettyAddsItemsToHerBasket(Actor actor, DataTable itemChoices) {
        List<Map<String, String>> choices = itemChoices.asMaps(String.class, String.class);
        actor.remember("choices", choices);
        actor.wasAbleTo(Open.browserOn().the(LandingPage.class));
        for (Map<String, String> choice: choices) {
            actor.attemptsTo(Shop.addItemsToBasket(
                    Integer.parseInt(choice.get("Count")),
                    choice.get("Item"),
                    Integer.parseInt(choice.get("Price"))
            ));
        }
    }

    @When("{actor} checks her basket")
    public void sheChecksHerBasket(Actor actor) {
        actor.attemptsTo(Shop.openBasket());
    }

    @Then("{actor} can see her basket contains only her chosen items")
    public void sheCanSeeHerBasketContainsOnlyHerChosenItems(Actor actor) {
        actor.attemptsTo(Shop.checkBasketContainsOnly(actor.recall("choices")));
    }
}
