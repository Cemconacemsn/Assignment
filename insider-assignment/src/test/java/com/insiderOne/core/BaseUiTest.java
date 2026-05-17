package com.insiderOne.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.insiderOne.config.Configuration;
import com.insiderOne.driver.DriverManager;

public abstract class BaseUiTest extends BaseTest {

    private static final DateTimeFormatter SCREENSHOT_TS =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    @BeforeMethod(alwaysRun = true)
    public void setUpUi() {
        DriverManager.initDriver();
        if (Configuration.getInstance().getBoolean("auto.open.base.url", false)) {
            String baseUrl = Configuration.getInstance().get("base.url");
            if (baseUrl != null && !baseUrl.isBlank()) {
                DriverManager.getDriver().get(baseUrl);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownUi(ITestResult result) {
        if (!result.isSuccess() && Configuration.getInstance().getBoolean("screenshot.on.failure", true)) {
            captureScreenshot(result.getMethod().getMethodName());
        }
        DriverManager.quitDriver();
    }

    protected WebDriver driver() {
        return DriverManager.getDriver();
    }

    protected String baseUrl() {
        return Configuration.getInstance().get("base.url");
    }

    private void captureScreenshot(String testName) {
        if (!DriverManager.hasDriver()) {
            return;
        }
        if (!(DriverManager.getDriver() instanceof TakesScreenshot screenshotDriver)) {
            return;
        }
        try {
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            String fileName = testName + "-" + LocalDateTime.now().format(SCREENSHOT_TS) + ".png";
            Path destination = dir.resolve(fileName);
            Files.write(destination, screenshotDriver.getScreenshotAs(OutputType.BYTES));
        } catch (IOException ignored) {
            // Best-effort; do not fail teardown
        }
    }
}
