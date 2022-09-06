package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTests
{

    @Test
    void selenideSearchTest()
    {
        //открыть google
        open("https://www.google.com/");
        //ввести Selenide в поиск
        $(byName("q")).setValue("Selenide").pressEnter();
        //проверить, что Selenide есть в результатах поиска
        $("[id=search]").shouldHave(text("selenide.org"));
    }
}
