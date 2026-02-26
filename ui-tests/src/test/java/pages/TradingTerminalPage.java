package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TradingTerminalPage {
    private final SelenideElement tradingPairComboBox = $("[aria-label='Select Торговая пара']");
    private final ElementsCollection traidingPairComboBoxItems = $$("[role='option']");
    private final SelenideElement searchTraidingPairInput = $("[placeholder='Выберите торговую пару']");
    private final SelenideElement noResultsTraidingPairElement = $("div.tw-text-sm[role='presentation']");

    @Step("Раскрыть список торговая пара")
    public TradingTerminalPage openTradingPairComboBox() {
        tradingPairComboBox.shouldBe(Condition.visible)
                .click();
        return this;
    }

    @Step("Убедиться, что в списке торговых пар содержится 1 или более элементов, элементы содержат текст {expectedPartialText}")
    public void checkTradingPairComboBoxItemsSizeAndPartialText(String expectedPartialText) {
        traidingPairComboBoxItems.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        for (SelenideElement traidingPairComboBoxItem : traidingPairComboBoxItems) {
            traidingPairComboBoxItem.shouldBe(Condition.visible)
                    .shouldHave(Condition.partialText(expectedPartialText));
        }
    }

    @Step("Ввести в поле торговой пары значение {pairValue}")
    public TradingTerminalPage enterTraidingPair(String pairValue) {
        searchTraidingPairInput.setValue(pairValue);
        return this;
    }

    @Step("Очистить редактор поиска торговой пары")
    public TradingTerminalPage clearSearchTraidingPairInput() {
        searchTraidingPairInput.clear();
        return this;
    }

    @Step("Убедиться, что поле поиска торговой пары пустое")
    public void checkSearchTraidingPairInputIsEmpty() {
        searchTraidingPairInput.shouldBe(Condition.empty);
    }

    @Step("Убедиться, что при отсутствии результатов поиска торговых пар отображается сообщение {expectedMessage}")
    public void checkNoResultsTraidingPairMessage(String expectedMessage) {
        noResultsTraidingPairElement.shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(expectedMessage));
    }
}
