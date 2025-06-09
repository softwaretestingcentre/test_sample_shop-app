package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

@DefaultUrl("http://192.168.1.242:8080/index.html")
public class LandingPage extends PageObject {
    public static Target WELCOME_BANNER = Target.the("Welcome Banner")
            .locatedBy(".headerTitle");
    public static Target NAVBAR_LINK = Target.the("User Link - {0}")
            .locatedBy("//div[@class='navUser']//button[.='{0}']");
}
