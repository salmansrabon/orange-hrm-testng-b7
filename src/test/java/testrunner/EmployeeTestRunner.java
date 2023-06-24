package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.LoginPage;
import page.PIMPage;
import page.UserModel;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class EmployeeTestRunner extends Setup {
    @BeforeTest(groups = "smoke")
    public void doLogin(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
    }
    @Test(priority = 1, description = "Apply for leave", groups = "smoke")
    public void getLeave(){
        driver.findElements(By.className("oxd-main-menu-item--name")).get(2).click();
        String textActual= driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span']")).getText();
        String textExpected="Records Found";

        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 2, description = "Create employee")
    public void createEmployee() throws InterruptedException, IOException, ParseException {
        driver.findElements(By.className("oxd-main-menu-item--name")).get(1).click();
        PIMPage pimPage = new PIMPage(driver);
//        pimPage.menuPIM.get(1).click();
        driver.findElements(By.className("oxd-button--medium")).get(2).click(); // add employee button

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("orangehrm-main-title")));

        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        String lastName=faker.name().lastName();
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.className("oxd-switch-input")).click();

        String username = faker.name().username() + Utils.generateRandomId(100, 999);
        String password = "P@ssword123";
        pimPage.textElem.get(5).sendKeys(username);//username
        pimPage.textElem.get(6).sendKeys(password);//password
        pimPage.textElem.get(7).sendKeys(password);//confirm password

        pimPage.buttonElem.get(1).click(); //save

        Thread.sleep(7000);
        String titleActual = driver.findElements(By.className("orangehrm-main-title")).get(0).getText();
        String titleExpected = "Personal Details";

        UserModel model=new UserModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);
        Utils.saveInfo(model);
        Assert.assertEquals(titleActual, titleExpected);
    }
}
