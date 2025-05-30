package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Shop {

    public static Performable addItemToBasket(String itemName) {
        theActorInTheSpotlight().remember("item cost", Text.of(ShopPage.ITEM_COST.of(itemName)));
        return Task.where("adds " + itemName + " to basket",
                Click.on(ShopPage.ADD_ITEM.of(itemName)));
    }

    public static Performable openBasket() {
        return Task.where("opens basket",
                Click.on(ShopPage.CHECKOUT));
    }

    public static Performable checkBasketContainsOnly(int itemCount, String itemName) {
        String itemPrice = theActorInTheSpotlight().recall("item cost").toString().replaceAll("^.", "");
        return Ensure.that(Text.of(CheckoutPage.BASKET_ITEMS))
                .matches(itemName + "\nQuantity " + itemCount + "remove\n" + itemPrice);
    }
}
