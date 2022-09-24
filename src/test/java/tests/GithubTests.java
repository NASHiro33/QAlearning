package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubTests
{
    @Test
    void JUnit5ExampleInSoftAssertions()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/selenide/selenide");

        $("[id='wiki-tab']").click();
        $("[id='user-content-chapters']").closest("h2").sibling(0)
                .$(byText("Soft assertions")).click();

        $("[id='user-content-3-using-junit5-extend-test-class']")
                .closest("h4").sibling(0)
                .shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }
}
