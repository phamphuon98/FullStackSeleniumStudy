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
import java.util.Random;
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
    By MyAcc=By.xpath("//div[@class='footer-container']//a[text()='My Account']");
    By MailAddr = By.cssSelector("#email");
    By pass =By.xpath("//div[@class='input-box']//input[@id='pass']");
    By button =By.cssSelector("#send2");
    By Passmess= By.xpath("//input[@id='pass']/parent::div[@class='input-box']//div[@class='validation-advice']");
    By Mailmess =By.xpath("//input[@id='email']/parent::div[@class='input-box']//div[@class='validation-advice']");



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

    // Element
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
    @Test public void TC_02_isEnabled(){
        //step 1
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //step 2
        //emailTextbox is enable
        if (driver.findElement(emailTextbox).isEnabled()){
            System.out.println("Element is Enabled");
        }
        else {
            System.out.println("Element is not Enabled");
        }
        //Under18 is enable
        if (driver.findElement(Under18).isEnabled())
        {
            System.out.println("Under18 is Enabled");
        }
        else {
            System.out.println("Under18 is not  Enabled");
        }
        //Ed is enable
        if(driver.findElement(Ed).isEnabled()){
            System.out.println("Ed is  Enabled");
        }
        else {
            System.out.println("Ed is not  Enabled");
        }
        ///job1 is enable
        if (driver.findElement(By.cssSelector("#job1")).isEnabled()){
            System.out.println("job1 is enable");
        }
        else {
            System.out.println("job1 is not enable");
        }
        if (driver.findElement(By.cssSelector("#development")).isEnabled()){
            System.out.println("development is enable");
        }
        else
        { System.out.println("development is disable");}
        //#slider-1
        if (driver.findElement(By.cssSelector("#slider-1")).isEnabled())
        {
            System.out.println("slider is enable");
        }
        else
        { System.out.println("slider is disable");}
        //step 3

        //pass is disable
        if (driver.findElement(By.cssSelector("#disable_password")).isEnabled())
        {
            System.out.println("password is enable");
        }
        else {
            System.out.println("Password is disabled");
        }
        // Radio button is disabled
        if (driver.findElement(By.cssSelector("#radio-disabled")).isEnabled()){
            System.out.println(" Radio button is enable");
        }
        else {
            System.out.println(" Radio button is disabled");
        }
        // Biography is disabled
        if (driver.findElement(By.cssSelector("#bio")).isEnabled()){
            System.out.println("Biography is enable");
        }
        else {
            System.out.println("Biography is disable");
        }

        //Job Role 03 - Disable
        if (driver.findElement(By.cssSelector("#job2")).isEnabled()){
            System.out.println("ob Role 03 is enable");
        }
        else {
            System.out.println("ob Role 03 is disable");
        }
        // Checkbox is disabled
        if (driver.findElement(By.cssSelector("#check-disbaled")).isEnabled()){
           System.out.println("Checkbox is enable");
        }
        else {
            System.out.println("Checkbox is disable");
        }
        //Slider 02 (Disabled)
        if (driver.findElement(By.cssSelector("#slider-2")).isEnabled()){
            System.out.println("Slider 02 is enable");
        }
        else
        {
            System.out.println("Slider 02 is disable");
        }
       }
       @Test public void TC_04_MailChimp(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("#email")).sendKeys("ptphuong.hnue@gmail.com");
        By passwordTextbox = By.id("new_password");
        driver.findElement(passwordTextbox).sendKeys("fja");

        // verify chu thuong
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='lowercase-char completed']")).isDisplayed());
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='uppercase-char not-completed']")).isDisplayed());
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='number-char not-completed']")).isDisplayed());
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='special-char not-completed']")).isDisplayed());
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='8-char not-completed']")).isDisplayed());
           Assert.assertTrue(driver.findElement(By.xpath("//li[@class ='username-check completed']")).isDisplayed());
       }

    //login
    @Test public void TC_01_Loginempty(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(MyAcc).click();
        driver.findElement(MailAddr).clear();
        driver.findElement(pass).clear();
        driver.findElement(button).click();
    }
    @Test public void TC_02_invalidEmail(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(MyAcc).click();
        driver.findElement(MailAddr).clear();
        driver.findElement(MailAddr).sendKeys("123434234@12312.123123");
        driver.findElement(pass).clear();
        driver.findElement(pass).sendKeys("123456");
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(Mailmess).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");


    }
    @Test public void TC_03_invalidPassword(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(MyAcc).click();
        driver.findElement(MailAddr).clear();
        driver.findElement(MailAddr).sendKeys("automation@gmail.com");
        driver.findElement(pass).clear();
        driver.findElement(pass).sendKeys("123");
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(Passmess).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
        driver.findElement(By.xpath(""));
    }
    public  String getmaillAddress(){
        Random rand = new Random();
        //rand.nextInt(99999);
        String emailAddress ="automation" + rand.nextInt(999999) + "@gmail.net";
        return  emailAddress;
        // return "automation" + rand.nextInt(999999) + "@gmail.net"
    }

//    @AfterClass
//    public void afterClass() {driver.quit();
//    }
}









