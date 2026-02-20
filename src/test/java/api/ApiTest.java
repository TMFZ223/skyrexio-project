package api;

import io.restassured.response.Response;
import language.ChangeLanguageRequestModel;
import language.LanguageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

public class ApiTest extends BaseTest {

    @Test(description = "Позитивный тест на логин")
    public void positiveLoginTest() {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        loginSteps.checkStatusCode(loginResponseBody, 200);
        correctLoginValidator.validateCorrectLoginSchema(loginResponseBody);
        correctLoginValidator.checkRole(loginResponseBody, USER_ROLE);
        correctLoginValidator.validateAccessTokenKey(loginResponseBody);
    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {UserFactory.withUnknownEmail(), "Invalid email or password"},
                {UserFactory.withIncorrectFormatEmail(), "email must be an email"},
                {UserFactory.withEmptyEmail(), "email should not be empty; email must be an email"},
                {UserFactory.withEmptyPassword(), "password should not be empty"},
                {UserFactory.withEmptyValues(), "email should not be empty; email must be an email; password should not be empty"},
                {UserFactory.withNullEmailValue(), "email contains potentially malicious content and is not allowed; email should not be empty; email must be an email"},
                {UserFactory.withNullPasswordValue(), "password contains potentially malicious content and is not allowed; password should not be empty; password must be a string"},
                {UserFactory.withNullValues(), "email contains potentially malicious content and is not allowed; email should not be empty; email must be an email; password contains potentially malicious content and is not allowed; password should not be empty; password must be a string"}};
    }

    @Test(description = "Негативный тест на логин", dataProvider = "negativeLoginTestData")
    public void negativeLoginTest(User user, String errorMessage) {
        Response loginResponseBody = loginSteps.getLoginResponse(user);
        loginSteps.checkStatusCode(loginResponseBody, 200);
        errorValidator.validateErrorJsonSchema(loginResponseBody);
        errorValidator.checkExpectedErrorMessage(loginResponseBody, errorMessage);
    }

    @DataProvider
    public Object[][] languageData() {
        return new Object[][]{
                {LanguageFactory.swichToEnglish()},
                {LanguageFactory.swichToEspanol()},
                {LanguageFactory.swichToFranch()},
                {LanguageFactory.swichToDeutsch()},
                {LanguageFactory.swichToRussian()},
                {LanguageFactory.swichToChinese()},
                {LanguageFactory.swichToJapanese()},
                {LanguageFactory.swichToKorean()}};
    }

    @Test(description = "смена языка", dataProvider = "languageData")
    public void changeLanguageTest(ChangeLanguageRequestModel language) {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        String token = loginResponseBody.jsonPath().getString("data.accessToken");
        Response changeLanguageResponse = changeLanguageSteps.getChangeLanguageResponse(token, language);
        changeLanguageSteps.checkStatusCode(changeLanguageResponse, 201);
        changeLanguageValidator.validateChangeLanguageSchema(changeLanguageResponse);
        changeLanguageValidator.checkUpdateLanguageMessage(changeLanguageResponse, "Language updated");
        Response loginResponseBodyAfterChangeLanguage = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        correctLoginValidator.checkChangeLanguageCode(loginResponseBodyAfterChangeLanguage, language.getLanguage());
    }

    @Test(description = "Переключение на несуществующий в системе язык")
    public void changeUnknownLanguageTest() {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        String token = loginResponseBody.jsonPath().getString("data.accessToken");
        Response changeLanguageResponse = changeLanguageSteps.getChangeLanguageResponse(token, LanguageFactory.swichToIrish());
        changeLanguageSteps.checkStatusCode(changeLanguageResponse, 200);
        errorValidator.validateErrorJsonSchema(changeLanguageResponse);
        errorValidator.checkExpectedErrorMessage(changeLanguageResponse, "language must be one of the following values: en, es, fr, de, ru, zh, ja, ko");
    }

    @DataProvider
    public Object[][] invalidAuthTokenProvider() {
        return new Object[][]{
                {"", "No auth token"},
                {null, "Invalid access token"}};
    }

    @Test(description = "отправка запроса на смену языка без авторизационного токена", dataProvider = "invalidAuthTokenProvider")
    public void changeLanguageTestWithoutAuthToken(String token, String errorMessage) {
        Response changeLanguageResponse = changeLanguageSteps.getChangeLanguageResponse(token, LanguageFactory.swichToEnglish());
        changeLanguageSteps.checkStatusCode(changeLanguageResponse, 200);
        errorValidator.validateErrorJsonSchema(changeLanguageResponse);
        errorValidator.checkExpectedErrorMessage(changeLanguageResponse, errorMessage);
    }
}
