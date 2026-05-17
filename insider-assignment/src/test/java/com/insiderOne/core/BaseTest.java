package com.insiderOne.core;

import org.testng.annotations.BeforeSuite;

import com.insiderOne.config.Configuration;

/**
 * Root test base: loads configuration once per suite.
 */
public abstract class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void loadConfiguration() {
        Configuration.getInstance();
    }
}
