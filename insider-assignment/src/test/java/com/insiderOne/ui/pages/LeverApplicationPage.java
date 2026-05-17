package com.insiderOne.ui.pages;

import org.openqa.selenium.By;

import com.insiderOne.utils.WaitUtils;

/**
 * Page object for a single Lever job posting / application form page.
 */
public class LeverApplicationPage extends BasePage {

    private static final String LEVER_JOB_POSTING_URL_FRAGMENT = "jobs.lever.co/insiderone/";

    private static final By POSTING_HEADLINE = By.cssSelector(".posting-headline h2");
    private static final By APPLY_SUBMIT_BUTTON = By.cssSelector("a.template-btn-submit");

    public LeverApplicationPage waitUntilLoaded() {
        WaitUtils.waitForUrlContains(LEVER_JOB_POSTING_URL_FRAGMENT);
        waitForVisible(POSTING_HEADLINE);
        return this;
    }

    public String getJobTitle() {
        return waitForVisible(POSTING_HEADLINE).getText().trim();
    }

    public boolean hasApplySubmitButton() {
        return isDisplayed(APPLY_SUBMIT_BUTTON);
    }

    public LeverApplicationFormPage openApplicationForm() {
        click(APPLY_SUBMIT_BUTTON);
        return new LeverApplicationFormPage().waitUntilLoaded();
    }
}
