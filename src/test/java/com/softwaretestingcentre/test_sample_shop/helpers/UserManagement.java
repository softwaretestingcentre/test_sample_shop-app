package com.softwaretestingcentre.test_sample_shop.helpers;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class UserManagement {

    public static Performable openNewUserForm() {
        return Task.where("User opens Create User form",
                Open.browserOn().the(LandingPage.class),
                Click.on(LandingPage.NAVBAR_LINK.of("Create User"))
        );
    }

    public static Performable completeNewUserForm(String username, String password) {
        return Task.where("User completes Create User form",
                SendKeys.of("q").into(CreateUserPage.EMAIL),
                DoubleClick.on(CreateUserPage.EMAIL),
                Enter.keyValues(username).into(CreateUserPage.EMAIL),

                Click.on(CreateUserPage.PASSWORD),
                SendKeys.of("q").into(CreateUserPage.PASSWORD),
                DoubleClick.on(CreateUserPage.PASSWORD),
                Enter.keyValues(password).into(CreateUserPage.PASSWORD),

                Click.on(CreateUserPage.SIGN_UP)
        );
    }

    public static Performable createUser(String username, String password) {
        deleteUserByName(username);
        return Task.where("Create a user with " + username + " and " + password,
                actor -> {
                    actor.remember("username", username);
                    actor.attemptsTo(completeNewUserForm(username, password));
                }
        );
    }

    public static Question<Boolean> userWasCreated() {
        return Question.about("User was created").answeredBy(
                actor -> {
                    String username = actor.recall("username");
                    return getUserIdFromUsername(username) > 0
                            && CreateUserPage.SUCCESS_MESSAGE.resolveFor(actor).getText()
                            .equals(CreateUserPage.EXPECTED_SUCCESS_MESSAGE);
                }
        );
    }

    public static Performable checkUserWasCreated() {
        return Ensure.that("User sees congratulations message and gets an id",
                userWasCreated()).isTrue();
    }

    public static int getUserIdFromUsername(String username) {
        int userId = 0;
        try {
            userId = when().get("http://192.168.1.242:8080/api/customer/username=" + username)
                    .then().contentType(ContentType.JSON)
                    .extract().path("customerIf"); //sic
        } catch (Exception ignored) {
        }
        return userId;
    }

    public static void deleteUserWithId(int userId) {
        when().delete("http://192.168.1.242:8080/api/customer/" + userId)
                .then().statusCode(204);
    }

    public static void deleteUserByName(String username) {
        int userId = getUserIdFromUsername(username);
        if (userId == 0) return;
        deleteUserWithId(userId);
    }

    public class Customer {
        public int getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEnabled() {
            return enabled;
        }

        public String getRole() {
            return role;
        }

        private int customerId;
        private String name;
        private String address;
        private String email;
        private String phone;
        private String username;
        private String password;
        private String enabled;
        private String role;

        public Customer(int customerId, String name) {
            this.customerId = customerId;
            this.name = name;
            this.address = "144 Townsend Street";
            this.email = "foo@bar.com";
            this.phone = "12345678";
            this.username = name;
            this.password = "password";
            this.enabled = "true";
            this.role = "USER";
        }

    }

    @Test
    public void createCustomerData() {
        for (int i = 2; i < 1001; i++) {
            Customer testCustomer = new Customer(i, "Foobar-" + i);
            given().contentType(ContentType.JSON).body(testCustomer)
                    .when().post("http://192.168.1.242:8080/api/customer/")
                    .then().statusCode(HttpStatus.SC_CREATED);
        }
    }

}
