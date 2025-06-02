package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

public class Shop {

    public static Performable addItemsToBasket(int itemCount, String itemName) {
        return Task.where("adds " + itemName + " to basket",
                actor -> {
                    actor.remember("item cost", Text.of(ShopPage.ITEM_COST.of(itemName)));
                    for (int i = 0; i < itemCount; i++) {
                        actor.attemptsTo(Click.on(ShopPage.ADD_ITEM.of(itemName)));
                    }
                }
        );
    }

    public static Performable openBasket() {
        return Task.where("opens basket",
                actor -> {
                    actor.attemptsTo(Click.on(ShopPage.CHECKOUT));
                    actor.remember("basket contents", Text.of(CheckoutPage.BASKET_ITEMS));
                    actor.remember("basket subtotal", Text.of(CheckoutPage.SUB_TOTAL));
                }
        );
    }

    public static Question<Boolean> basketIsCorrect(int itemCount, String itemName) {
        return Question.about("basket contents").answeredBy(
                actor -> {
                    String itemPrice = actor.recall("item cost").toString().replaceAll("^.", "");
                    String itemTotal = "$" + (itemCount * Integer.parseInt(itemPrice)) + ".00";
                    String basketContents = actor.recall("basket contents");
                    String subTotal = actor.recall("basket subtotal");

                    return basketContents.equals(itemName + "\nQuantity " + itemCount + "remove\n" + itemPrice)
                            && subTotal.equals(itemTotal);
                }
        );
    }

    public static Performable checkBasketContainsOnly(int itemCount, String itemName) {
        return Ensure.that("basket has " + itemCount + " of " + itemName,
                basketIsCorrect(itemCount, itemName))
                .isTrue();
    }

}
