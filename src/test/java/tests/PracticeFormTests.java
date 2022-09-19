package tests;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @Test
    void checkPracticeFormOutput() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName  = faker.name().lastName();
        String email     = faker.internet().emailAddress();
        String phone     = faker.number().digits(10);
        String address   = faker.address().fullAddress();

        open("https://demoqa.com/automation-practice-form");
        $("[class='main-header']").shouldHave(text("Practice Form"));

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
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Arts")).click();
        $("[id='subjectsInput']").setValue("c");
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Chemistry")).click();
        $("[id='subjectsInput']").setValue("m");
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Maths")).click();
        //the whole form
        $(by("for","hobbies-checkbox-2")).click();
        $(by("for","hobbies-checkbox-3")).click();
        $("[id='uploadPicture']").uploadFile(new File("src/test/java/docs/Junit5Annotations.java"));
        $("[id='currentAddress']").setValue(address);
        $("[id='state']").click();
        $(byText("NCR")).click();
        $("[id='city']").click();
        $(byText("Delhi")).click();
        $("[id='submit']").pressEnter();

        //asserts
        $("[id='example-modal-sizes-title-lg']").shouldHave(text("Thanks for submitting the form"));
        $("[class='table-responsive']").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text("Male"),
                text(phone),
                text("15 February,1988"),
                text("Arts, Chemistry, Maths"),
                text("Reading, Music"),
                text("Junit5Annotations.java"),
                text(address),
                text("NCR Delhi")
        );
    }

    @Test
    void DragNdropTest() //doesn't work because selenium use user's mouse instead of virtual.
    {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        actions().clickAndHold($("[id='column-a']")).moveToElement($("[id='column-b']")).release().perform();

        $("[id='column-a'] header").shouldHave(text("B"));
    }
}
