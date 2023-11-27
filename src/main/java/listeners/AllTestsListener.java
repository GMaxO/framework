package listeners;

import annotations.ApiTest;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.logging.LogType;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static settings.SettingStorage.runWithRetries;
import static utilities.ScreenShooter.getVideoUrl;
import static utilities.ScreenShooter.makeScreenShot;

public class AllTestsListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (runWithRetries) {
            method.getTestMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (isApiTest(iInvokedMethod)) {
        } else {
            if (WebDriverRunner.driver().hasWebDriverStarted()) {
                Allure.link("Видео теста", getVideoUrl());
                if (iTestResult.getStatus() == ITestResult.FAILURE) {
                    makeScreenShot();
                    String consoleLog = Selenide.getWebDriverLogs(LogType.BROWSER).toString();
                    String logsForAllure = consoleLog.isEmpty() ? "Записи в консоли отсутствуют" : consoleLog;
                    Allure.addAttachment("Логи консоли", logsForAllure);
                }
            }
        }
    }

    private boolean isApiTest(IInvokedMethod iInvokedMethod) {
        return iInvokedMethod.getTestMethod().getRealClass().isAnnotationPresent(ApiTest.class);
    }
}
