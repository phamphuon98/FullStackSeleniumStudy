package Textbox_TextArea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class P8_Textbox_TextArea {
    String osName= System.getProperty("os.name");
    String projectPath =System.getProperty("user.dir");
    WebDriver driver;
    String pass ="admin123";
    String name="Admin";
    String fistName="Automation";
    String LastName="FC";
    String passportNumber = "40517-402-96-7202";
    String comment = "This is generated data \nof real people";

    @BeforeClass
    public void BeforeClass()
    {
        System.out.println(osName);
        System.out.println(projectPath);
        if (osName.contains("Window"))
        { System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");}
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test public  void TC_01_Create_New_Employee(){
        //Login
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys(name);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        //Click BIM
        driver.findElement(By.xpath("//span[text()='PIM']")).click();

        //chon employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();

        //Nhap fist name va lastname
        driver.findElement(By.name("firstName")).sendKeys(fistName);
        driver.findElement(By.name("lastName")).sendKeys(LastName);

        // get du lieu lu vao bien
        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input")).getAttribute("value");
        sleepInSecond(10);

        //click vaof button
        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();
        sleepInSecond(3);
        String username = "abc" + employeeID;

        //
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(pass);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(pass);
        // click button save
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();

        //verify personal Details
        Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), fistName);
        Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), LastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);

        //immigration
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button/i")).click();

        //Add immigration
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
        driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();

        //click vaof icon pencil
        driver.findElement(By.cssSelector(".bi-pencil-fill")).click();

        //verify du lieu
        Assert.assertEquals(
                driver.findElement(By.xpath("//label[text()='Number']//parent::div/following-sibling::div/input"))
                        .getAttribute("value"),
                passportNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea"))
                .getAttribute("value"),
                comment);

        //logout
        driver.findElement(By.xpath("//p[text()='jfhjgvnb user']")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
