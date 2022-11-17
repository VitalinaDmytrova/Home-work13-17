package it.testing.school;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OlxTest1 {
    private WebDriver driver;


    @Test
    public void openOlx() {
        System.setProperty("webdriver.chrome.driver", "drivers/Chrom/chromedriver");


        driver = new ChromeDriver();
        driver.navigate().to("https://www.olx.ua/uk/");
        driver.quit();
    }
}