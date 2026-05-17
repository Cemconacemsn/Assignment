package ui.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import ui.pages.ExamplePage;
import ui.utils.BaseUiTest;

public class SampleUiTest extends BaseUiTest {

    @Test(description = "Verify example.com heading via Page Object Model")
    public void shouldDisplayExampleDomainHeading() {
        String heading = new ExamplePage().open().headingText();

        assertThat(heading)
                .as("Page heading")
                .isEqualTo("Example Domain");
    }
}
