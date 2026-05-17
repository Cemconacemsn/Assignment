package com.insiderOne.ui.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.insiderOne.driver.DriverManager;
import com.insiderOne.utils.WaitUtils;

/**
 * Base Page Object: holds driver reference and common interaction helpers.
 */
public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisible(By locator) {
        return WaitUtils.waitForVisible(locator);
    }

    protected WebElement waitForClickable(By locator) {
        return WaitUtils.waitForClickable(locator);
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected void click(By locator) {
        scrollIntoView(locator);
        WebElement element = waitForClickable(locator);
        try {
            element.click();
        } catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        return driver.findElements(locator).stream().anyMatch(WebElement::isDisplayed);
    }

    protected void scrollIntoView(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void switchToNewWindowIfOpened(String originalWindow) {
        Set<String> handles = driver.getWindowHandles();
        if (handles.size() > 1) {
            handles.stream()
                    .filter(handle -> !handle.equals(originalWindow))
                    .findFirst()
                    .ifPresent(handle -> driver.switchTo().window(handle));
        }
    }
}
