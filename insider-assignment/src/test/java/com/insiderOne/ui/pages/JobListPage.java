package com.insiderOne.ui.pages;

import java.util.List;

import org.openqa.selenium.By;

/**
 * Page object for the filtered job listings grid.
 */
public class JobListPage extends BasePage {

    private static final By JOB_LISTINGS = By.cssSelector(".position-list-item");
    private static final By VIEW_ROLE_BUTTON = By.cssSelector(".btn-view-role, a[href*='jobs.lever']");
    private static final By APPLY_BUTTON = By.cssSelector(".btn-apply, a[href*='apply']");

    public int getListedJobCount() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public List<String> getListedJobTitles() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean areAllJobsInDepartment(String department) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean areAllJobsInLocation(String location) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public JobListPage clickViewRoleForJob(int index) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public JobListPage clickApplyForJob(int index) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public String getApplyRedirectUrl() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
