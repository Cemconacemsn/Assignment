package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.HomePage;
import com.insiderOne.ui.pages.JobListPage;
import com.insiderOne.ui.pages.OpenRolesPage;

public class ApplyRedirectTest extends BaseUiTest {

    private static final String LOCATION_ISTANBUL = "Istanbul, Turkey";
    private static final String DEPARTMENT_QA = "Quality Assurance";
    private static final String LEVER_HOST_FRAGMENT = "jobs.lever.co";

    @Test(enabled = false, description = "Apply action redirects to Lever application page")
    public void shouldRedirectToLeverOnApply() {
        new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .openCompanyMenu()
                .goToCareers()
                .waitUntilLoaded()
                .clickSeeAllTeams();

        JobListPage jobListPage = new OpenRolesPage()
                .selectLocation(LOCATION_ISTANBUL)
                .selectDepartment(DEPARTMENT_QA)
                .applyFilters()
                .showFilteredJobs();

        jobListPage.clickViewRoleForJob(0).clickApplyForJob(0);

        assertThat(jobListPage.getApplyRedirectUrl())
                .as("Apply redirect URL")
                .contains(LEVER_HOST_FRAGMENT);
    }
}
