package ui.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.Configuration;
import driver.DriverManager;

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
}
