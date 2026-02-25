package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

@Feature("Смарт терминал")
public class TraidingTerminalTest extends BaseTest {

    @Test(description = "Открытие списка торговая пара")
    public void openTraidingPairComboBoxTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToManualTradingTerminalPage()
                .openTradingPairComboBox()
                .checkTradingPairComboBoxItemsSizeAndPartialText("USDC");
    }

    @DataProvider
    public Object[][] baseActiveProvider() {
        return new Object[][]{
                {"BTC"},
                {"btc"}};
    }

    @Test(description = "Поиск торговой пары по тикеру базового актива с учётом регистра", dataProvider = "baseActiveProvider")
    public void searchPairWithTikerBaseActiveTest(String tikerBaseActive) {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToManualTradingTerminalPage()
                .openTradingPairComboBox()
                .enterTraidingPair(tikerBaseActive)
                .checkTradingPairComboBoxItemsSizeAndPartialText(tikerBaseActive.toUpperCase());
    }

    @Test(description = "Поиск несуществующей торговой пары")
    public void searchUnknownTraidingPairTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToManualTradingTerminalPage()
                .openTradingPairComboBox()
                .enterTraidingPair("XYZ123")
                .checkNoResultsTraidingPairMessage("Торговая пара не найдены");
    }

    @Test(description = "Очистка поля поиска торговой пары")
    public void clearSearchTraidingPairInputTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToManualTradingTerminalPage()
                .openTradingPairComboBox()
                .enterTraidingPair("example text")
                .clearSearchTraidingPairInput()
                .checkSearchTraidingPairInputIsEmpty();
    }
}
