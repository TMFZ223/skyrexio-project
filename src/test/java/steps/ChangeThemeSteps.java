package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.ChangeThemeRequestModel;

import static enums.Endpoints.CHANGETHEME;

public class ChangeThemeSteps extends BaseSteps {

    @Step("Отправить post запрос на смену темы")
    public Response getChangeThemeResponse(String token, ChangeThemeRequestModel theme) {
        return givenBase().header("Authorization", "Bearer " + token)
                .body(theme)
                .when()
                .post(CHANGETHEME.getPath())
                .then()
                .log().all()
                .extract()
                .response();
    }
}
