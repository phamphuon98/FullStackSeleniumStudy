package part_2_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class texbox2{
    WebDriver driver;
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {

//        if (osName.contains("Windows")) {
//            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//        } else {
//            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Register_New_Account() {
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.name("uid")).sendKeys("mngr574580");
        driver.findElement(By.name("password")).sendKeys("UgytumY");
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(By.xpath("/input[@name='name']")).sendKeys("Selenium Online");
        


    }

    public void sleepInSeconds(long TimeInSeconds) {
        try {
            Thread.sleep(TimeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterclass() {

        //driver.quit();
    }

}

