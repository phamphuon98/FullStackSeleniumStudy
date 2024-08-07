package selenium;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.List;
import java.util.function.UnaryOperator;

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
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
        keys=osName.contains("Windows") ? Keys.CONTROL:Keys.COMMAND;

    }
    @Test public  void TC01_shadowDom() {
        driver.get("https://automationfc.github.io/shadow-dom");
         sleepInSecond(5);
         WebElement shadowDom = driver.findElement(By.cssSelector("div#non_host"));
         SearchContext ShadowrootContext = shadowDom.getShadowRoot();
         String sometext =ShadowrootContext
                 .findElement(By.cssSelector("span#shadow_content"))
                 .getText();
         System.out.println(sometext);
         Assert.assertEquals(sometext,"some text");
         WebElement checkboxShedow = driver.findElement(By.cssSelector("input[type='checkbox']"));
         Assert.assertFalse(checkboxShedow.isSelected());
         List <WebElement> AllIpnut = driver.findElements(By.cssSelector("input[type='checkbox']"));
         System.out.println(AllIpnut.size());

    }
    public int getRandomNumber() {
        return new Random().nextInt(9999);
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
