package api;

import io.restassured.response.Response;
import models.ChangeLanguageRequestModel;
import factories.LanguageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import models.ChangeThemeRequestModel;
import factories.ThemeFactory;
import models.User;
import factories.UserFactory;

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
        changeUserSettingsValidator.validateChangeUserSettingsSchema(changeLanguageResponse);
        changeUserSettingsValidator.checkMessage(changeLanguageResponse, "Language updated");
        Response loginResponseBodyAfterChangeLanguage = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        correctLoginValidator.checkLanguageCode(loginResponseBodyAfterChangeLanguage, language.getLanguage());
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

    @DataProvider
    public Object[][] themeData() {
        return new Object[][]{
                {ThemeFactory.swichToDark()},
                {ThemeFactory.swichToLight()}};
    }

    @Test(description = "Смена темы", dataProvider = "themeData")
    public void changeThemeTest(ChangeThemeRequestModel theme) {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        String token = loginResponseBody.jsonPath().getString("data.accessToken");
        Response changeThemeResponse = changeThemeSteps.getChangeThemeResponse(token, theme);
        changeThemeSteps.checkStatusCode(changeThemeResponse, 201);
        changeUserSettingsValidator.validateChangeUserSettingsSchema(changeThemeResponse);
        changeUserSettingsValidator.checkMessage(changeThemeResponse, "Theme updated");
        Response loginResponseBodyAfterChangeTheme = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        correctLoginValidator.checkTheme(loginResponseBodyAfterChangeTheme, theme.getTheme());
    }

    @DataProvider
    public Object[][] pairLanguageThemeData() {
        return new Object[][]{
                {LanguageFactory.swichToEnglish(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToEspanol(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToFranch(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToDeutsch(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToRussian(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToChinese(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToJapanese(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToKorean(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToEspanol(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToEnglish(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToDeutsch(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToFranch(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToChinese(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToRussian(), ThemeFactory.swichToLight()},
                {LanguageFactory.swichToKorean(), ThemeFactory.swichToDark()},
                {LanguageFactory.swichToJapanese(), ThemeFactory.swichToLight()}};
    }

    @Test(description = "Попарное тестирование смены языка и темы", dataProvider = "pairLanguageThemeData")
    public void pairChangeLanguageThemeTest(ChangeLanguageRequestModel language, ChangeThemeRequestModel theme) {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        String token = loginResponseBody.jsonPath().getString("data.accessToken");
        changeLanguageSteps.getChangeLanguageResponse(token, language);
        changeThemeSteps.getChangeThemeResponse(token, theme);
        Response loginResponseBodyAfterChangeSettings = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        correctLoginValidator.checkTheme(loginResponseBodyAfterChangeSettings, theme.getTheme());
        correctLoginValidator.checkLanguageCode(loginResponseBodyAfterChangeSettings, language.getLanguage());
    }

    @Test(description = "Переключение на несуществующую тему в системе")
    public void changeUnknownThemeTest() {
        Response loginResponseBody = loginSteps.getLoginResponse(UserFactory.withValidCredentials());
        String token = loginResponseBody.jsonPath().getString("data.accessToken");
        Response changeThemeResponse = changeThemeSteps.getChangeThemeResponse(token, ThemeFactory.swichToDim());
        changeThemeSteps.checkStatusCode(changeThemeResponse, 200);
        errorValidator.validateErrorJsonSchema(changeThemeResponse);
        errorValidator.checkExpectedErrorMessage(changeThemeResponse, "theme must be one of the following values: light, dark");
    }
}
