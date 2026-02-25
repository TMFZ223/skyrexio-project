package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import user.UserFactory;

@Feature("Логин")
public class LoginTest extends BaseTest {

    @Test(description = "Позитивный тест на логин")
    public void positiveLoginTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .checkVisibilityOfProfileIcon();
    }
}
