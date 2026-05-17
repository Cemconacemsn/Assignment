package com.insiderOne.ui.pages;

import org.openqa.selenium.By;

/**
 * Page object for open roles / positions filtering UI.
 */
public class OpenRolesPage extends BasePage {

    private static final By LOCATION_FILTER = By.id("filter-by-location");
    private static final By DEPARTMENT_FILTER = By.id("filter-by-department");
    private static final By FILTER_SUBMIT = By.cssSelector("#btn-filter-submit");

    public OpenRolesPage selectLocation(String location) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public OpenRolesPage selectDepartment(String department) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public OpenRolesPage applyFilters() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public JobListPage showFilteredJobs() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isOpenRolesPageDisplayed() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
