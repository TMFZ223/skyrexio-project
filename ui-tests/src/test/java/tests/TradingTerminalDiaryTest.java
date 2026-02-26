package tests;

import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;
import utils.PeriodGeneratorText;

@Feature("Торговый дневник торгового терминала")
public class TradingTerminalDiaryTest extends BaseTest {

    @Test(description = "Открытие страницы торгового дневника торгового терминала")
    public void openTradingTerminalDiaryPageTest() {
        loginPage
                .openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToTerminalTradingDiaryLink()
                .checkTradingDiaryText("Торговый дневник");
    }

    @DataProvider
    public Object[][] periodProvider() {
        return new Object[][]{
                {"Неделя", PeriodGeneratorText.generateTextFromPeriod1Week()},
                {"Месяц", PeriodGeneratorText.generateTextFromPeriod1Month()},
                {"3 месяца", PeriodGeneratorText.generateTextFromPeriod3Month()}};
    }

    @Test(description = "Изменение периода через вкладки фильтров для показа информации в дневнике", dataProvider = "periodProvider")
    public void changePeriodFromFilterTabTest(String dateFilter, String expectedPeriod) {
        loginPage.openPage()
                .login(UserFactory.withValidCredentials())
                .clickTerminalButton()
                .goToTerminalTradingDiaryLink()
                .goToFilterTab(dateFilter)
                .checkDatePeriod(expectedPeriod);
    }
}
