package com.insiderOne.ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.insiderOne.core.BaseUiTest;
import com.insiderOne.ui.pages.HomePage;

public class HomePageTest extends BaseUiTest {

    @Test(description = "Verify Insider One home page opens and main sections are visible")
    public void shouldOpenInsiderHomePageAndDisplayMainElements() {
        HomePage homePage = new HomePage()
                .open()
                .acceptCookieConsentIfPresent()
                .dismissAnnouncementBannerIfPresent();

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
