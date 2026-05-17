package com.insiderOne.driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread-local WebDriver holder for parallel TestNG execution.
 */
public final class DriverManager {

    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized. Ensure BaseUiTest @BeforeMethod ran.");
        }
        return driver;
    }

    public static void initDriver() {
        if (DRIVER.get() != null) {
            LOG.warn("WebDriver already exists for this thread; quitting previous instance");
            quitDriver();
        }
        DRIVER.set(DriverFactory.createDriver());
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                LOG.warn("Error while quitting WebDriver", e);
            } finally {
                DRIVER.remove();
            }
        }
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }
}
