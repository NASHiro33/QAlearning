package tests.pageobjects.steps;


import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;


public class ApiSteps
{

 @Step("Создаем issue в github через API")
 public Issue createIssue(String title)
 {
        Issue toCreate = new Issue();
        toCreate.setTitle(title);

  return given()
          .filter(new AllureRestAssured())
          .header("Authorization", "token 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")
          .baseUri("https://api.github.com")
          .body(toCreate)
        .when()
          .post("repos/NASHiro33/QAlearning/issues")
        .then()
          .statusCode(201)
        .extract()
          .as(Issue.class);
 }
}
