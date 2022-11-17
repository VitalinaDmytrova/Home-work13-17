package it.testing.school;

import it.testing.school.infrastructure.enums.DefaultWebDriverManager;
import it.testing.school.infrastructure.enums.capabilities.BrowserCapabilities;
import it.testing.school.infrastructure.enums.webdrivermanager.WebDriverManager;
import jdk.jfr.Description;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

protected static WebDriver driver;
private static WebDriverManager driverManager = new DefaultWebDriverManager();
protected static WebDriver driver = new driverManager.getWebDriver();
protected static BrowserCapabilities browserCapabilities = new BrowserCapabilities(driver);
    protected static WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeClass
    public static void beforeClass() {
        driver = driverManager.getWebDriver();
        //browserCapabilities.setFullScreen();
        driver.navigate().to("https://www.olx.ua/uk/");
    }

    @AfterClass
    public static void afterClass(){
        driverManager.destroy(driver);
    }

    @Rule
    TestWatcher testWatcher = new TestWatcher (){

        @Override
        protected void starting(Description description) {
            super.starting(description);
        }
        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure();
            super.failed(e, description);
        }
        @Override
        protected void finished(Description description) {
            super.finished(description);
        }



    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshotOnFailure() {
        String path;
        try {
            File source =
                    ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

}}