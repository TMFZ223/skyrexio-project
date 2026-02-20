package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import language.ChangeLanguageRequestModel;

import static enums.Endpoints.CHANGELANGUAGE;

public class ChangeLanguageSteps extends BaseSteps {

    @Step("Отправить post запрос на смену языка")
    public Response getChangeLanguageResponse(String token, ChangeLanguageRequestModel language) {
        return givenBase().header("Authorization", "Bearer " + token)
                .body(language)
                .when()
                .post(CHANGELANGUAGE.getPath())
                .then()
                .log().all()
                .extract()
                .response();
    }
}
