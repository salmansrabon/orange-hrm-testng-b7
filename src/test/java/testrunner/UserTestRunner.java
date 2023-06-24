package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import page.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
    @Test(priority = 1, description = "Login with new user")
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);
        JSONArray empArray= Utils.readJSONArray("./src/test/resources/Employees.json");
        JSONObject empObj= (JSONObject) empArray.get(empArray.size()-1);
        String username=empObj.get("username").toString();
        String password=empObj.get("password").toString();

        loginPage.doLogin(username, password);
    }
}
