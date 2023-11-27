package utilities;

import api.steps.LabmdaApiSteps;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.SessionId;

public class ScreenShooter {
    @Attachment(value = "Скриншот", type = "image/png")
    public static byte[] makeScreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String getVideoUrl() {
        SessionId session = WebDriverRunner.driver().getSessionId();
        return LabmdaApiSteps.getVideoUrl(session);
    }
}
