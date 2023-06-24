package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuPIM; //1
    @FindBy(className = "oxd-button--medium") //2
    public List<WebElement> buttonElem;

    @FindBy(className = "oxd-input")
    public List<WebElement> textElem;
    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
