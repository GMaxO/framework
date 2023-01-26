package asserts;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CustomAssert {

    private static final SoftAssert softAssert = new SoftAssert();

    @Step("Проверяем: {message}")
    public static <T> void assertEquals(final T actual, final T expected, String message) {
        Assert.assertEquals(actual, expected, "Условие не выполняется: " + message);
    }


    @Step("Проверяем: {message}")
    public static <T> void softAssertEquals(final T actual, final T expected, String message) {
        softAssert.assertEquals(actual, expected, "Условие не выполняется: " + message);
    }

    @Step("Проверяем: {message}")
    public static void assertNotNull(int actual, String message) {
        Assert.assertNotNull(actual, "Условие не выполняется: " + message);
    }

    @Step("Проверяем: {message}")
    public static void assertNotEquals(String actual, String expected, String message) {
        Assert.assertNotEquals(actual, expected, "Условие не выполняется: " + message);
    }

    @Step("Проверяем: {message}")
    public static void assertTrue(boolean actual, String message) {
        Assert.assertTrue(actual, "Условие не выполняется: " + message);
    }

    @Step("Проверяем: {message}")
    public static void assertFalse(boolean actual, String message) {
        Assert.assertFalse(actual, "Условие не выполняется: " + message);
    }

    @Step("Проверяем: {message}")
    public static void softAssertTrue(boolean actual, String message) {
        softAssert.assertTrue(actual, "Условие не выполняется: " + message);
    }

    public static void softAssertAll() {
        softAssert.assertAll();
    }
    //endregion
}
