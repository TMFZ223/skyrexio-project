package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TerminalTradingDiaryPage {
    private final SelenideElement tradingDiaryHeder = $("h2.tw-font-normal");
    private final String TAB_FILTER_PATTERN = "//button[@role='tab' and text()='%s']";
    private final SelenideElement openDateTableButton = $("button.tw-justify-start[aria-haspopup='dialog']");

    @Step("Убедиться, что заголовок содержит текст {tradingDiaryText}")
    public void checkTradingDiaryText(String tradingDiaryText) {
        tradingDiaryHeder.shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(tradingDiaryText));
    }

    @Step("Активировать вкладку с фильтром {tabName}")
    public TerminalTradingDiaryPage goToFilterTab(String tabName) {
        SelenideElement tabFilter = $x(TAB_FILTER_PATTERN.formatted(tabName));
        tabFilter.shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step("Убедиться, что на кнопке открытия таблицы для выбора даты отображается диапазон дат {expectedPeriod}")
    public void checkDatePeriod(String expectedPeriod) {
        openDateTableButton.shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(expectedPeriod));
    }
}
