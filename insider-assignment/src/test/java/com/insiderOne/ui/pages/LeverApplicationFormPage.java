package com.insiderOne.ui.pages;

import org.openqa.selenium.By;

import com.insiderOne.utils.WaitUtils;

/**
 * Page object for the Lever job application form (after clicking Apply on the posting page).
 */
public class LeverApplicationFormPage extends BasePage {

    private static final By RESUME_UPLOAD_INPUT = By.id("resume-upload-input");

    public LeverApplicationFormPage waitUntilLoaded() {
        WaitUtils.explicitWait().until(driver -> !driver.findElements(RESUME_UPLOAD_INPUT).isEmpty());
        return this;
    }

    public boolean isResumeUploadPresent() {
        return WaitUtils.isPresent(RESUME_UPLOAD_INPUT);
    }
}
