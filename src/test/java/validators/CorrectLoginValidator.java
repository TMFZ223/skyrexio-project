package validators;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CorrectLoginValidator extends BaseValidator {

    @Step("Проверить соответствие тела ответа после успешного логина JSON схеме")
    public void validateCorrectLoginSchema(Response responseBody) {
        responseBody.then()
                .body(matchesJsonSchemaInClasspath(getJsonSchema("correct_login_schema.json")));
    }

    @Step("Убедиться, что роль авторизованного пользователя под ролью {role}")
    public void checkRole(Response responseBody, String role) {
        responseBody.then()
                .body("data.role", equalTo(role));
    }

    @Step("Проверить наличие непустого ключа accessToken")
    public void validateAccessTokenKey(Response responseBody) {
        responseBody.then()
                .body("data", hasKey("accessToken"))
                .body("data.accessToken", notNullValue());
    }

    @Step("Убедиться, что код в ключе language содержит значение {LanguageCode}")
    public void checkLanguageCode(Response responseBody, String languageCode) {
        responseBody.then()
                .body("data", hasKey("language"))
                .body("data.language", equalTo(languageCode));
    }

    @Step("Убедиться, что значение поля theme {expectedTheme}")
    public void checkTheme(Response responseBody, String expectedTheme) {
        responseBody.then()
                .body("data", hasKey("theme"))
                .body("data.theme", equalTo(expectedTheme));
    }
}
