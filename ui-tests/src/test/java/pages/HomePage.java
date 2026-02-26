package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private final SelenideElement profile = $("svg.lucide-user-cog");
    private final SelenideElement terminalButton = $x("//li//button[text()='Терминал']");
    private final SelenideElement manualTradingTerminalLink = $("[href='/manual-trading/trading-terminal']");
    private final SelenideElement terminalTradingDiaryLink = $("[href='/manual-trading/trading-diary']");

    @Step("Убедиться, что иконка профиля видна")
    public void checkVisibilityOfProfileIcon() {
        profile.scrollTo().hover().shouldBe(Condition.visible);
    }

    @Step("Кликнуть по иконке профиля и убедиться в том, что меню расскрылось")
    public void GotoProfileMenu() {
        profile.scrollTo().hover().click();
        profile.shouldHave(Condition.attribute("data-state", "open"));
    }

    @Step("Кликнуть по кнопке Терминал")
    public HomePage clickTerminalButton() {
        terminalButton.click();
        return this;
    }

    @Step("Перейти по ссылке торгового терминала")
    public TradingTerminalPage goToManualTradingTerminalPage() {
        manualTradingTerminalLink.click();
        return new TradingTerminalPage();
    }

    @Step("Перейти по ссылке торгового дневника терминалат")
    public TerminalTradingDiaryPage goToTerminalTradingDiaryLink() {
        terminalTradingDiaryLink.shouldBe(Condition.visible)
                .click();
        return new TerminalTradingDiaryPage();
    }
}
