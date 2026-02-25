package tests;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.HomePage;
import pages.LoginPage;
import pages.TradingTerminalPage;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.closeWebDriver;

abstract class BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    TradingTerminalPage tradingTerminalPage;

    @Parameters({"webBrowser"})
    @BeforeMethod
    @Step("Открыть браузер {webBrowser}")
    public void setUp(@Optional("chrome") String webBrowser) {
        if (webBrowser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            browser = "chrome";
            browserSize = "1920x1080";
        } else if (webBrowser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            browser = "firefox";
            browserSize = "1920x1080";
        } else {
            throw new IllegalArgumentException("Браузер " + webBrowser + " Не поддерживается");
        }
        baseUrl = "https://test.skyrexio.com";
        timeout = 15000;
        loginPage = new LoginPage();
        homePage = new HomePage();
        tradingTerminalPage = new TradingTerminalPage();
    }

    @AfterMethod(alwaysRun = true)
    @Step("Очистить куки и закрыть браузер")
    public void tearDown() {
        clearBrowserCookies();
        closeWebDriver();
    }
}
