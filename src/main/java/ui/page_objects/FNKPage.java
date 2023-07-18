package ui.page_objects;

import asserts.CustomAssert;
import com.codeborne.selenide.SelenideElement;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static utilities.JsonReader.getJsonObjectFromFile;

public class FNKPage {
    @Step("Открываем чемпионат Италии")
    public FNKPage championsItaly(){
        $x("//a[@href= '/italy/' and text() = 'Италия']").click();
        SelenideElement text = $x("//b[@style ='font-size: 16px;']");

        String actual = text.getText();
        String expected = "ЧЕМПИОНАТ ИТАЛИИ 2022/2023.";

        CustomAssert.assertEquals(actual,expected,"Чемпионат Италии открыт");
        return this;
    }
    @Step("Проверка очков команды {name}")
    public FNKPage checkPoints(String name){
        SelenideElement point = $x("//b//a[@href and text() = '" + name + "']/ancestor::td[@align]/following-sibling::td[6]");
        JsonObject body =
                getJsonObjectFromFile("src/test/resources/json_expected/param_FNK.json");

        String actual = point.getText();
        String expected = body.get(name).getAsString();
        CustomAssert.assertEquals(actual,expected,"У команды " + name + " количество очков совпадает");
        return this;
    }
}
