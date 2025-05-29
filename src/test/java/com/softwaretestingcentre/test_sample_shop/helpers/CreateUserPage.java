package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class CreateUserPage extends PageObject {

    public static Target EMAIL = Target.the("User email")
            .locatedBy("[name='username']");

    public static Target PASSWORD = Target.the("User password")
            .locatedBy("[name='password']");

    public static Target SIGN_UP = Target.the("Sign up button")
            .locatedBy(".createFormButton>button");

    public static Target SUCCESS_MESSAGE = Target.the("User created successfully message")
            .locatedBy(".successMessage");

}
