package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import user.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("[type='submit']");

    @Step("Перейти на страницу логина")
    public LoginPage openPage() {
        Selenide.open("/ru/login");
        return this;
    }

    @Step("Выполнить вход в систему")
    public HomePage login(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickLoginButton();
        return new HomePage();
    }

    @Step("Ввести в поле email значение {email}")
    private void enterEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввести в поле password значение {password}")
    private void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Нажать на кнопку входа")
    private void clickLoginButton() {
        loginButton.click();
    }
}
