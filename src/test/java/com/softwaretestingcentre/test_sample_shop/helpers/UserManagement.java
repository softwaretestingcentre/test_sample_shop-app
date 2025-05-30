package com.softwaretestingcentre.test_sample_shop.helpers;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.DoubleClick;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import static io.restassured.RestAssured.when;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserManagement {

    public static Performable createUser(String username, String password) {
        deleteUserByName(username);
        theActorInTheSpotlight().remember("username", username);
        return Task.where("Create a user with " + username + " and " + password,
                SendKeys.of("q").into(CreateUserPage.EMAIL),
                DoubleClick.on(CreateUserPage.EMAIL),
                Enter.keyValues(username).into(CreateUserPage.EMAIL),

                Click.on(CreateUserPage.PASSWORD),
                SendKeys.of("q").into(CreateUserPage.PASSWORD),
                DoubleClick.on(CreateUserPage.PASSWORD),
                Enter.keyValues(password).into(CreateUserPage.PASSWORD),

                Click.on(CreateUserPage.SIGN_UP));
    }

    public static Performable checkUserWasCreated() {
        String username = theActorInTheSpotlight().recall("username");
        return Ensure.that(Text.of(CreateUserPage.SUCCESS_MESSAGE))
                .matches("User created",
                        message ->
                                message.equals("Congratulations! Your account has been created!")
                                        && getUserIdFromUsername(username) > 0);
    }

    public static int getUserIdFromUsername(String username) {
        int userId = 0;
        try {
            userId = when().get("http://localhost:8080/api/customer/username=" + username)
                    .then().contentType(ContentType.JSON)
                    .extract().path("customerIf"); //sic
        } catch (Exception ignored) {
        }
        return userId;
    }

    public static void deleteUserWithId(int userId) {
        when().delete("http://localhost:8080/api/customer/" + userId)
                .then().statusCode(204);
    }

    public static void deleteUserByName(String username) {
        int userId = getUserIdFromUsername(username);
        if (userId == 0) return;
        deleteUserWithId(userId);
    }

}
