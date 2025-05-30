package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class ShopPage extends PageObject {

    public static Target ADD_ITEM = Target.the("Add Item to Basket")
            .locatedBy("//*[.='{0}']//..//button");

    public static Target ITEM_COST = Target.the("item cost")
            .locatedBy("//*[.='{0}']//..//*[@class='tilePrice']");

    public static Target CHECKOUT = Target.the("Checkout button")
            .locatedBy("//*[text()='Checkout']");

}
