package selenium;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class HandleWindow_Tap {
    WebDriver driver;
    Alert alert;
    WebDriverWait expliciWait;
    Properties props;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        props = new Properties();
        var envFile = Paths.get(System.getProperty("user.dir") + "/.env");
        try (var inputStream = Files.newInputStream(envFile)) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(System.getenv("PP_PWD"));
    }

    @Test
    public void TC_01_Basicform() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String githubID = driver.getWindowHandle();
        System.out.println(driver.getCurrentUrl());
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);
        switchTitleExpect("Google");
        driver.findElement(By.xpath("//textarea[@name='q']"))
                .sendKeys("Selenium");
        sleepInSecond(3);
        switchTitleExpect("Selenium WebDriver");
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSecond(3);
        switchTitleExpect("Facebook – log in or sign up");
        sleepInSecond(2);
        switchTitleExpect("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSecond(3);
        switchTitleExpect("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        closeAllWindownWithoutParentID(githubID);


    }

    @Test
    public void TC_02_Techpanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSecond(3);
        driver.findElement
                        (By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']"))
                .click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).getText()
                , "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']"))
                .click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(
                        By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).getText()
                , "The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.xpath("//button[@class='button' and @type='button']")).click();
        sleepInSecond(3);
        switchTitleExpect("Products Comparison List - Magento Commerce");
        sleepInSecond(3);
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
        driver.close();
        switchTitleExpect("Mobile");
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSecond(1);
        alert = expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),
                "Are you sure you would like to remove all products from your comparison?");
        alert.accept();
        sleepInSecond(5);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Dictionary() {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.xpath("//header//span[text()='Đăng nhập']")).click();
        sleepInSecond(2);
        switchTitleExpect("Login");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input.gigya-input-submit[value='Log in']")).click();
        sleepInSecond(5);
        Assert.assertEquals(
                driver.findElement(By.cssSelector("div#login_content span[data-bound-to = 'loginID']")).getText(),
                "This field is required");
        Assert.assertEquals(
                driver.findElement(By.cssSelector("div#login_content span[data-bound-to = 'password']")).getText(),
                "This field is required");
        driver.close();
        sleepInSecond(3);
        String Keyword = "Shadow";
        switchTitleExpect("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keyword);
        driver.findElement(By.cssSelector("button.cdo-search-button i.i-search[aria-hidden='true']")).click();
        //driver.findElement(By.cssSelector("input#searchword")).sendKeys(Keys.ENTER);
        sleepInSecond(3);
        switchTitleExpect(Keyword.toUpperCase() + " " + "| Định nghĩa trong Từ điển tiếng Anh Cambridge");
        List<WebElement> ListElements = driver.findElements(By.xpath("//div[@data-id= 'cald4']//descendant::div[@class='di-title']//span[text()='shadow']"));
        Assert.assertEquals(ListElements.get(0).getText(), Keyword.toLowerCase());

    }

    @Test
    public void TC_04_Harvard() {
//        String fbPwd = String.valueOf(props.get("FB_PWD"));
//        String fbUser = String.valueOf(props.get("FB_USER"));
        driver.get("https://courses.dce.harvard.edu/");
        sleepInSecond(3);
        driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[i[@class='fa fa-sign-in']]")).click();
        sleepInSecond(4);
        switchTitleExpect("Harvard Division of Continuing Education Login Portal");
        sleepInSecond(4);
        Assert.assertTrue(driver.findElement(By.cssSelector("section._prompt-box-outer div")).isDisplayed());
        sleepInSecond(2);
        driver.close();
        sleepInSecond(2);
        switchTitleExpect("DCE Course Search");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.sam-wait__error")).getText(), "No authentication token was provided.");
        sleepInSecond(2);
        By span = By.xpath("//span[text()='Close']");
        WebElement element = driver.findElement(span);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);


    }

    @Test
    public void TC_seleniumV4() {
//        String fbPwd = String.valueOf(props.get("FB_PWD"));
//        String fbUser = String.valueOf(props.get("FB_USER"));
        String mail = String.valueOf(props.get("Mail"));

        driver.get("https://kynaenglish.vn/");
        System.out.println("KynaID: " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        //window moi-driver nhay qua window moi nay nhung k tao ra driver moi
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://kynaenglish.vn/programs");
        System.out.println("KynaID1: " + driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());



        WebDriver facebookDriver = driver.switchTo().newWindow(WindowType.TAB);
        facebookDriver.get("https://www.facebook.com/kyna.vn");
        System.out.println("fbID: " + facebookDriver.toString());
        facebookDriver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']"))
                .sendKeys(mail);
        sleepInSecond(3);
        switchTitleExpect("KYNA ENGLISH PROGRAMS - TỪNG LỘ TRÌNH TIẾNG ANH BẠN CẦN");
        driver.findElement(By.cssSelector("div.header_wrapper_flex__nIBLx button.Elements_btn_cta__75y7H"))
                .click();
    }

    public void switchTitleExpect(String ExpectedTitle) {
        //lay ra all id cua tap
        Set<String> allsIDs = driver.getWindowHandles();
        //duyet qua cac id
        for (String id : allsIDs) {
            // switch qua id dang duyet
            driver.switchTo().window(id);
            //get ra title cua Tap dang xet
            String actualTitle = driver.getTitle();
            //
            if (actualTitle.equals(ExpectedTitle)) {
                break;
            }
        }


    }

    public void closeAllWindownWithoutParentID(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
