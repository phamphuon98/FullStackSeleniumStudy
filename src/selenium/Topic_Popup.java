package selenium;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Popup {
    WebDriver driver;
    String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";

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
        sleepInSecond(5);
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
    public void FixedPopupNotinDOM_04() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"))
                .click();
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(
                        By.xpath("//div[text()='Đăng ký']/parent::div/parent::div"))
                .isDisplayed());
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img"))
                .click();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElements(
                        By.xpath("//div[text()='Đăng ký']/parent::div/parent::div"))
                .size(), 0);
    }

    @Test
    public void TC_05_RandomPopupNotInDom() {
        driver.get("https://dehieu.vn/");
        sleepInSecond(15);
        By MarketingPopup = By.cssSelector("div#modalPopupForm div.modal-dialog");
        if (driver.findElements(MarketingPopup).size() > 0 &&
                driver.findElements(MarketingPopup)
                        .get(0).isDisplayed()) {
            System.out.println(" Popup hien thi");
            int HeightBrower = driver.manage().window().getSize().getHeight();
            System.out.println("do phan giai: " + HeightBrower);
            sleepInSecond(2);
            if (HeightBrower < 1920 ){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",
                        driver.findElement(By.cssSelector("button.close")));
            }
            else {
                driver.findElement(By.cssSelector("button#btn-close")).click();
            }
            sleepInSecond(5);

        }


    }

    @Test
    public void TC_06_Random_Popup_In_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        sleepInSecond(15);
//

        By lePopup = By.cssSelector("div.lepopup-popup-container > div:not([style^='display:none'])");

        //List<WebElement> popups = driver.findElements(lePopup);
        WebElement popup = null;
        try {
            popup = driver.findElement(lePopup);
        } catch (Exception ex) {
            System.out.println("Not found Element");
        }
//        boolean isDisplayPopup = popups.stream().anyMatch(WebElement::isDisplayed);
        if (popup != null && popup.isDisplayed()) {
            List<WebElement> buttonExits = driver.findElements(By.cssSelector("div.lepopup-form[data-title='Newsletter-Books Anime Brief - NEW']>div.lepopup-form-inner>div>div>a[onclick='return lepopup_close();']"));
            for (WebElement button : buttonExits) {
                if (button.isDisplayed()) {
                    button.click();

                }
            }
        }

        String articleName = "Agile Testing Explained";

        driver.findElement(By.cssSelector("input#search-input")).sendKeys(articleName);
        sleepInSecond(3);
        WebElement button = driver.findElement(By.id("search-submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        sleepInSecond(15);

        Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container>li:first-child h2 a")).getText(),
                articleName);
//
//
//        if (driver.findElement(lePopup).isDisplayed()) {
//
//            // input email address
//            driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
////            driver.findElement(
////                            By.xpath("//div[@class='lepopup-element lepopup-element-13']//div/div/div[@class='lepopup-cr-box']/label"))
////                    .click();
//            WebElement label = driver.findElement(By.xpath("//div[@class='lepopup-element lepopup-element-13']//div/div/div[@class='lepopup-cr-box']/label"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
//            driver.findElement(By.cssSelector("div[data-type='button'] a")).click();
//            sleepInSecond(10);
//
////
//            Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText()
//                    .contains("Your sign-up request was successful. We will contact you shortly. Please "));
//
//            sleepInSecond(15);
//        }


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
