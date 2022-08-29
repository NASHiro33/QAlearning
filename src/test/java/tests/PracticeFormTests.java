package tests;


import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @Test
    void checkPracticeFormOutput() {
        open("https://demoqa.com/automation-practice-form");

        $("[class='main-header']").shouldHave(text("Practice Form"));
        $("[id='firstName']").setValue("Michael");
        $("[id='lastName']").setValue("Scott");
        $("[id='userEmail']").setValue("ms@dundermifflin.com");
        $("[class='custom-control custom-radio custom-control-inline']").click();
        $("[id='userNumber']").setValue("9099998888");
        $("[id='dateOfBirthInput']").click();
        $("[class='react-datepicker__month-select']").selectOption(1);
        $("[class='react-datepicker__year-select']").selectOption("1988");
        $("[class='react-datepicker__day react-datepicker__day--015']").click();
        $("[id='subjectsContainer']").click();
        $("[id='subjectsInput']").setValue("ar");
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Arts")).click();
        $("[id='subjectsInput']").setValue("c");
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Chemistry")).click();
        $("[id='subjectsInput']").setValue("m");
        $("[class='subjects-auto-complete__menu css-26l3qy-menu']").$(byText("Maths")).click();
        $(by("for","hobbies-checkbox-2")).click();
        $(by("for","hobbies-checkbox-3")).click();
        $("[id='uploadPicture']").uploadFile(new File("src/test/java/docs/Junit5Annotations.java"));
        $("[id='currentAddress']").setValue("2279, Dunder Mifflin office, Scranton, Pennsylvania state");
        $("[id='state']").click();
        $(byText("NCR")).click();
        $("[id='city']").click();
        $(byText("Delhi")).click();
        $("[id='submit']").pressEnter();

        $("[id='example-modal-sizes-title-lg']").shouldHave(text("Thanks for submitting the form"));
        $("[class='table-responsive']").shouldHave(
                text("Michael Scott"),
                text("ms@dundermifflin.com"),
                text("Male"),
                text("9099998888"),
                text("15 February,1988"),
                text("Arts, Chemistry, Maths"),
                text("Reading, Music"),
                text("Junit5Annotations.java"),
                text("2279, Dunder Mifflin office, Scranton, Pennsylvania state"),
                text("NCR Delhi")
        );
    }
}
