package tests;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @Test
    void checkDataInOutputTest()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());//подключаем листенера

        String name = "Nadin";
        String email = "ns@olol.ru";
        String currentAddress = "Moscow-city";
        String permanentAddress = "2279 3rd Ave";
        open("https://demoqa.com/text-box");

        $("[id='userName']").setValue(name);
        $("[id='userEmail']").setValue(email);
        $("[id='currentAddress']").setValue(currentAddress);
        $("[id='permanentAddress']").setValue(permanentAddress);
        $("[id='submit']").click();

        $("[id='output']").shouldHave(text(email), text(name), text(currentAddress), text(permanentAddress));
        sleep(4000);
    }
}
