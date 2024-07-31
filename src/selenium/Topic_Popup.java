package selenium;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Popup {
    WebDriver driver;

    @BeforeClass
    public void befoclass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void FixedPopupinDOM_01() {
        driver.get("https://ngoaingu24h.vn/");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("button.login_ ")).click();
        sleepInSecond(2);
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        sleepInSecond(2);
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#account-input"))
                .sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style]>div input#password-input"))
                .sendKeys("automationfc");
        driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading' and text()='Đăng nhập']"))
                .click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']"))
                .getText(), "Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void FixedPopupinDOM_02() {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        sleepInSecond(2);
        //driver.findElement(By.cssSelector("button.login_ ")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='k-popup-account-login'][style] div[class='k-popup-account-mb-content']"))
                .isDisplayed());
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input#user-login")).
                sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        sleepInSecond(2);
        //driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

    }

    @Test
    public void FixedPopupNotinDOM_03() {
        driver.get("https://tiki.vn/");
        WebElement closeButton = driver.findElement(By.xpath("//img[@class='styles__StyledImg-sc-p9s3t3-0 hbqSye']"));
        // Nhấp vào nút đóng pop-up
        closeButton.click();
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']"))
                .click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(
                        By.cssSelector("div.ReactModal__Content"))
                .isDisplayed());
        driver.findElement(By.cssSelector("p.login-with-email"))
                .click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//button[text()='Đăng nhập']"))
                .click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(
                        By.xpath("//span[text()='Email không được để trống']"))
                .getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(
                        By.xpath("//span[text()='Email không được để trống']"))
                .getText(), "Email không được để trống");
        driver.findElement(By.cssSelector("img.close-img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(), 0);
    }
    @Test
    public void FixedPopupNotinDOM_04(){
        driver.get("https://www.facebook.com/");
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000L);
        } catch (InterruptedException var4) {
            InterruptedException e = var4;
            throw new RuntimeException(e);
        }
    }
}
