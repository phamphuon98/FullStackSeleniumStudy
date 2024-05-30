package part_2_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_01_Relative_Locator {
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
        // driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        //Login button
        By loginButtonBy = By.cssSelector("button.login-button");

        WebElement loginButtonWebElement = driver.findElement(By.cssSelector("button.login-button"));
        RelativeLocator.with(By.tagName("label")).above(loginButtonBy);

        //Remember me checkbox
        By rememberMeCheckboxBy = By.id("RememberMe");

        //Forgot password Link
        WebElement forgotPasswordElement = driver.findElement(By.cssSelector("span.forgot-password"));

        // Password textbox
        By passwordTextboxBY = By.cssSelector("input[id='Password']");

// GUI (location/position)
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy).toLeftOf(forgotPasswordElement)
                        .below(passwordTextboxBY)
                        .near(forgotPasswordElement));
        System.out.println(rememberMeTextElement.getText());
        List<WebElement> allLinks = driver.findElements(RelativeLocator.with(By.tagName("a")));
        System.out.println(allLinks.size());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}