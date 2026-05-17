package ui.utils;

import org.testng.annotations.BeforeSuite;

import config.Configuration;

/**
 * Root test base: loads configuration once per suite.
 */
public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void loadConfiguration() {
        Configuration.getInstance();
    }
}
