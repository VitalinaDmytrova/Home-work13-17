package it.testing.school;

import org.junit.Test;

public class OlxTest3 extends BaseTest {

    @Test
    public void openOlxChrome() {
        String s = driver.getTitle();
        driver.navigate().refresh();
        s = driver.getTitle();
    }
    @Test
    public void openOlxFireFox() {
        String s = driver.getTitle();
    }
}

