package validators;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class ChangeUserSettingsValidator extends BaseValidator {

    @Step("Проверить соответствие тела ответа после отправки запроса на смену пользовательских настроек json схеме")
    public void validateChangeUserSettingsSchema(Response responseBody) {
        responseBody.then()
                .body(matchesJsonSchemaInClasspath(getJsonSchema("change_user_settings_schema.json")));
    }

    @Step("Убедиться, что ключ message содержит текст {expectedUpdateMessage}")
    public void checkMessage(Response responseBody, String expectedUpdateMessage) {
        responseBody.then()
                .body("data", hasKey("message"))
                .body("data.message", equalTo(expectedUpdateMessage));
    }
}
