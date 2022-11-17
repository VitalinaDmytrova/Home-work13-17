package it.testing.school.infrastructure.enums.webdrivermanager;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver create();

    default void destroy (WebDriver driver){
        if(driver != null){
            driver.quit();
        }
    }
}
