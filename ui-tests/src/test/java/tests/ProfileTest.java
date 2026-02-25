package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import user.UserFactory;

@Feature("Профиль")
public class ProfileTest extends BaseTest {

    @Test(description = "Открытие профиля")
    public void goToProfileTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .GotoProfileMenu();
    }
}
