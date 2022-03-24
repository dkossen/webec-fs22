package webeng.contactlist;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactsPage {

    public static ContactsPage create(WebDriver driver, int port) {
        driver.navigate().to("http://localhost:" + port + "/contacts");
        return PageFactory.initElements(driver, ContactsPage.class);
    }

    @FindBy(css = "#contacts nav a")
    private List<WebElement> links;

    @FindBy(css = "#contacts table")
    private List<WebElement> tables;

    public List<WebElement> links() {
        return links;
    }

    public List<WebElement> tables() {
        return tables;
    }

}
