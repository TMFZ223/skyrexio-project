package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;

import static enums.Endpoints.LOGIN;

public class LoginSteps extends BaseSteps {

    @Step("Отправить запрос на логин")
    public Response getLoginResponse(User user) {
        return givenBase().body(user)
                .when()
                .post(LOGIN.getPath())
                .then()
                .log().all()
                .extract()
                .response();
    }
}
