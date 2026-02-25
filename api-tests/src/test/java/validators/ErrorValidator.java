package validators;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ErrorValidator extends BaseValidator {

    @Step("Проверить соответствие тела ответа после отправки запроса с невалидными данными json схеме")
    public void validateErrorJsonSchema(Response responseBody) {
        responseBody.then()
                .body(matchesJsonSchemaInClasspath(getJsonSchema("error_response_schema.json")));
    }

    @Step("Убедиться, что ключ message содержит сообщение об ошибке {expectedErrorMessage}")
    public void checkExpectedErrorMessage(Response responseBody, String expectedErrorMessage) {
        responseBody.then()
                .body("data", hasKey("message"))
                .body("data.message", equalTo(expectedErrorMessage));
    }
}
