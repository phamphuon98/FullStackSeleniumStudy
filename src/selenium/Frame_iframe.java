package selenium;

import java.time.Duration;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Frame_iframe {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_iframe() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();
        sleepInSecond(5);
        //iframe
        WebElement formiFrame = driver.findElement(By.cssSelector("div#formTemplateContainer> iframe"));
        Assert.assertTrue(formiFrame.isDisplayed());

        //switch vao trc khi thao tac voi elemen ben trong -> co the khong chinh xac
        //driver.switchTo().frame(0);
        //1. dung name hoac id -> co TH khong co id va khong co name
        //driver.switchTo().frame("frame-one85593366");
        //2. Dung webelemet -> nen dung do it thay doi
        driver.switchTo().frame(formiFrame);
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2")))
                .selectByVisibleText("Senior");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3")))
                .selectByVisibleText("North Dorm");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                driver.findElement(By.xpath("//input[@id='RESULT_RadioButton-4_1']/following-sibling::label[text()='Female']")));
        driver.findElement(By.cssSelector("input.submit_button")).click();
        sleepInSecond(5);
        driver.switchTo().defaultContent();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSecond(2);
        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(
                By.xpath("//div[text()='Username and password are both required.']")).getText()
                ,"Username and password are both required.");

    }
    @Test public void Tc_02_HDFCBankiframe() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        sleepInSecond(5);
        WebElement HDFCframe = driver.findElement(By.cssSelector("frame[name='login_page']"));
        sleepInSecond(5);
        driver.switchTo().frame(HDFCframe);
        sleepInSecond(3);
        driver.findElement(By.cssSelector("input.form-control")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("a.btn-primary")).click();
        sleepInSecond(5);
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789@");
        sleepInSecond(5);
        driver.findElement(By.cssSelector("a#loginBtn")).click();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
