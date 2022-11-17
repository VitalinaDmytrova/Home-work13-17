package it.testing.school.infrastructure.enums;

import it.testing.school.infrastructure.enums.webdrivermanager.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalWebDriverFactory implements WebDriverFactory {

    private WebDriver driver;

    @Override
    public WebDriver create() {
        TestBrowser browser = TestBrowser.valueOf(PropertiesUtils.get("ui.testBrowser"));

        switch (browser){

            case CHROME:
                System.setProperty("webdriver.chrome.driver","drivers/Chrom/chromedriver");
                //ChromeOptions = new ChromeOptions();
                //chromeOptions.setHeadless(true);
                //chromeOptions.addArguments("--incognito");
                //driver = new ChromeDriver(chromeOptions);
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "drivers/FF/geckodriver");
                //FireFoxOptions options = new FireFoxOptions();
                //options.setHeadless (true);
                //return new FireFoxDriver (options);
                driver = new FirefoxDriver();
                break;

            default:
                System.err.println("No exception");
        }
        return driver;
    }
}
