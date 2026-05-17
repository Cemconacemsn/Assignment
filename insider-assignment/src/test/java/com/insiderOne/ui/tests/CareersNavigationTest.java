package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.CareersPage;
import com.insiderOne.ui.pages.HomePage;

public class CareersNavigationTest extends BaseUiTest {

    @Test(enabled = false, description = "Navigate from home to careers via company menu")
    public void shouldNavigateFromHomeToCareers() {
        CareersPage careersPage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .openCompanyMenu()
                .goToCareers();

        assertThat(careersPage.isCareersPageDisplayed())
                .as("Careers page after navigation from home")
                .isTrue();
    }
}
