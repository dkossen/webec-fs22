package webeng.contactlist;

// IT = Integration Test

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ContactsPageIT {

    @LocalServerPort
    int port;

    private ContactsPage page;

    @BeforeEach
    public void initializePage() {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        page = ContactsPage.create(driver, port);
    }


    @Test
    public void contactLinksPresentOnMainPage() {
        // check if there are 30 contacts
        assertEquals(30, page.links().size());
    }

    @Test
    public void contactLinkClick() {
        page.links().get(0).click();

        var tables = page.tables();
        assertFalse(tables.isEmpty());

        var detailsTable = tables.get(0);
        assertTrue(detailsTable.getText().contains("Librarian"));
    }

    @Test
    public void contactLinksPresentOnDetailsPage() {
        var driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port + "/contacts");

        var links = driver.findElements(By.cssSelector("#contacts nav a"));
        var firstLink = links.get(0);
        firstLink.click();

        // check if there are 30 contacts
        assertEquals(30, links.size());

    }

}
