package it.testing.school;

import org.junit.Test;

public class OlxTest2 extends BaseTest {

    @Test
    public void openOlxChrome(){
        String s = driver.getTitle();
        assert s.contains ("Olx");

    }

    @Test
    public void openOlxFireFox () {
        driver.navigate().to("https://www.olx.ua/uk/");
        String s = driver.getTitle();
        assert s.contains ("Olx");

    }
}
