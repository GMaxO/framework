package apiTests;

import annotations.ApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static api.steps.AuthApiSteps.*;

@Epic(value = "Апи тестирование")
@ApiTest
public class AuthApiTests {

    @Feature(value = "Авторизация")
    @Test(description = "Успешная авторизация: верный логин, верный пароль")
    void login_success_test() {
        successLogin();
    }

//    @Feature(value = "Авторизация")
//    @Test(description = "Неуспешная авторизация: верный логин, неверный пароль")
//    void login_failed_test1() {
//        invalidCredentialsLogin(getStringProperty("user.mail"), "123123");
//    }
//
//    @Feature(value = "Авторизация")
//    @Test(description = "Неуспешная авторизация: неверный логин, верный пароль")
//    void login_failed_test2() {
//        invalidCredentialsLogin("1123@m.ru");
//    }
//
//    @Feature(value = "Авторизация")
//    @Test(description = "Неуспешная авторизация: невалидный логин")
//    void login_wrong_channel_test() {
//        wrongChannelLogin("1", "123123");
//    }

//    @Feature("Восстановление пароля")
//    @Test(description = "Успешное восстановление пароля")
//    void restore_password_test() throws MessagingException, IOException, InterruptedException {
//        MailApiSteps mailApiSteps = new MailApiSteps();
//        LocalDateTime date = LocalDateTime.now().minusMinutes(5);
//        String login = getStringProperty("user.mail");
//        String password = getStringProperty("restore.password");
//        resetPassword(login);
//        String code = mailApiSteps.getLastCodeWithSubject(login, password, "Восстановление пароля", date);
//        Cookie cookie = confirmCodeFromMail(login, code, RESTORE_PASSWORD_CONFIRM_ENDPOINT);
//        changePasswordAfterReset(cookie);
//    }
}

