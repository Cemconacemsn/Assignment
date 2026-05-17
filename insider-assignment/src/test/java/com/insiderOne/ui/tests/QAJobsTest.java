package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.HomePage;
import com.insiderOne.ui.pages.LeverQAJobsPage;

public class QAJobsTest extends BaseUiTest {

    private static final String LEVER_QA_JOBS_URL = "https://jobs.lever.co/insiderone?team=Quality%20Assurance";
    private static final String DEPARTMENT_QA = "Software Quality Assurance Engineer (Remote)";

    @Test(description = "Careers → QA team on Lever → verify position, department, and Istanbul listings")
    public void shouldVerifyQualityAssuranceJobsOnLever() {
        LeverQAJobsPage leverPage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .dismissAnnouncementBannerIfPresent()
                .clickWereHiring()
                .waitUntilLoaded()
                .clickSeeAllTeams()
                .openQualityAssuranceJobs();

        assertThat(leverPage.isOnQualityAssuranceJobsPage())
                .as("Lever QA jobs page")
                .isTrue();
        assertThat(leverPage.getPageUrl())
                .as("Lever QA jobs URL")
                .startsWith(LEVER_QA_JOBS_URL.split("\\?")[0])
                .contains("team=Quality%20Assurance");

        assertThat(leverPage.getJobCount())
                .as("Listed job count")
                .isGreaterThan(0);
        assertThat(leverPage.areAllPositionsInQualityAssurance())
                .as("All job positions contain '%s'", DEPARTMENT_QA)
                .isTrue();
        assertThat(leverPage.isDepartmentQualityAssurance())
                .as("Department filter")
                .isTrue();
        assertThat(leverPage.hasIstanbulLocationAmongQAJobs())
                .as("At least one QA job in Istanbul")
                .isTrue();
    }
}
