package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web_Element {
    WebDriver driver;
    Random rand;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    String emailAddress;
    String fullname;
    String firstName;
    String middleName;
    String lastName;

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver = new FirefoxDriver();
        rand = new Random();
        emailAddress = "phuong" + rand.nextInt(9999) + "@gmail.com";
        firstName = "phuong";
        middleName = "thi";
        lastName = "pham";
        fullname = firstName + " " + middleName + " " + lastName;
    }
    @Test public void TC_Create_New_Account(){
        //TRuy cap
        driver.get("http://live.techpanda.org/");

        //Nhap thong tin
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("middlename")).sendKeys(middleName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys("Huyen304!");
        driver.findElement(By.id("confirmation")).sendKeys("Huyen304!");
        driver.findElement(By.xpath("//button[@class='button']//span[text()='Register']")).click();

        //

        String contactInf = driver
                .findElement(By.xpath(
                        "//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div/p"))
                .getText();
        Assert.assertTrue(contactInf.contains(fullname));
        Assert.assertTrue(contactInf.contains(emailAddress));
        driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'http://live.techpanda.org/media/catalog/product/cache/1/small_image/210x/9df78eab33525d08d6e5fb8d27136e95/s/a/samsung_2.png')]")).isDisplayed());
        




    }

}
