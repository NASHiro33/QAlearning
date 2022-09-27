package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTests {
    //todo убрать мапу и переменные в отдельный файл, а то мусорка

    //генерирую рандомные значения в некоторые переменные
    static Faker faker = new Faker();
    static String
            firstName = faker.name().firstName(),
            lastName  = faker.name().lastName(),
            email     = faker.internet().emailAddress(),
            phone     = faker.number().digits(10),
            address   = faker.address().fullAddress();
    static Map<String, String> expectedData = new HashMap()
    {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", email);
        put("Gender", "Male");
        put("Mobile", phone);
        put("Date of Birth", "15 February,1988");
        put("Subjects", "Arts, Chemistry, Maths");
        put("Hobbies", "Reading, Music");
        put("Picture", "Junit5Annotations.java");
        put("Address", address);
        put("State And City", "NCR Delhi");
    }};

    @Test
    @Owner("NadyaShiro")
    @Link(name = "URL", value = "https://demoqa.com/automation-practice-form")
    @Feature("Students")
    @Story("Lambda steps метод")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("positive"), @Tag("Critical")})
    @DisplayName("Позитивный тест формы регистрации студента")
    void checkPracticeFormOutput() {
        SelenideLogger.addListener("allure", new AllureSelenide()); //добавили listener-a

//        String[] hobbies   = {"Sports", "Music", "Reading"};
//        Stream<String> hobbie = Arrays.stream(hobbies).findAny().toString(); - попытка поработать со stream
            step("Открываем страницу формы регистрации", ()-> {
            open("https://demoqa.com/automation-practice-form");
            $("[class='main-header']").shouldHave(text("Practice Form"));
        });

        step("Заполняем форму корректными данными", ()-> {
            $("[id='firstName']").setValue(firstName);
            $("[id='lastName']").setValue(lastName);
            $("[id='userEmail']").setValue(email);
            $("[id='genterWrapper']").$(byText("Male")).click();
            $("[id='userNumber']").setValue(phone);
            //date of birth
            $("[id='dateOfBirthInput']").click();
            $("[class='react-datepicker__month-select']").selectOption(1);
            $("[class='react-datepicker__year-select']").selectOption("1988");
            $("[class='react-datepicker__day react-datepicker__day--015']").click();
            //subjects
            $("[id='subjectsContainer']").click();
            $("[id='subjectsInput']").setValue("ar");
            $(byText("Arts")).click();
            $("[id='subjectsInput']").setValue("c");
            $(byText("Chemistry")).click();
            $("[id='subjectsInput']").setValue("m");
            $(byText("Maths")).click();
            //the rest form
            $("[id='hobbiesWrapper']").$(byText("Reading")).click();
            $("[id='hobbiesWrapper']").$(byText("Music")).click();
            $("[id='uploadPicture']").uploadFile(new File("src/test/java/docs/Junit5Annotations.java"));
            $("[id='currentAddress']").setValue(address);
            $("[id='state']").click();
            $(byText("NCR")).click();
            $("[id='city']").click();
            $(byText("Delhi")).click();
            $("[id='submit']").pressEnter();
        });
        step("Проверяем входные данные с зарегистрированной анкетой", ()-> {
            //todo так мы каждый раз обращаемся к сайту, можно переделать на js, но там ебок
            $("[id='example-modal-sizes-title-lg']").shouldHave(text("Thanks for submitting the form"));
//        $("[class='table-responsive']").shouldHave(
//                text(firstName),
//                text(lastName),
//                text(email),
//                text("Male"),
//                text(phone),
//                text("15 February,1988"),
//                text("Arts, Chemistry, Maths"),
//                text("Reading, Music"),
//                text("Junit5Annotations.java"),
//                text(address),
//                text("NCR Delhi")
//          );
            //Вариант проверки через цикл,
            SoftAssertions softly = new SoftAssertions();
            for(SelenideElement element: $$(".table-responsive"))
            {
                String key = element.$("td").getText();
                String actualValue = element.$("td", 1).getText();
                String expectedValue = expectedData.get(key);
                softly.assertThat(actualValue).isEqualTo(expectedValue);
            }
            softly.assertAll();
        });
    }

    @Test
    void DragNdropTest() //doesn't work because selenium use user's mouse instead of virtual.
    {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().clickAndHold($("[id='column-a']")).moveToElement($("[id='column-b']")).release().perform();

        $("[id='column-a'] header").shouldHave(text("B"));
    }
}
