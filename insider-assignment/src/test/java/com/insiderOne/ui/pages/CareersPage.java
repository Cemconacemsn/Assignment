package com.insiderOne.ui.pages;

import org.openqa.selenium.By;

/**
 * Page object for the Insider careers landing experience.
 */
public class CareersPage extends BasePage {

    private static final By PAGE_HEADER = By.cssSelector(".careers-header, h1");
    private static final By SEE_ALL_TEAMS_BUTTON = By.cssSelector("a.btn, a[href*='open-positions']");

    public CareersPage waitUntilLoaded() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public OpenRolesPage clickSeeAllTeams() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isCareersPageDisplayed() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
