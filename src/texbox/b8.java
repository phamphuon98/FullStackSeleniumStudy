package texbox;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class b8 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String firtName, lastName, emailTextbox, pass;
    Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
    Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
    Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        firtName = "john";
        lastName = "Wick";
        emailTextbox = firtName + randomNumber() + "@gmail.com";
        pass = "123456abc";


    }

    @Test
    public void TC_01_Register_New_Account() {
        driver.get("https://demo.nopcommerce.com/register");
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@value='F']")).click();
        driver.findElement(By.name("firtName")).sendKeys(firtName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        day.selectByVisibleText("1");
        //khai bao bien
//        List <WebElement> dayOptions = day.getOptions();
//        Assert.assertEquals(dayOptions.size(), 30);
        Assert.assertEquals(day.getOptions(), 32);
        month.selectByVisibleText("May");
        Assert.assertEquals(month.getOptions(), 13);
        year.selectByVisibleText("1980");
        Assert.assertEquals(year.getOptions(), 112);
        driver.findElement(By.name("Email")).sendKeys(emailTextbox);
        driver.findElement(By.name("Password")).sendKeys(pass);
        driver.findElement(By.name("ConfirmPassword")).sendKeys(pass);

        // Click button
        driver.findElement(By.id("register-button")).click();
        sleepInSecond(5);

        // Verify register complete
        Assert.assertEquals(driver.findElement(By.className("result")), "Your registration completed");

        // My Account
        driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).click();
        Assert.assertEquals(day, "1");
        Assert.assertEquals(month, "May");
        Assert.assertEquals(year, "1980");


    }
    @Test
    public void tc_02() {
        List<WebElement> alliteams = driver.findElements(By.xpath("//a[text()='Alliances']"));
        for (WebElement item : alliteams) {
            String textitem=item.getText();
            System.out.println("Textitem: "+textitem);
            if (textitem.equals("8")){
                item.click();
                break; //9-19 k ktra
            }

        }
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int randomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    @AfterClass
    public void afterClass() {
//		driver.quit();
    }

}
