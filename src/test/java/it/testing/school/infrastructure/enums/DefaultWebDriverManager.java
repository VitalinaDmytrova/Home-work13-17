package it.testing.school.infrastructure.enums;

import it.testing.school.infrastructure.enums.webdrivermanager.WebDriverFactory;
import it.testing.school.infrastructure.enums.webdrivermanager.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DefaultWebDriverManager implements WebDriverManager {

    WebDriverFactory driverFactory;

    @Override
    public WebDriver getWebDriver() {
        RunOn runOn = RunOn.valueOf(PropertiesUtils.get("ui.runOn"));

        switch (runOn){
            case CLOUD:
                break;
            case LOCAL:
                driverFactory = new LocalWebDriverFactory();
                break;
        }
        return driverFactory.create();
    }

    @Override
    public void destroy(WebDriver driver) {
        WebDriverManager.super.destroy(driver);
    }
}
