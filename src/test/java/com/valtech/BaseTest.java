package com.valtech;


import com.valtech.page_objects.HomePage;
import com.valtech.page_objects.MainMenuPage;
import com.valtech.page_objects.OfficePage;
import com.valtech.page_objects.PartnersPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.*;


public class BaseTest {

    public MainMenuPage mainMenu = new MainMenuPage();
    public HomePage homePge = new HomePage();
    public PartnersPage partnersPage ;
    public OfficePage officePage = new OfficePage();
    protected Logger logger;
    private final String pathToWebDriversfolder = System.getProperty("user.dir") + "\\src\\main\\resources\\";// im doing this to show that i know how driver setup works


    public void setWebDriverByBrowserName(String browserName) throws Exception {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("disable-infobars");
            WebDriverManager.chromedriver().setup();
//            System.setProperty("webdriver.chrome.driver",pathToWebDriversfolder+"chromedriver.exe"); or if you want to use manually
            setWebDriver(new ChromeDriver(chromeOptions));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxOptions.addArguments("start-maximized");
            WebDriverManager.firefoxdriver().setup();
            setWebDriver(new FirefoxDriver(firefoxOptions));
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            setWebDriver(new EdgeDriver(edgeOptions));
        } else if (browserName.equalsIgnoreCase("internetExplorer")) {
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            ieOptions.disableNativeEvents();
            WebDriverManager.iedriver().setup();
            setWebDriver(new InternetExplorerDriver(ieOptions));
        } else {
            throw new Exception("Browser name " + browserName + " was provided incorrect");
        }
        getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getWebDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().window().maximize();
    }

    private void setLogger (){
        logger = LogManager.getLogger(getClass().getName());
        this.partnersPage = new PartnersPage(logger);
    }


    @Parameters({"browserName"})
    @BeforeClass
    public void setDriver(String browserName) throws Exception {
        setWebDriverByBrowserName(browserName);
        setLogger();
    }

    @AfterMethod
    public void clearCookies() {
        getWebDriver().manage().deleteAllCookies();
    }

    @AfterClass
    public void dropDriver() {
        getWebDriver().quit();
    }
}
