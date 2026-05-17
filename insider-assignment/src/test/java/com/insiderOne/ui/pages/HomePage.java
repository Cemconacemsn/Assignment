package com.insiderOne.ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.insiderOne.config.Configuration;
import com.insiderOne.utils.WaitUtils;

/**
 * Page object for https://insiderone.com/ home.
 */
public class HomePage extends BasePage {

    private static final String HOME_URL_FRAGMENT = "insiderone.com";
    private static final String EXPECTED_TITLE_FRAGMENT = "Insider One";
    private static final String HERO_HEADLINE_FRAGMENT = "Agentic Customer Engagement Platform";

    private static final By COOKIE_ACCEPT_ALL = By.cssSelector("#wt-cli-accept-all-btn");
    private static final By HEADER_BANNER_CLOSE = By.cssSelector(".header-banner-close button");
    private static final By HEADER = By.cssSelector("header#navigation.header-insiderone");
    private static final By HEADER_LOGO = By.cssSelector(".header-logo a");
    private static final By GET_DEMO_CTA = By.cssSelector(".header-menu-action a[href*='request-a-demo']");
    private static final By HERO_SECTION = By.cssSelector("section.homepage-hero");
    private static final By HERO_HEADLINE = By.cssSelector("section.homepage-hero h1");
    private static final By TRUSTED_CUSTOMERS_HEADING = By.cssSelector("h2.homepage-logo-reel-heading");
    private static final By SOCIAL_PROOF_SECTION = By.cssSelector("section.homepage-social-proof");
    private static final By PLATFORM_SECTION = By.cssSelector("section.homepage-capabilities");
    private static final By FOOTER = By.cssSelector("footer.footer");

    public HomePage open() {
        driver.get(Configuration.getInstance().get("base.url"));
        return this;
    }

    public HomePage acceptCookieConsentIfPresent() {
        WaitUtils.clickIfVisible(COOKIE_ACCEPT_ALL);
        return this;
    }

    public HomePage dismissAnnouncementBannerIfPresent() {
        WaitUtils.clickIfVisible(HEADER_BANNER_CLOSE);
        return this;
    }

    public HomePage openCompanyMenu() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public CareersPage goToCareers() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isHomePageOpened() {
        WaitUtils.waitForUrlContains(HOME_URL_FRAGMENT);
        String title = getTitle();
        return title != null && title.contains(EXPECTED_TITLE_FRAGMENT);
    }

    public String getHeroHeadlineText() {
        return waitForVisible(HERO_HEADLINE).getText().replace("\n", " ").trim();
    }

    public boolean isHeaderDisplayed() {
        return isDisplayed(HEADER);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(HEADER_LOGO);
    }

    public boolean isGetDemoCtaDisplayed() {
        return isDisplayed(GET_DEMO_CTA);
    }

    public boolean isHeroSectionDisplayed() {
        return isDisplayed(HERO_SECTION);
    }

    public boolean isHeroHeadlineDisplayed() {
        return getHeroHeadlineText().contains(HERO_HEADLINE_FRAGMENT);
    }

    public boolean isTrustedCustomersSectionDisplayed() {
        return isDisplayed(TRUSTED_CUSTOMERS_HEADING);
    }

    public boolean isSocialProofSectionDisplayed() {
        return isDisplayed(SOCIAL_PROOF_SECTION);
    }

    public boolean isPlatformSectionDisplayed() {
        return isDisplayed(PLATFORM_SECTION);
    }

    public boolean isFooterDisplayed() {
        scrollIntoView(FOOTER);
        return isDisplayed(FOOTER);
    }

    public boolean areAllMainSectionsDisplayed() {
        waitForMainContent();
        return isHeaderDisplayed()
                && isLogoDisplayed()
                && isGetDemoCtaDisplayed()
                && isHeroSectionDisplayed()
                && isHeroHeadlineDisplayed()
                && isTrustedCustomersSectionDisplayed()
                && isSocialProofSectionDisplayed()
                && isPlatformSectionDisplayed()
                && isFooterDisplayed();
    }

    private void waitForMainContent() {
        waitForVisible(HERO_SECTION);
        waitForVisible(HEADER);
    }

    private void scrollIntoView(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty()) {
            WebElement element = elements.getFirst();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(element));
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        }
    }
}
