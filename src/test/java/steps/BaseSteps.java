package steps;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.AllureRequestOnlyFilter;
import utils.PropertyReader;

import static io.restassured.RestAssured.*;

abstract class BaseSteps {
    private static final String BASE_URI = PropertyReader.getProperty("stage.backend");

    public RequestSpecification givenBase() {
        return given().spec(getRequestSpecification());
    }

    @Step("Убедиться, что код ответа {statusCode}")
    public void checkStatusCode(Response response, int statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .addFilter(new AllureRequestOnlyFilter())
                .build();
    }
}
