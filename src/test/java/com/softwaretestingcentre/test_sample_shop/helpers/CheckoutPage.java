package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage extends PageObject {

    public static Target BASKET_ITEMS = Target.the("Item in basket")
            .locatedBy("//*[@class='productItem']");

    public static Target SUB_TOTAL = Target.the("basket subtotal")
            .locatedBy("//*[.='subtotal']//following-sibling::*");

}
