package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.CareersPage;
import com.insiderOne.ui.pages.HomePage;

public class CareersNavigationTest extends BaseUiTest {

    @DataProvider(name = "departments")
    public Object[][] departments() {
        return new Object[][] {
                { "Quality Assurance" }
        };
    }

    @Test(
            dataProvider = "departments",
            description = "Home → We're hiring → careers → See all teams → verify department in open roles")
    public void shouldNavigateFromHomeToCareersAndDisplayDepartmentInOpenRoles(String department) {
        HomePage homePage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .dismissAnnouncementBannerIfPresent();

        assertHomePageMainElements(homePage);

        CareersPage careersPage = homePage.clickWereHiring().waitUntilLoaded();

        assertThat(careersPage.isCareersPageDisplayed())
                .as("Careers page after clicking We're hiring")
                .isTrue();
        assertThat(careersPage.getPageUrl())
                .as("Careers page URL")
                .contains("insiderone.com/careers");

        careersPage.clickSeeAllTeams();

        assertThat(careersPage.isDepartmentListed(department))
                .as("Department '%s' in expanded open roles list", department)
                .isTrue();
    }

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

    private void assertHomePageMainElements(HomePage homePage) {
        assertThat(homePage.isHomePageOpened())
                .as("Home page URL and title")
                .isTrue();
        assertThat(homePage.isHeaderDisplayed())
                .as("Header navigation")
                .isTrue();
        assertThat(homePage.isLogoDisplayed())
                .as("Header logo")
                .isTrue();
        assertThat(homePage.isGetDemoCtaDisplayed())
                .as("Get a demo CTA")
                .isTrue();
        assertThat(homePage.isHeroSectionDisplayed())
                .as("Hero section")
                .isTrue();
        assertThat(homePage.isHeroHeadlineDisplayed())
                .as("Hero headline")
                .isTrue();
        assertThat(homePage.getHeroHeadlineText())
                .as("Hero headline text")
                .containsIgnoringCase("Agentic Customer Engagement Platform");
        assertThat(homePage.isTrustedCustomersSectionDisplayed())
                .as("Trusted customers section")
                .isTrue();
        assertThat(homePage.isSocialProofSectionDisplayed())
                .as("Social proof section")
                .isTrue();
        assertThat(homePage.isPlatformSectionDisplayed())
                .as("Platform capabilities section")
                .isTrue();
        assertThat(homePage.isFooterDisplayed())
                .as("Footer")
                .isTrue();
        assertThat(homePage.areAllMainSectionsDisplayed())
                .as("All main home page sections")
                .isTrue();
    }
}
