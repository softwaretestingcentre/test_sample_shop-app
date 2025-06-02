package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Shop {

    public static Performable addItemsToBasket(int itemCount, String itemName, int itemPrice) {
        return Task.where("adds " + itemName + " to basket",
                actor -> {
                    actor.remember("item price", itemPrice);
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
                    actor.remember("basket contents", Text.ofEach(CheckoutPage.BASKET_ITEMS));
                    actor.remember("basket subtotal", Text.of(CheckoutPage.SUB_TOTAL));
                }
        );
    }

    public static Question<Boolean> basketIsCorrect(List<Map<String, String>> choices) {
        return Question.about("basket contents").answeredBy(
                actor -> {
                    ArrayList<String> basketContents = actor.recall("basket contents");
                    if (basketContents.size() != choices.size()) {
                        return false;
                    }
                    int row = 0;
                    int totalPrice = 0;
                    for (Map<String, String> choice: choices) {
                        String basketRow = basketContents.get(row);
                        String itemName = choice.get("Item");
                        int itemCount = Integer.parseInt(choice.get("Count"));
                        int itemPrice = Integer.parseInt(choice.get("Price"));
                        totalPrice += itemCount * itemPrice;
                        if (!basketRow.equals(itemName + "\nQuantity " + itemCount + "remove\n" + itemPrice)) {
                            return false;
                        }
                        row++;
                    }
                    return actor.recall("basket subtotal").equals("$" + totalPrice + ".00");
                }
        );
    }

    public static Performable checkBasketContainsOnly(List<Map<String, String>> choices) {
        return Ensure.that("basket has expected Contents",
                        basketIsCorrect(choices))
                .isTrue();
    }

}
