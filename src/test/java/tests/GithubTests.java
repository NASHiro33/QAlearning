package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tests.pageobjects.steps.ApiSteps;
import tests.pageobjects.steps.Issue;
import tests.pageobjects.steps.StepsForGithubTests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class GithubTests extends BaseTest
{
    //todo вынести листенера в отдельную функцию
    @Test
    void JUnit5ExampleInSoftAssertions()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/selenide/selenide");

        $("[#wiki-tab").click();
        $("[#user-content-chapters").closest("h2").sibling(0)
                .$(byText("Soft assertions")).click();

        $("[#user-content-3-using-junit5-extend-test-class")
                .closest("h4").sibling(0)
                .shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }

    String GITHUB_URL      = "https://github.com/";
    String REPOSITORY_NAME = "eroshenkoam/allure-example";
    int    ISSUE_NUMBER    = 81;
    @Test
    @DisplayName("Поиск номера issue в репозитории (Selenide steps)")
    @Owner("Nadya Shiro")
    void checkIssuesNumberOnRepoPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());

                open(GITHUB_URL);
                $("[name = 'q']").setValue(REPOSITORY_NAME).pressEnter();
                $("a[href='/"+REPOSITORY_NAME+"']").click();
                $("#issues-tab").click();
                $(withText("#"+ISSUE_NUMBER)).shouldBe(visible);
    }

    @Test
    @DisplayName("Поиск номера issue в репозитории (lambda steps)")
    @Owner("Nadya Shiro")
    void checkIssuesNumberOnRepoPageLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть " + GITHUB_URL, ()->
                open(GITHUB_URL));
        step("Ввести в поиске " + REPOSITORY_NAME, ()->
                $("[name = 'q']").setValue(REPOSITORY_NAME).pressEnter());
        step("Найти " + REPOSITORY_NAME + "на странице результатов", ()->
                $("a[href='/"+REPOSITORY_NAME+"']").click());
        step("Перейти в раздел Issues", ()->
                $("#issues-tab").click());
        step("Убедиться, что есть текст #" + ISSUE_NUMBER, ()->
                $(withText("#"+ISSUE_NUMBER)).shouldBe(visible));
    }


    public StepsForGithubTests steps = new StepsForGithubTests();
    @Test
    @DisplayName("Поиск номера issue в репозитории (method steps)")
    @Owner("Nadya Shiro")
    void checkIssuesNumberOnRepoPageMethodSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openPage(GITHUB_URL);
        steps.searchRequest(REPOSITORY_NAME);
        steps.findElementByLink(REPOSITORY_NAME);
        steps.findTabOnMenu("issues-tab");
        steps.checkIssueNumberIsVisible(ISSUE_NUMBER);
    }

    @Test
    @DisplayName("Проверяем наличие задачи, созданной по АПИ")
    @Owner("Nadya Shiro")
    void issueCreatedFromApiShouldExist()
    {
        Configuration.browser = "firefox";

        Issue issue = new Issue();
        String title = "This is an issue created by API";
        int new_issue_number = issue.getNumber();
        ApiSteps apiSteps = new ApiSteps();
        String repository = "NASHiro33/QAlearning";

        apiSteps.createIssue(title);
        steps.openPage(GITHUB_URL);
        steps.searchRequest(repository);
        steps.findElementByLink(repository);
        steps.findTabOnMenu("issues-tab");
        steps.checkIssueNumberIsVisible(new_issue_number);
    }
}
