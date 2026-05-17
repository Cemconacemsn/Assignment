package com.insiderOne.ui.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.insiderOne.utils.WaitUtils;

/**
 * Page object for Lever open roles filtered by Quality Assurance team.
 */
public class LeverQAJobsPage extends BasePage {

    private static final String LEVER_JOBS_URL_FRAGMENT = "jobs.lever.co/insiderone";
    private static final String QA_TEAM_QUERY = "team=Quality";
    private static final String DEPARTMENT_QA = "Quality Assurance";
    private static final String LOCATION_ISTANBUL = "Istanbul";

    private static final By JOB_POSTINGS = By.cssSelector(".posting");
    private static final By POSTING_NAME = By.cssSelector("h5[data-qa='posting-name']");
    private static final By POSTING_CATEGORIES = By.cssSelector(".posting-categories");
    private static final By POSTING_LOCATION = By.cssSelector(".posting-categories .location");
    private static final By APPLY_BUTTON = By.cssSelector("a.posting-btn-submit.template-btn-submit");

    public LeverQAJobsPage waitUntilLoaded() {
        WaitUtils.waitForUrlContains(LEVER_JOBS_URL_FRAGMENT);
        WaitUtils.waitForUrlContains(QA_TEAM_QUERY);
        waitForVisible(JOB_POSTINGS);
        return this;
    }

    public String getPageUrl() {
        return getCurrentUrl();
    }

    public boolean isOnQualityAssuranceJobsPage() {
        String url = getPageUrl();
        return url.contains(LEVER_JOBS_URL_FRAGMENT) && url.contains(QA_TEAM_QUERY);
    }

    public int getJobCount() {
        return driver.findElements(JOB_POSTINGS).size();
    }

    public List<JobPosting> getJobPostings() {
        return driver.findElements(JOB_POSTINGS).stream().map(this::toJobPosting).toList();
    }

    public boolean areAllPositionsInQualityAssurance() {
        return getJobPostings().stream().allMatch(job -> isQualityAssurancePosition(job.position()));
    }

    public boolean isDepartmentQualityAssurance() {
        return getPageUrl().contains("team=Quality%20Assurance");
    }

    public boolean hasIstanbulLocationAmongQAJobs() {
        return getJobPostings().stream()
                .filter(job -> isQualityAssurancePosition(job.position()))
                .anyMatch(job -> locationMatchesIstanbul(job.location()));
    }

    public LeverApplicationPage clickApplyForJob(String position, String location) {
        String originalWindow = driver.getWindowHandle();
        int windowCountBefore = driver.getWindowHandles().size();

        WebElement applyButton = findPostingApplyButton(position, location);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", applyButton);
        try {
            WaitUtils.explicitWait()
                    .until(ExpectedConditions.elementToBeClickable(applyButton))
                    .click();
        } catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applyButton);
        }

        if (driver.getWindowHandles().size() > windowCountBefore) {
            switchToNewWindowIfOpened(originalWindow);
        }
        return new LeverApplicationPage().waitUntilLoaded();
    }

    private WebElement findPostingApplyButton(String position, String location) {
        return driver.findElements(JOB_POSTINGS).stream()
                .filter(posting -> postingMatches(posting, position, location))
                .findFirst()
                .map(posting -> posting.findElement(APPLY_BUTTON))
                .orElseThrow(() -> new NoSuchElementException(
                        "No posting found for position '%s' in '%s'".formatted(position, location)));
    }

    private boolean postingMatches(WebElement posting, String position, String location) {
        String postingPosition = posting.findElement(POSTING_NAME).getText().trim();
        String postingLocation = posting.findElement(POSTING_LOCATION).getText().trim();
        return postingPosition.equals(position) && locationMatches(postingLocation, location);
    }

    private static boolean locationMatches(String postingLocation, String expectedLocation) {
        String normalizedPosting = postingLocation.toLowerCase();
        String normalizedExpected = expectedLocation.toLowerCase();
        return normalizedPosting.contains(normalizedExpected)
                || locationMatchesIstanbul(postingLocation) && normalizedExpected.contains("istanbul");
    }

    private static boolean isQualityAssurancePosition(String position) {
        String normalized = position.toLowerCase();
        return normalized.contains("quality assurance") || normalized.matches(".*\\bqa\\b.*");
    }

    private static boolean locationMatchesIstanbul(String location) {
        String normalized = location.toLowerCase();
        return normalized.contains(LOCATION_ISTANBUL.toLowerCase())
                || normalized.contains("istanbul, turkey")
                || normalized.contains("istanbul, turkiye");
    }

    private JobPosting toJobPosting(WebElement posting) {
        String position = posting.findElement(POSTING_NAME).getText().trim();
        String location = posting.findElement(POSTING_CATEGORIES).getText().trim();
        return new JobPosting(position, location);
    }

    public record JobPosting(String position, String location) {}
}
