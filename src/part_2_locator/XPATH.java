package part_2_locator;

import org.bouncycastle.dvcs.DVCSRequestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static graphql.Assert.assertTrue;

public class XPATH{
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
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        // driver.get("https://demo.nopcommerce.com/register");
    }
    @Test
    public void TC_01_(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
        String errorTxtFirstName=driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
        Assert.assertEquals(errorTxtFirstName,"Vui lòng nhập họ tên");

        String errortxtEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
        Assert.assertEquals(errortxtEmail,"Vui lòng nhập email");

        String errortxtCEmail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
        Assert.assertEquals(errortxtCEmail,"Vui lòng nhập lại địa chỉ email");

        String errortxtPassword=driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
        Assert.assertEquals(errortxtPassword,"Vui lòng nhập mật khẩu");

        String errortxtCPassword=driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
        Assert.assertEquals(errortxtCPassword,"Vui lòng nhập lại mật khẩu");

        String errortxtPhone =driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
        Assert.assertEquals(errortxtPhone,"Vui lòng nhập số điện thoại");

        //String errorTxtFirstName = driver.findElement(By.xpath("Vui lòng nhập họ tên")).getText();
        // String errorTxtFirstName = "Vui lòng nhập họ tên";
        //assertTrue(errorTxtFirstName.equals(txtFirstname.getText()), () -> "Error First Name message is incorrect !!!");
    //input[@id='txtFirstname']

    };
    @Test
    public void TC_02_(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Thi Phuong");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0331259600");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
        String errortxtCPassword=driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
        Assert.assertEquals(errortxtCPassword,"Vui lòng nhập email");

    };
    @Test
    public void TC_03_(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement((By.xpath("//input[@id='txtFirstname']"))).sendKeys("Pham Thi Phuong");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("ptphuongb.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0331259600");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
        String txtCEmailerror = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
        Assert.assertEquals(txtCEmailerror,"Email nhập lại không đúng");
    }

    @Test
    public void  TC_04_(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Thi Phuong");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcv1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcd1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0364506728");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
//        String txtPassworderror =driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
//        Assert.assertEquals(txtPassworderror,"Vui lòng nhập mật khẩu");
        String txtCPassworderror =driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
        Assert.assertEquals(txtCPassworderror,"Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void TC_05(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Thi Phuong");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcv1");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcv1");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0364506728");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
        String txtPassworderror =driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
        Assert.assertEquals(txtPassworderror,"Mật khẩu phải có ít nhất 6 ký tự");
        String txtCPassworderror =driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
        Assert.assertEquals(txtCPassworderror,"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_06(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Pham Thi Phuong");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("ptphuong.hnue@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("abcv1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("abcv1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("113645067281");
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
        String txtPhoneerror =driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
        Assert.assertEquals(txtPhoneerror, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
    @Test
    public  void  TC_07(){
        driver.get("https://automationfc.github.io/basic-form/");

        //truỳen text va locator để hiển thị
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']")).isDisplayed();
    }
    @Test public  void TC_08(){
       String nestedText =driver.findElement(By.xpath("//h5[@id='nested']")).getText();
       System.out.println(nestedText);
    };

    //driver.findElement(By.id("FirstName")).sendKeys("abchfs s");
//    @AfterClass
//    public void afterClass(){
//        driver.quit();
//    }
}


