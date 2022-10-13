package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleTests extends BaseTest
{
    @Test
    @DisplayName("Пример позитивного теста")
    @Tag("noBrowser")
    void checkTrueIsTrue()
    {
        SelenideLogger.addListener("allure", new AllureSelenide()); //добавили listener-a
        assertThat(true, is(true));
    }

    @Test
    @DisplayName("Пример негативного теста")
    @Tag("noBrowser")
    void negativeCheckTrueIsFalse()
    {
        SelenideLogger.addListener("allure", new AllureSelenide()); //добавили listener-a
        assertThat(true, is(false));
    }

    @Test
    @DisplayName("Пример позитивного теста с шагами")
    @Tag("noBrowser")
    void checkTrueIsTrueWithSteps()
    {
        SelenideLogger.addListener("allure", new AllureSelenide()); //добавили listener-a
        step("Проверка что true равно true", () ->
                assertThat(true, is(true))
        );
    }

    @Test
    @DisplayName("Пример негативного теста с шагами")
    @Tag("noBrowser")
    void negativeCheckTrueIsFalseWithSteps()
    {
        SelenideLogger.addListener("allure", new AllureSelenide()); //добавили listener-a
        step("Проверяем что true не равно false", () ->
                assertThat(true, is(false))
        );
    }
}
