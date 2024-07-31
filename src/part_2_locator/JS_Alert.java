package part_2_locator;
import java.time.Duration;
import  org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class JS_Alert {
    WebDriver driver;
    WebDriverWait expliciWait;
    String username="admin";
    String password="admin";
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        expliciWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void TC_01_AcceptAlert(){
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

//        //Accept alert
//        driver.switchTo().alert().accept();
//        // cancel alert
//        driver.switchTo().alert().dismiss();
//        // get tex bên trong description
//        driver.switchTo().alert().getText();
//        // nhap text vao alert
//        driver.switchTo().alert().sendKeys("bcg");
//             Tu dong switch qua+ wait
        Alert alert =expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
    }
    @Test
    public void TC_02_ConfirmAlert(){
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert1 =expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert1.getText(), "I am a JS Confirm");
        alert1.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_03_PromptAlert(){
        driver.get("https://automationfc.github.io/basic-form/");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert2 =expliciWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert2.getText(), "I am a JS prompt");
        String value="Automation FC";
        alert2.sendKeys(value);
        alert2.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: "+ value);
    }
    @Test
    public void  TC_04_Authentication_URL(){
        // http/https: + username +: + pass + @URL
        driver.get("https://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }
    @Test public void TC_05_Authentication_NavigateURL(){
        driver.get("https://the-internet.herokuapp.com/");
        String BasicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenticationURL(BasicAuthLink, username, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(),
                "Congratulations! You must have the proper credentials.");
    }

    public  String getAuthenticationURL(String link, String username, String password){
        String linkArray[]=link.split("//");
        return linkArray[0]+ "//" + username + ":" + password + "@" + linkArray[1];
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }

}
