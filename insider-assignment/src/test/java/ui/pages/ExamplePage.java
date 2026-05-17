package ui.pages;

import org.openqa.selenium.By;

/**
 * Sample page object for https://example.com — replace with application pages.
 */
public class ExamplePage extends BasePage {

    private static final By HEADING = By.cssSelector("h1");

    public ExamplePage open() {
        driver.get("https://example.com/");
        return this;
    }

    public String headingText() {
        return waitForVisible(HEADING).getText();
    }
}
