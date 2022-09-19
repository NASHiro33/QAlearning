package tests;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    //todo убрать мапу и переменные в отдельный файл, а то мусорка
    //todo привести в порядок импорты
    static Faker faker = new Faker();
    static String
            firstName = faker.name().firstName(),
            lastName  = faker.name().lastName(),
            email     = faker.internet().emailAddress(),
            phone     = faker.number().digits(10),
            address   = faker.address().fullAddress();
    static Map<String, String> expectedData = new HashMap<>()
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
    void checkPracticeFormOutput() {

//        String[] hobbies   = {"Sports", "Music", "Reading"};
//        Stream<String> hobbie = Arrays.stream(hobbies).findAny().toString(); - попытка поработать со stream

        open("https://demoqa.com/automation-practice-form");
        $("[class='main-header']").shouldHave(text("Practice Form"));
        //filling the form
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

        //asserts
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
    }

    @Test
    void DragNdropTest() //doesn't work because selenium use user's mouse instead of virtual.
    {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        actions().clickAndHold($("[id='column-a']")).moveToElement($("[id='column-b']")).release().perform();

        $("[id='column-a'] header").shouldHave(text("B"));
    }
}
