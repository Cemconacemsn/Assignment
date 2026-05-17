package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.CareersPage;
import com.insiderOne.ui.pages.HomePage;
import com.insiderOne.ui.pages.JobListPage;
import com.insiderOne.ui.pages.OpenRolesPage;

public class QAJobsTest extends BaseUiTest {

    private static final String LOCATION_ISTANBUL = "Istanbul, Turkey";
    private static final String DEPARTMENT_QA = "Quality Assurance";

    @Test(enabled = false, description = "Filter open roles to QA jobs in Istanbul")
    public void shouldDisplayOnlyQAJobsInIstanbul() {
        CareersPage careersPage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .openCompanyMenu()
                .goToCareers()
                .waitUntilLoaded();

        OpenRolesPage openRolesPage = careersPage.clickSeeAllTeams();

        JobListPage jobListPage = openRolesPage
                .selectLocation(LOCATION_ISTANBUL)
                .selectDepartment(DEPARTMENT_QA)
                .applyFilters()
                .showFilteredJobs();

        assertThat(jobListPage.getListedJobCount())
                .as("Filtered QA job count")
                .isGreaterThan(0);
        assertThat(jobListPage.areAllJobsInDepartment(DEPARTMENT_QA))
                .as("All listings department")
                .isTrue();
        assertThat(jobListPage.areAllJobsInLocation(LOCATION_ISTANBUL))
                .as("All listings location")
                .isTrue();
    }
}
