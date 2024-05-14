package part_2_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Element {
    String osName= System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    WebDriver driver;
    By ACCOUNT = By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']");
    By MyAcc = By.xpath("//div[@id='header-account']//a[text()='My Account']");
    By createAcc = By.xpath("//a[@class='button']");
    By myAcc = By.xpath("//div[@class='footer']//a[text()='My Account']");
     By CreatBCA =By.xpath("//div[@class='col2-set']//span[text()='Create an Account']");
    String username, pass;
    @BeforeClass
    public void BeforeClass(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.dir"));
        if (osName.contains("Window")){ System.setProperty("webdriver.gecko.driver", projectPath + "\\browerDriver\\geckodriver.exe");}
        else System.setProperty("webdriver.gecko.driver", projectPath + "/browerDriver/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
//    public static void main(String[] args) {
//        //member1
//        boolean member01;
//        boolean member02;
//        boolean result;
//        member01 = true;
//        member02 = true;
//        System.out.println("result: " + (member01 && member02));
//        // member01 || member02
//
//    }
    @Test public void TC_01(){
        driver.get("http://live.techpanda.org/");
        //driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        //driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
        //String UrlLoginPage = driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).getText();
        driver.findElement(ACCOUNT).click();
        driver.findElement(MyAcc).click();
        //sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(createAcc).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }
    @Test public  void  TC_02(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(myAcc).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(By.xpath("//div[@class='col2-set']//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }
    @Test public  void TC_03(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(myAcc).click();
        driver.findElement(CreatBCA).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test public void TC_04(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(myAcc).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(CreatBCA).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
//ELEMENT


    @Test public void Register(){
        driver.get("https://demo.guru99.com/");
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        pass = driver.findElement(By.xpath("//td[@class='accpage']/following-sibling::td")).getText();


    }

    @Test public void login(){
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.xpath("//td[text()='UserID']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

    }
//
}
