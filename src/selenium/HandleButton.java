package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandleButton {
    WebDriver driver;
    WebDriverWait expliciWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Ngam dinh: khong ro rang cho 1 trang thai cu the nao
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // cho thoi gian hien thi het item cua dropdowlist
        //co nhung  case item k visible het tat ca(Agular/react/..)
        //expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
        //Xuat hien trong HTML
        //expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

    }

    @Test
    public void TC_01_EgovButton() {
        driver.get("https://egov.danang.gov.vn/reg");
        //verify button disable khi chua click
        WebElement RegisterButton = driver.findElement(By.cssSelector("input.egov-button"));
        Assert.assertFalse(RegisterButton.isEnabled());
        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSecond(2);

        //verify button duoc enable
        Assert.assertTrue(RegisterButton.isEnabled());
        //lay ra ma mau cua button
        String registerBackgroundcolorRGB = RegisterButton.getCssValue("background-color");
        System.out.println("BackgroundRGB: " + registerBackgroundcolorRGB);
        //Convert qua kieu hexa
        Color registerBackgroundcolor = Color.fromString(registerBackgroundcolorRGB);
        String registerBackgroundcolorHexa = registerBackgroundcolor.asHex();
        System.out.println("Background Hexa: " + registerBackgroundcolorHexa);
        Assert.assertEquals(registerBackgroundcolorHexa, "#ef5a00");
        // chuyen sang viet hoa
        System.out.println("Background Hexa: " + registerBackgroundcolorHexa.toUpperCase());

    }

    @Test
    public void TC_02_fahasabutton() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSecond(3);
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());
        //verify mau cua button
        //System.out.println(loginButton.getCssValue("background-color"));
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(), "#000000");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456abc@");
        sleepInSecond(3);
        Assert.assertTrue(loginButton.isEnabled());
        System.out.println(loginButton.getCssValue("background-color"));
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
    }

    @Test
    public void tc_03_default_telerik_checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By Dualcheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span//descendant::input");
        By Rearcheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span//descendant::input");
        sleepInSecond(3);
        //click button đã chọn
        driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
        sleepInSecond(3);
        driver.findElement(Dualcheckbox).click();
        // verify checkbox đã được chọn
        Assert.assertTrue(driver.findElement(Dualcheckbox).isEnabled());
        //1. app mở ra checkboox đã được chọn
//        if (!driver.findElement(Dualcheckbox).isSelected()) {
//            driver.findElement(Dualcheckbox).click();
//            sleepInSecond(3);
//        }
        checkToElement(Dualcheckbox);
        //2.app mở ra checkbox chưa được chọn
//        if (!driver.findElement(Rearcheckbix).isSelected()) {
//            driver.findElement(Rearcheckbix).click();
//            sleepInSecond(3);
//        }
        checkToElement(Rearcheckbox);


        //bo chon 2 checkbox
//        if (driver.findElement(Dualcheckbox).isSelected()) {
//            driver.findElement(Dualcheckbox).click();
//            sleepInSecond(3);
//        }
        UncheckToElement(Dualcheckbox);
//        //2.app mở ra checkbox chưa được chọn
//        if (driver.findElement(Rearcheckbix).isSelected()) {
//            driver.findElement(Rearcheckbix).click();
//            sleepInSecond(3);
//        }
        UncheckToElement(Dualcheckbox);
        UncheckToElement(Rearcheckbox);
        //verify checkbox duoc chon thanh cong
//        Assert.assertTrue(driver.findElement(Dualcheckbox).isSelected());
//        Assert.assertTrue(driver.findElement(Rearcheckbox).isSelected());
        Assert.assertFalse(driver.findElement(Dualcheckbox).isSelected());
        Assert.assertFalse(driver.findElement(Rearcheckbox).isSelected());
        sleepInSecond(3);
    }

    @Test
    public void tc_04_default_telerik_radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By TwoPetroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span//child::input");
        By TwoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span//child::input");

        sleepInSecond(5);
        driver.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
        sleepInSecond(5);
        //click 1 trong 2
        checkToElement(TwoPetroRadio);
        sleepInSecond(3);
        //verify
        Assert.assertTrue(driver.findElement(TwoPetroRadio).isSelected());
        Assert.assertFalse(driver.findElement(TwoDieselRadio).isSelected());
        sleepInSecond(3);
        //click button khacs
        checkToElement(TwoDieselRadio);
        Assert.assertTrue(driver.findElement(TwoDieselRadio).isSelected());

    }

    @Test
    public void tc_05_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allcheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type=checkbox]"));
        //chon het checkbox trong man hinh do
        for (WebElement checkbox : allcheckbox) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                sleepInSecond(3);
            }
        }
        //verify all checkbox da duoc chon
        for (WebElement checkbox : allcheckbox) {
            Assert.assertTrue(checkbox.isSelected());
            sleepInSecond(3);
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        allcheckbox = driver.findElements(By.cssSelector("input[type=checkbox]"));

        //chon 1 checkbox/radio trong so tat cả checkbox/radio
        for (WebElement checkbox : allcheckbox) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
                sleepInSecond(1);
            }
        }
        //verify checkbox duoc chon
        for (WebElement checkbox : allcheckbox) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }
    }

    @Test
    public void tc_06_Custom_Radio() {
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.cssSelector("")).click();
        sleepInSecond(3);
        //cach 1
//        ((JavascriptExecutor)driver).executeScript("");
//        JavascriptExecutor jsExecuor = (JavascriptExecutor) driver;
//        jsExecuor.executeScript("");
        //cach 2
        By registerRadio = By.cssSelector("bbb");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerRadio);
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());
        // tham so de click:arguments


    }

    @Test
    public void TC_customGoogleDocs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By HCMRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");
        By QuangNamcheckbox = By.xpath("//span[text()='Quảng Nam']");
        //driver.findElement(HCMRadio).getAttribute("aria-checked");
        //verify radio not selected
        Assert.assertEquals(driver.findElement(HCMRadio).getAttribute("aria-checked"), "false");
//        String HCMRadioValue =driver.findElement(HCMRadio).getAttribute("aria-checked");
//       Assert.assertEquals(HCMRadioValue,"fasle");
        //cach 2--> khong dung
        //Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh' and @aria-checked='false' ]")).isDisplayed());
        WebElement element = driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Hồ Chí Minh']")).getAttribute("aria-checked"), "true");
        sleepInSecond(2);
        WebElement elementQN = driver.findElement((By.xpath("//span[text()='Quảng Nam']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementQN);
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Quảng Nam']")).getAttribute("aria-checked"), "true");


    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkToElement(By xpath) {
        if (!driver.findElement(xpath).isSelected()) {
            driver.findElement(xpath).click();
            sleepInSecond(3);
        }
    }

    public void UncheckToElement(By xpath) {
        if (driver.findElement(xpath).isSelected()) {
            driver.findElement(xpath).click();
            sleepInSecond(3);
        }
    }

    @AfterClass
    public void afterClass() {
//		driver.quit();
    }

}
