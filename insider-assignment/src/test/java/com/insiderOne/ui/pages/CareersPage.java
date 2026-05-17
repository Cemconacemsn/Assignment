package com.insiderOne.ui.pages;

import org.openqa.selenium.By;

import com.insiderOne.utils.WaitUtils;

/**
 * Page object for https://insiderone.com/careers/
 */
public class CareersPage extends BasePage {

    private static final String CAREERS_URL_FRAGMENT = "insiderone.com/careers";
    private static final By OPEN_ROLES_HEADING = By.xpath("//h2[contains(normalize-space(), 'Explore open roles')]");
    private static final By SEE_ALL_TEAMS_BUTTON = By.cssSelector("a.inso-btn.see-more");
    private static final By SEE_MORE_DEPARTMENT_CARD =
            By.cssSelector(".insiderone-icon-cards-see-more-div [data-department]");
    private static final By QA_TEAM_OPEN_ROLES_LINK =
            By.cssSelector("a[href*=\"team=Quality%20Assurance\"]");

    public CareersPage waitUntilLoaded() {
        WaitUtils.waitForUrlContains(CAREERS_URL_FRAGMENT);
        waitForVisible(OPEN_ROLES_HEADING);
        return this;
    }

    public CareersPage clickSeeAllTeams() {
        scrollIntoView(SEE_ALL_TEAMS_BUTTON);
        click(SEE_ALL_TEAMS_BUTTON);
        waitForVisible(SEE_MORE_DEPARTMENT_CARD);
        return this;
    }

    public String getPageUrl() {
        return getCurrentUrl();
    }

    public boolean isCareersPageDisplayed() {
        return getPageUrl().contains(CAREERS_URL_FRAGMENT)
                && isDisplayed(OPEN_ROLES_HEADING);
    }

    public boolean isDepartmentListed(String department) {
        By departmentCard = departmentCardLocator(department);
        scrollIntoView(departmentCard);
        return isDisplayed(departmentCard);
    }

    public LeverQAJobsPage openQualityAssuranceJobs() {
        String originalWindow = driver.getWindowHandle();
        scrollIntoView(QA_TEAM_OPEN_ROLES_LINK);
        click(QA_TEAM_OPEN_ROLES_LINK);
        switchToNewWindowIfOpened(originalWindow);
        return new LeverQAJobsPage().waitUntilLoaded();
    }

    private static By departmentCardLocator(String department) {
        String escaped = department.replace("'", "\\'");
        return By.cssSelector("[data-department='" + escaped + "']");
    }
}
