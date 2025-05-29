package com.softwaretestingcentre.test_sample_shop.helpers;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class UserManagement {
//    private static EnvironmentVariables envVars;
//
    private static final Actor Dobby = Actor.named("Database user")
            .whoCan(CallAnApi.at("http://localhost:8080/api"));

    public static Performable createUser(String email, String password) {
        deleteUserByName(email);
        return Task.where("Create a user with " + email + " and " + password,
                SendKeys.of("q").into(CreateUserPage.EMAIL),
                DoubleClick.on(CreateUserPage.EMAIL),
                Enter.keyValues(email).into(CreateUserPage.EMAIL),

                Click.on(CreateUserPage.PASSWORD),
                SendKeys.of("q").into(CreateUserPage.PASSWORD),
                DoubleClick.on(CreateUserPage.PASSWORD),
                Enter.keyValues(password).into(CreateUserPage.PASSWORD),

                Click.on(CreateUserPage.SIGN_UP));
    }

    public static Performable checkUserWasCreated() {
        return Ensure.that(Text.of(CreateUserPage.SUCCESS_MESSAGE))
                .matches("Congratulations! Your account has been created!");
    }

    public static String getUserId(String username) {
        Dobby.attemptsTo(
                Get.resource("http://localhost:8080/api/customer/username=" + username)
        );
        try {
            User user = SerenityRest.lastResponse()
                    .jsonPath()
                    .getObject("data", User.class);
            return user.getCustomerIf();
        } catch (Exception ignored) {
            return "NOT FOUND";
        }
    }

    public static void deleteUser(String userId) {
        Dobby.attemptsTo(Delete.from("http://localhost:8080/api/customer/" + userId));
    }

    public static void deleteUserByName(String username) {
        String userId = getUserId(username);
        if (userId.equals("NOT FOUND")) return;
        deleteUser(userId);
    }

}
