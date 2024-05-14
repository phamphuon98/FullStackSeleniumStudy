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
import java.util.List;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class WebBrowser {
    WebDriver driver;
    String osName= System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    By emailTextbox=By.cssSelector("#mail");
    By Under18=By.cssSelector("#under_18");
    By edu=By.cssSelector("#edu");
    By user5=By.xpath("//h5[text() = 'Name: User5']");
    By Ed =By.xpath("//div[@class='container']//label[text()='Education:']");



    @BeforeClass
    public void beforeClass() {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browerDriver/geckodriver");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // driver.get("https://demo.nopcommerce.com/register");
    }
    @Test public void TC01_Displayed(){
        //Truy cap vao trang
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // Check email textbox
        if (driver.findElement(emailTextbox).isDisplayed()){
            driver.findElement(emailTextbox).sendKeys("ptphuong.hnue@gmail.com");
            System.out.println("Email textbox is Displayed");

        }
        else {
            System.out.println("Email textbox is not Displayed");
        };
        //Check hiển thị Age(Under 18) radio
        if (driver.findElement(Under18).isDisplayed()) {
            System.out.println("Age Under 18 radio is Displayed");

        }
        else {
            System.out.println("Age Under 18 radio is not Displayed");
        }

        //Check Name : User5 not displayed
        if (driver.findElement(user5).isDisplayed())
            System.out.println("Name: User5 is Displayed");
        else {
            System.out.println("Name: User5 is not Displayed");
        }
        if (driver.findElement(Ed).isDisplayed())
        {
            System.out.println("Ed: Education is Displayed");
        }
        else {
            System.out.println("Ed: Education is not Displayed");
        }
    }






    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

