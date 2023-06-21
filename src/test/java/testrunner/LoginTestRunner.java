package testrunner;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.DashboardPage;
import page.LoginPage;
import setup.Setup;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboard;

    @Test(priority = 1, description = "User can not login with wrong creds")
    public void doLoginWithInvalidCreds() {
        loginPage = new LoginPage(driver);
        String errorMessageActual = loginPage.doLoginWithWrongCreds("wronguser", "wrongpass");
        String errorMessageExpected = "Invalid credentials";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));
    }

    @Test(priority = 2, description = "User can login with valid creds", groups = "smoke")
    public void doLogin() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        dashboard = new DashboardPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dashboard.imgProfile.isDisplayed());
        Thread.sleep(3000);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "/index.php/dashboard/index";
        softAssert.assertTrue(urlActual.contains(urlExpected));
        softAssert.assertAll();
//        WebElement profileImageElem= driver.findElement(By.className("oxd-userdropdown-img"));
//        Assert.assertTrue(profileImageElem.isDisplayed());
    }
}
