package api.steps;

import api.dto.auth_dto.AuthDTO;
import api.dto.auth_dto.RegisterUserDTO;
import api.dto.auth_dto.UserDTO;
import asserts.CustomAssert;
import io.qameta.allure.Step;
import io.restassured.config.EncoderConfig;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static settings.SettingStorage.getIntProperty;
import static settings.SettingStorage.getStringProperty;

public class AuthApiSteps extends BaseApiSteps {

    @Step("Попытка авторизации")
    public static Cookie successLogin() {
        String expectedFirstName = getStringProperty("user.name");
        int expectedId = getIntProperty("user.id");
        UserDTO user = new UserDTO(getStringProperty("user.mail"), getStringProperty("user.password"));
        Cookie cookie = getAuthCookie(user.getChannel(), user.getPassword());

        JsonPath response = given()
                .spec(authReqSpec("user/info"))
                .when()
                .cookie(cookie)
                .get()
                .then()
                .statusCode(200).extract().body().jsonPath();

        int actualId = Integer.parseInt(response.get("id"));
        String actualFirstName = response.get("firstName");

        CustomAssert.assertEquals(actualFirstName, expectedFirstName, "имя в ответе сервера соответствует ожидаемому");
        CustomAssert.assertEquals(actualId, expectedId, "айди в ответе сервера соответствует ожидаемому");
        return cookie;
    }
    @Step("Получаем куки для авторизации")
    public static Cookie getAuthCookie(String login, String password) {
        UserDTO user = new UserDTO(login, password);
        Cookie cookie;
        String message;
        boolean actual;
        Response response = login(user).thenReturn();
        int statusCode = response.getStatusCode();
        cookie = statusCode == 200 ? response.then().extract().detailedCookie("jid") : null;
        actual = statusCode == 200 ? true : false;
        message = statusCode == 200 ? "Куки получены" : "Куки не получены";
        CustomAssert.softAssertTrue(actual, message);
        return cookie;
    }

    public static Pair<Map<String, String>, String> getAstrologerTokenTwice(String login, String password) {
        Pair<Map<String, String>, String> pair;
        try {
            pair = getAstrologerToken(login, password);
        } catch (IllegalArgumentException e) {
            pair = getAstrologerToken(login, password);
        }
        return pair;
    }

    public static AuthDTO getAuthDTOTwoTries(String login, String password) {
        AuthDTO authDTO;
        try {
            authDTO = getAuthDTO(login, password);
        } catch (IllegalArgumentException | AssertionError e) {
            authDTO = getAuthDTO(login, password);
        }
        return authDTO;
    }


    public static synchronized AuthDTO getAuthDTO(String login, String password) {
        UserDTO user = new UserDTO(login, password);

        Cookies cookies = login(user).then().statusCode(200).extract().detailedCookies();

        Response response1 = given()
                .spec(authReqSpec("auth/cb"))
                .when()
                .cookies(cookies)
                .body("{\"conversion\":\",\"}")
                .post();

        String token = response1.body().jsonPath().get("code");

        Response response2 = given()
                .spec(appReqSpec("auth/login"))
                .log().all()
                .when()
                .body("{\"token\":\"" + token + "\", \"test\":\"" + getStringProperty("token.test") + "\" }")
                .cookies(cookies)
                .post()
                .then()
                .log().all()
                .statusCode(200).extract().response();
        String access = response2.getBody().jsonPath().get("data.token.access").toString();
        String refresh = response2.getBody().jsonPath().get("data.token.refresh").toString();
        return new AuthDTO(access, refresh, cookies);
    }

    public static Pair<Map<String, String>, String> getAstrologerToken(String login, String password) {
        String token = getAuthToken(login, password);
        Response response = given()
                .spec(appReqSpec("auth/login"))
                .when()
                .log().all()
                .body("{\"token\":\"" + token + "\", \"test\":\"" + getStringProperty("token.test") + "\" }")
                .post()
                .then()
                .log().all()
                .statusCode(200).extract().response();
        Map<String, String> cookies = response.getCookies();
        String astrologerToken = response.jsonPath().getString("data.token.access");
        return new MutablePair<>(cookies, astrologerToken);
    }

    public static String getAuthTokenTwoTries(String login, String password) {
        String token;
        token = getAuthToken(login, password);
        if (token == null) {
            token = getAuthToken(login, password);
        }
        return token;
    }

    public static String getAuthToken(String login, String password) {
        Cookie cookie = getAuthCookie(login, password);

        JsonPath response = given()
                .spec(authReqSpec("auth/cb"))
                .when()
                .cookie(cookie)
                .body("{\"conversion\":\",\"}")
                .post()
                .then()
                .statusCode(200).extract().body().jsonPath();
        System.out.println("");
        return response.get("code");
    }

    public static void main(String[] args) {
        getAuthDTO(getStringProperty("user.mail"), getStringProperty("user.password"));
    }

    @Step("Попытка авторизации. Логин: {channel}, пароль: {password}")
    public static void invalidCredentialsLogin(String channel, String password) {
        UserDTO user = new UserDTO(channel, password);
        String expectedMessage = "INVALID_CREDENTIALS";
        String actualMessage = login(user)
                .then()
                .statusCode(400)
                .extract().body().jsonPath().get("message");
        CustomAssert.assertEquals(actualMessage, expectedMessage, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Попытка авторизации. Логин: {channel}")
    public static void invalidCredentialsLogin(String channel) {
        UserDTO user = new UserDTO(channel, getStringProperty("user.password"));
        String expectedMessage = "INVALID_CREDENTIALS";
        String actualMessage = login(user)
                .then()
                .statusCode(400)
                .extract().body().jsonPath().get("message");
        CustomAssert.assertEquals(actualMessage, expectedMessage, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Попытка авторизации. Логин: {channel}, пароль: {password}")
    public static void wrongChannelLogin(String channel, String password) {
        UserDTO user = new UserDTO(channel, password);
        String expectedMessage = "WRONG CHANNEL";
        String actualMessage = login(user)
                .then()
                .statusCode(400)
                .extract().body().jsonPath().get("message");
        CustomAssert.assertEquals(actualMessage, expectedMessage, "ответ сервера соотвествует ожидаемому");
    }


    @Step("Попытка регистрации. Логин: {channel}, пароль: {password}")
    public static int successRegister(String channel, String password, String partner) {
        int id = given()
                .spec(authReqSpec(REGISTER_ENDPOINT))
                .when()
                .body(new RegisterUserDTO(channel, password, partner))
                .post()
                .then()
                .statusCode(200).extract().jsonPath().get("id");
        CustomAssert.assertNotNull(id, "присвоился айди");
        return id;
    }

    @Step("Подтверждаем код из почты. Логин: {channel}, код: {code}")
    public static Cookie confirmCodeFromMail(String channel, String code, String endpoint) {
        return given()
                .spec(authReqSpec(endpoint))
                .when()
                .body("{\"channel\":\"" + channel + "\"," +
                        "\"code\":\"" + code + "\"}")
                .post()
                .then()
                .statusCode(200).extract().detailedCookie("jid");
    }

    @Step("Проверяем совпадение присвоенного и полученного айди")
    public static void checkIds(Cookie cookie, int expectedId) {
        JsonPath response = given()
                .spec(authReqSpec("user/info"))
                .when()
                .cookie(cookie)
                .get()
                .then()
                .statusCode(200).extract().body().jsonPath();
        int actualId = Integer.parseInt(response.get("id"));
        CustomAssert.assertEquals(actualId, expectedId, "айди совпадают");
    }

    @Step("Удаляем пользователя")
    public static void deleteUser(Cookie cookie) {
        String message;
        String actualStatus = "ok";
        String expectedStatus = "ok";
        if (cookie != null) {
            actualStatus = given()
                    .spec(authReqSpec("user/"))
                    .queryParam("reason", "%D0%9C%D0%BD%D0%B5%20%D0%BD%D0%B5%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B5%D1%81%D0%BD%D1%8B%20%D0%B2%D0%B0%D1%88%D0%B8%20%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D1%8B")
                    .when()
                    .cookie(cookie)
                    .delete()
                    .then()
                    .log().all()
                    .statusCode(200).extract().body().jsonPath().get("status");
            message = "статус удаления `ok`";
        } else {
            message = "нечего удалять, пользователя не существует";
        }
        CustomAssert.assertEquals(actualStatus, expectedStatus, message);
    }


    @Step("Попытка регистрации. Логин: {channel}, пароль: {password}")
    public static void alreadyExistsRegister(String channel, String password, String partner) {
        String expectedMessage = "already exists";
        String actualMessage = given()
                .spec(authReqSpec(REGISTER_ENDPOINT))
                .when()
                .body(new RegisterUserDTO(channel, password, partner))
                .post()
                .then()
                .statusCode(400).extract().body().jsonPath().get("message");

        CustomAssert.assertEquals(actualMessage, expectedMessage, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Попытка регистрации. Логин: {channel}, пароль: {password}")
    public static void passwordMustBeLonger(String channel, String password, String partner) {
        RegisterUserDTO registerUser = new RegisterUserDTO(channel, password, partner);
        String expectedMessage = "password must be longer than or equal to 8 characters";
        String actualMessage = given()
                .spec(authReqSpec(REGISTER_ENDPOINT))
                .when()
                .body(registerUser)
                .post()
                .then()
                .statusCode(400).extract().body().jsonPath().get("message");

        CustomAssert.assertEquals(actualMessage, expectedMessage, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Сброс пароля по логину: {login}")
    public static void resetPassword(String login) {
        String expectedStatus = "ok";
        String actualStatus = given()
                .spec(authReqSpec(RESTORE_PASSWORD_ENDPOINT))
                .when()
                .body("{\"channel\":\"" + login + "\"}")
                .post()
                .then()
                .statusCode(200).extract().body().jsonPath().get("status");
        CustomAssert.assertEquals(actualStatus, expectedStatus, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Задаем новый пароль после сброса")
    public static void changePasswordAfterReset(Cookie cookie) {
        String expectedStatus = "ok";
        String newPassword = getStringProperty("user.password");
        String actualStatus = given()
                .config(config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .spec(authReqSpec(CHANGE_PASSWORD_ENDPOINT))
                .when()
                .cookie(cookie)
                .body("{\"newPassword\":\"" + newPassword + "\",\"confirmPassword\":\"" + newPassword + "\"}")
                .post()
                .then()
                .statusCode(200).extract().body().jsonPath().get("status");
        CustomAssert.assertEquals(actualStatus, expectedStatus, "ответ сервера соотвествует ожидаемому");
    }

    @Step("Регистрируем аккаунт")
    public AuthApiSteps checkRegisterAccount(MailApiSteps mailApiSteps) throws IOException, MessagingException, InterruptedException {
        LocalDateTime date = LocalDateTime.now().minusSeconds(80);
        String registerUserMail = getStringProperty("register.mail");
        String registerUserPassword = getStringProperty("register.password");
        int expectedId = successRegister(registerUserMail,registerUserPassword, null);
        String code = mailApiSteps.getLastCodeWithSubject(registerUserMail, registerUserPassword,"Подтверждение регистрации", date);
        Cookie cookie = confirmCodeFromMail(registerUserMail, code, CONFIRM_ENDPOINT);
        checkIds(cookie, expectedId);
        return this;
    }
}
