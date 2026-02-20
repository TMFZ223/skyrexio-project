package validators;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ChangeLanguageValidator extends BaseValidator {

    @Step("Проверить соответствие тела ответа после отправки запроса на смену языка json схеме")
    public void validateChangeLanguageSchema(Response responseBody) {
        responseBody.then()
                .body(matchesJsonSchemaInClasspath(getJsonSchema("change_language_schema.json")));
    }

    @Step("Убедиться, что ключ message содержит текст {expectedUpdateLanguageMessage}")
    public void checkUpdateLanguageMessage(Response responseBody, String expectedUpdateLanguageMessage) {
        responseBody.then()
                .body("data", hasKey("message"))
                .body("data.message", equalTo(expectedUpdateLanguageMessage));
    }
}
