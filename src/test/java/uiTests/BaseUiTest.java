package uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.open;


public class BaseUiTest {

    @BeforeMethod(description = "Настройка вебдрайвера")
    public void init() {
        setUp();
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;
    }

    protected void openMainPage(String url){
        open(url);
    }

    @AfterMethod(description = "Закрытие вебдрайвера")
    public void closeWebDriverAfterMethod() {
        Selenide.closeWebDriver();
    }

    private String getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }
}
