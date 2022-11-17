package it.testing.school.infrastructure.enums;

import it.testing.school.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;


public class OlxTest4cars extends BaseTest {
protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    public OlxTest4cars(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.driver = driver;
        this.wait = wait;
        this.logger = logger;
    }

    @Test
        //Знайти аudi машини які менше 50000
        public void searchAudi() throws InterruptedException {
            Actions actions = new Actions(driver);
            logger.debug("Виключити кукі для сторінки");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 7);

            driver.manage().addCookie(new Cookie("cookieBarSeen", Boolean.TRUE.toString(), "/", cal.getTime()));
            driver.navigate().refresh();

            //вибрати вікно Авто
            logger.info("Обрати Авто на головній сторнці");
            WebElement carButtonOnMainPage = driver.findElement(By.xpath("//*[@data-id='1532']"));
            carButtonOnMainPage.click();

            logger.info("Обрати 'Легкові автомобілі' на головній сторнці");
            WebElement carButton = driver.findElement(By.xpath("(//*[@data-category-id='1532']/span)[1]"));
            wait.until(ExpectedConditions.visibilityOf(carButton));
            carButton.click();

            logger.info("Перевірити що 'Будь-яка категорія' відображається");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".css-12snx2d")));
            WebElement anyCategoryDropDownRoot = driver.findElement(By.cssSelector(".css-12snx2d"));
            wait.until(ExpectedConditions.visibilityOf(anyCategoryDropDownRoot));

            logger.info("Обрати 'Audi' в дропдауні");
            anyCategoryDropDownRoot.click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul.css-1g9b9fl"))));
            List<WebElement> anyCategoryDropDownList = driver.findElements(By.cssSelector("li.css-1bywj74"));
            for (WebElement elementFromDropDown : anyCategoryDropDownList) {
                if (elementFromDropDown.getText().contains("Audi")) {
                    logger.info("Обрати в дропдауні Audi");
                    elementFromDropDown.click();
                    break;
                }
            }
            //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(), 'Audi')]"))));
            // WebElement audiFromDropDown = driver.findElement(By.cssSelector("li.css-1bywj74"));
            //  audiFromDropDown.click();
            logger.info("Перевірити що  Audi обралось з дропдауна");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()[contains(.,'Audi')]]")));

            logger.info("Вказати ціну машини ДО");
            WebElement priceRangeTo = driver.findElement(By.xpath("(//*[@name='range-to-input'])[1]"));
            priceRangeTo.sendKeys("50000");

            logger.info("Обрати сортування по Доллару");
            WebElement sortByDollar = driver.findElement(By.xpath("//span[text()[contains(.,'$')]]"));
            logger.info("Проскролити до кнопки сортування");
            actions.moveToElement(sortByDollar);
            actions.doubleClick(sortByDollar);
            actions.perform();
            Thread.sleep(100);

            logger.info("Перевіряю що обрана валюта Долар в списку результатів");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@data-testid='ad-price'])[1]")));
            WebElement firstElementFromResultList = driver.findElement(By.xpath("(//*[@data-testid='ad-price'])[1]"));
            logger.info("Проскролити до результатів пошуку");
            actions.moveToElement(firstElementFromResultList);
            actions.perform();

            logger.info("Конвертувати першу ціну до Long");
            String actualPriceString = driver.findElement(By.xpath("(//p[@data-testid='ad-price'])[1]"))
                    .getText()
                    .replace(" ", "");
            actualPriceString = actualPriceString.substring(0, actualPriceString.indexOf("$"));
            Long actualPrice = Long.parseLong(actualPriceString);

            Assert.assertTrue(actualPrice < 50000);
        }
    }

}
