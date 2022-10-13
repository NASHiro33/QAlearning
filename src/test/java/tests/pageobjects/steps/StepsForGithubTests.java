package tests.pageobjects.steps;

import io.qameta.allure.Step;
import tests.BaseTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepsForGithubTests extends BaseTest
{
    @Step("Открываем страницу {url}")
    public void openPage(String url)
    {
        open(url);
    }

    @Step("Ищем в поиске {request}")
    public void searchRequest(String request)
    {
        $("[name = 'q']").setValue(request).pressEnter();
    }

    @Step("Ищем на странице ссылку {link}")
    public void findElementByLink(String link)
    {
        $("a[href='/"+link+"']").click();
    }

    @Step("Ищем пункт меню {tabId}")
    public void findTabOnMenu(String tabId)
    {
        $("[id='"+tabId+"']").click();
    }

    @Step("Проверяем, что на странице есть issue #{number}")
    public void checkIssueNumberIsVisible(int number)
    {
        $(withText("#"+number)).shouldBe(visible);
    }
}
