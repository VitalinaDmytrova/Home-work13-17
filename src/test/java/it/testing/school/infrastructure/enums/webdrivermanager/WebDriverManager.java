package it.testing.school.infrastructure.enums.webdrivermanager;

import org.openqa.selenium.WebDriver;

public interface WebDriverManager {

    WebDriver getWebDriver ();

    default void destroy (WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

}
