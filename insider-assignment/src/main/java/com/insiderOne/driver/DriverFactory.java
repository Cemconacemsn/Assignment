package com.insiderOne.driver;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insiderOne.config.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        Configuration config = Configuration.getInstance();
        String browser = config.get("browser", "chrome").toLowerCase();
        boolean headless = config.getBoolean("headless", false);

        WebDriver driver = switch (browser) {
            case "firefox" -> createFirefox(headless);
            case "edge" -> createEdge(headless);
            case "chrome" -> createChrome(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(config.getInt("implicit.wait.seconds", 0)));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(config.getInt("page.load.timeout.seconds", 30)));
        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(config.getInt("script.timeout.seconds", 30)));
        driver.manage().window().maximize();

        LOG.info("WebDriver started: {} (headless={})", browser, headless);
        return driver;
    }

    private static ChromeDriver createChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return new ChromeDriver(options);
    }

    private static FirefoxDriver createFirefox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return new FirefoxDriver(options);
    }

    private static EdgeDriver createEdge(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        return new EdgeDriver(options);
    }
}
