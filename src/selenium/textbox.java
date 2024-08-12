package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class textbox {
    WebDriver driver;
    String osName= System.getProperty("os.name");
    String projectPath = System.getProperty("user.dir");
    String password = "Admin";
    String user="admin123";
    @BeforeClass
    public void beforeClass() {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.manage().window().maximize();
    }


}
