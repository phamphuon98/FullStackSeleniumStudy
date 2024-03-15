package part_2_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic01 {
    String osName = System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("abchfs s");
//        System.out.println(driver.findElement(By.id("FirstName")));
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElement(By.tagName("input"));
    }
    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Conditions of Use"));

    }
    @Test
    public void TC_06_Partial_Link() {
        driver.findElement(By.partialLinkText("Compare"));
    }
    @Test
    public void TC_07_Css() {
        //CSS ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));
        //CSS ClASS
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //CSS Name
      driver.findElement(By.cssSelector("input[name='FirstName']"));
      // CSS Tagname
        driver.findElement(By.cssSelector("input"));

        //CSS LINK
        driver.findElement(By.cssSelector("a[href='/contactus']"));

        //CSS partial link
        driver.findElement(By.cssSelector("a[href*='/compare']"));
        //driver.findElement(By.cssSelector("a[href^='/compare']"));
        //driver.findElement(By.cssSelector("a[href$='/compare']"));

    }
    @Test
    public void TC_07_XPath() {
        //XPath ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //XPath ClASS
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //XPath Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));
        // XPath Tagname
        driver.findElement(By.xpath("//input"));

        //XPath LINK
        driver.findElement(By.xpath("//a[@href='/contactus']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));

        //XPath partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));


    }
    @AfterClass
    public  void afterClass()
    {
        //driver.quit();
    }
}