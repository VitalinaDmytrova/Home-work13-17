package it.testing.school.infrastructure.enums.capabilities;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriverBuilder;

import java.security.PublicKey;

public class BrowserCapabilities {
    private WebDriver driver;

    public BrowserCapabilities (WebDriver driver){
        this.driver = driver;

        public void browserSize(){
            Dimension d = new Dimension(600, 600);
            driver.manage().window().setSize(d);
        }
        public void setFullScreen(){
            driver.manage().window().maximize();
        }

        public String getBrowserInfo(){
            Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
            String browserName = cap.getBrowserName().toLowerCase();
            System.out.println(browserName);
            String os = cap.getPlatform().toString();
            System.out.println(os);
            String v = cap.getVersion();
            System.out.println(v);
        }
        public String getPlatformInfo(){
            System.out.println(capabilities.getPlatformInfo());

        }
    }
}
