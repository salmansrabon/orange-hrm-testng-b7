package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest(groups = "smoke")
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @AfterMethod(groups = "smoke")
    public void screenShot(ITestResult result){
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils.takeScreenShot(driver);
            } catch (Exception exception) {
                System.out.println(exception);
            }

        }

    }
    @AfterTest(groups = "smoke")
    public void closeDriver(){
        driver.findElement(By.className("oxd-userdropdown-icon")).click();
        driver.findElements(By.className("oxd-userdropdown-link")).get(3).click();
        driver.close();
    }
}
