package com.insiderOne.utils;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insiderOne.config.Configuration;
import com.insiderOne.driver.DriverManager;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebDriverWait explicitWait() {
        int seconds = Configuration.getInstance().getInt("explicit.wait.seconds", 15);
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds));
    }

    public static WebElement waitForVisible(By locator) {
        return explicitWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return explicitWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForUrlContains(String fragment) {
        return explicitWait().until(ExpectedConditions.urlContains(fragment));
    }

    public static void waitForVisible(WebDriver driver, By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitForInvisible(By locator) {
        return explicitWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForNumberOfElementsToBe(By locator, int expectedCount) {
        return Boolean.TRUE.equals(
                explicitWait().until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount)));
    }

    public static boolean isPresent(By locator) {
        return !DriverManager.getDriver().findElements(locator).isEmpty();
    }

    public static void clickIfVisible(By locator) {
        List<WebElement> elements = DriverManager.getDriver().findElements(locator);
        if (!elements.isEmpty() && elements.getFirst().isDisplayed()) {
            elements.getFirst().click();
        }
    }
}
