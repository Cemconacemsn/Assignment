package ui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Suite started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("Suite finished: {} | passed={} failed={} skipped={}",
                context.getName(),
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("Test started: {}.{}", result.getTestClass().getName(), result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Test passed: {}.{}", result.getTestClass().getName(), result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("Test failed: {}.{} — {}",
                result.getTestClass().getName(),
                result.getName(),
                result.getThrowable() != null ? result.getThrowable().getMessage() : "unknown");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.warn("Test skipped: {}.{}", result.getTestClass().getName(), result.getName());
    }
}
