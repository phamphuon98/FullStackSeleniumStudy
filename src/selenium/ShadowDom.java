package selenium;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.List;
import java.util.function.UnaryOperator;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShadowDom {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void beforeClass() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
        keys = osName.contains("Windows") ? Keys.CONTROL : Keys.COMMAND;

    }

    @Test
    public void TC01_shadowDom() {
        driver.get("https://automationfc.github.io/shadow-dom");
        sleepInSecond(5);
        WebElement shadowDom = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext ShadowrootContext = shadowDom.getShadowRoot();
        String sometext = ShadowrootContext
                .findElement(By.cssSelector("span#shadow_content>span"))
                .getText();
        System.out.println(sometext);
        Assert.assertEquals(sometext, "some text");
        WebElement checkboxShedow = ShadowrootContext
                .findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkboxShedow.isSelected());
        List<WebElement> AllIpnut = ShadowrootContext
                .findElements(By.cssSelector("input"));
        System.out.println(AllIpnut.size());
        sleepInSecond(2);
        WebElement nestedShadowDomElement = ShadowrootContext
                .findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowDomElement.getShadowRoot();
        String nestedText = nestedShadowRootContext
                .findElement(By.cssSelector("div#nested_shadow_content>div"))
                .getText();
        sleepInSecond(2);
        Assert.assertEquals(nestedText, "nested text");

    }

    @Test
    public void TC02_shadowDomShopee() {
        driver.get("https://shopee.vn/");
        sleepInSecond(5);
        WebElement ShadowHostElement = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext ShadowRootContext = ShadowHostElement.getShadowRoot();
        //WebElement Popupshopee = ShadowRootContext.findElement(By.cssSelector("div.home-popup__content"));
        WebElement popup = null;
        try {
            popup =  ShadowRootContext.findElement(By.cssSelector("div.home-popup__content"));
        } catch (Exception ex) {
            System.out.println("Not found Element");
        }
        if (popup != null && popup.isDisplayed()) {
            List<WebElement> Button = ShadowRootContext.findElements(By.cssSelector("div.home-popup__close-area"));
            for (WebElement button : Button) {
                if (button.isDisplayed()) {
                    button.click();

                }
            }

        }
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input"))
                .sendKeys("iPhone 15 Pro Max");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
    }



    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
