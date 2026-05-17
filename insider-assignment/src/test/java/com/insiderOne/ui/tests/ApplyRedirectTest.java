package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.HomePage;
import com.insiderOne.ui.pages.LeverApplicationFormPage;
import com.insiderOne.ui.pages.LeverApplicationPage;
import com.insiderOne.ui.pages.LeverQAJobsPage;

public class ApplyRedirectTest extends BaseUiTest {

    private static final String JOB_TITLE = "Software Quality Assurance Engineer (Remote)";
    private static final String LOCATION_ISTANBUL = "Istanbul";

    @Test(description = "Apply action redirects to Lever application page")
    public void shouldRedirectToLeverOnApply() {
        LeverQAJobsPage leverJobsPage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .dismissAnnouncementBannerIfPresent()
                .clickWereHiring()
                .waitUntilLoaded()
                .clickSeeAllTeams()
                .openQualityAssuranceJobs();

        assertThat(leverJobsPage.hasIstanbulLocationAmongQAJobs())
                .as("Istanbul QA job listing")
                .isTrue();

        LeverApplicationPage applicationPage =
                leverJobsPage.clickApplyForJob(JOB_TITLE, LOCATION_ISTANBUL);

        assertThat(applicationPage.getJobTitle())
                .as("Lever application page job title")
                .isEqualTo(JOB_TITLE);
        assertThat(applicationPage.hasApplySubmitButton())
                .as("Lever application submit button")
                .isTrue();

        LeverApplicationFormPage applicationFormPage = applicationPage.openApplicationForm();

        assertThat(applicationFormPage.isResumeUploadPresent())
                .as("Lever application form resume upload")
                .isTrue();
    }
}
