package part_2_locator;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class topic_17_Action {
    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys keys;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
        keys=osName.contains("Windows") ? Keys.CONTROL:Keys.COMMAND;

    }
    @Test public  void TC_01_hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

    }
    @Test public  void TC_02_hoverMyntra()throws InterruptedException {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        Thread.sleep(2000);
        actions.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(),"Kids Home Bath");
        Thread.sleep(2000);
    }
    @Test public  void TC_03_hoverfahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu']"))).perform();
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='FOREIGN BOOKS']"))).perform();
        Thread.sleep(2000);
        actions.click(driver.findElement(By.xpath("//div[@class='dropdown-menu']//a[text()='Romance']"))).perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//li//strong[text()='Romance']")).isDisplayed());

    }
    @Test public  void TC_04_ClickAndHold() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List <WebElement> ListNum = driver.findElements(By.cssSelector("ol#selectable>li"));
        // di chuot tu 1-> 4 và click
        Assert.assertEquals(ListNum.size(),20);
//        actions.clickAndHold(ListNum.get(0))
//                .moveToElement(ListNum.get(3))
//                .release()//nha chuot trai
//                .perform(); // thuc thi
        //List <WebElement> ListNum2 = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
       // Assert.assertEquals(ListNum2.size(),4);
        actions.keyDown(keys).perform();
        actions.click(ListNum.get(0))
                .click(ListNum.get(2))
                .click(ListNum.get(4))
                .click(ListNum.get(6))
                .click(ListNum.get(8))
                .perform();
        actions.keyUp(keys).perform();
        List <WebElement> ListNum2 = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(ListNum2.size(),5);
    }
    @Test public void TC_05_DoubleClick()throws InterruptedException{
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement button = driver.findElement(By.xpath("//button[text()='Double click me']"));
        // Cuộn đến phần tử bằng JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(3000);
        actions.doubleClick(button).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

    }
    @Test public  void TC_06_RigtClick()throws InterruptedException{
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        Thread.sleep(2000);
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']")))
                .perform();
        Thread.sleep(2000);
        By Quit = By.xpath("//li//span[text()='Quit']");
        Assert.assertTrue(driver.findElement(Quit).isDisplayed());
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(Quit)).perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(
                By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
                .isDisplayed());
        actions.click(driver.findElement(Quit)).perform();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        Assert.assertFalse(driver.findElement(Quit).isDisplayed());
    }

    @Test public  void TC_07_DragAndDropHTML4()throws InterruptedException{
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement SmallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement BigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        actions.dragAndDrop(SmallCircle,BigCircle).perform();
        Thread.sleep(3000);
        Assert.assertEquals(BigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(BigCircle.getCssValue("background-color"))
                .asHex()
                .toLowerCase(),"#03a9f4");

    }
    @Test public  void TC_07_DragAndDropHTML5()throws InterruptedException{

        
    }



}
