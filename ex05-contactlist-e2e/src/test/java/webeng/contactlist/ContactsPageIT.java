package webeng.contactlist;

// IT = Integration Test

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void contactLinksPresent() {
        var driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port + "/contacts");

        // check if there are 30 contacts
        var links = driver.findElements(By.cssSelector("#contacts nav a"));
        assertEquals(30, links.size());


//        var body = driver.findElement(By.tagName("body"));
//        Assertions.assertNotNull(body);
//
//        var tables = driver.findElements(By.tagName("table"));
//        Assertions.assertTrue(tables.isEmpty());

    }

    @Test
    public void contactLinkClick() {
        var driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port + "/contacts");

        //
        var links = driver.findElements(By.cssSelector("#contacts nav a"));
        var firstLink = links.get(0);
        firstLink.click();

        var tables = driver.findElements(By.cssSelector("#contacts table"));
        assertFalse(tables.isEmpty());

        var detailsTable = tables.get(0);
        assertTrue(detailsTable.getText().contains("Librarian"));

    }

    @Test
    public void contactLinksPresentOnDetailsPage() {
        var driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:" + port + "/contacts");

        //
        var links = driver.findElements(By.cssSelector("#contacts nav a"));
        var firstLink = links.get(0);
        firstLink.click();


        // todo check video
        // check if there are 30 contacts
        var links = driver.findElements(By.cssSelector("#contacts nav a"));
        assertEquals(30, links.size());

    }

}
