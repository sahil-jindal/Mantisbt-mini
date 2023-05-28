package stepdefinitions;

import com.mantis.POMIssue;
import com.mantis.POMIssues;
import com.mantis.POMUpdateIssue;
import com.utility.DriverLib;
import io.cucumber.java.en.Then;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class UpdateIssue {

    private final DriverLib driverLib = new DriverLib();
    private final WebDriver driver = driverLib.getWebDriver();

    private final POMUpdateIssue pomUpdateIssue = new POMUpdateIssue(driver);
    private final POMIssues pomIssues = new POMIssues(driver);
    private final POMIssue pomIssue = new POMIssue(driver);

    public UpdateIssue() {
        System.out.printf("-----------------------------------------------------------------------------------%n");
        System.out.printf("| %-12s |", "UPDATE ISSUE");
    }

    @Then("User clicks on edit button")
    public void user_clicks_on_edit_button() {
        System.out.printf("| %-12s | %-40s |", "", "CLICK ON EDIT BUTTON");

        try {
            pomIssue.clickOnEditButton();
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in Edit Button");
        }
    }

    @Then("User updates status as {string} and resolution as {string}")
    public void user_updates_status_as_and_resolution_as(String stat, String resolution) {
        System.out.printf("| %-12s | %-40s |", "", "UPDATE ISSUE");

        try {
            boolean status = pomUpdateIssue.updateIssue(stat, resolution);
            if (status) {
                System.out.printf(" %-7s |%-12s |%n", "PASS", "");
            } else {
                System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            }
        } catch (Exception e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in update issue");
        }

    }

    @Then("validate update issue on issue page with {string} and {string}")
    public void validate_update_issue_on_issue_page(String stat, String resolution) {
        boolean status = false;
        System.out.printf("| %-12s | %-40s |", "", "VALIDATE ON ISSUE PAGE");

        try {
            status = pomIssues.validateUpdate(stat, resolution);
            System.out.printf(" %-7s |", "PASS");
        } catch (NoSuchElementException e) {
            System.out.printf(" %-7s |", "FAIL");
            fail("Exception in Validate update");
        }

        if (status) {
            System.out.printf("%-12s |%n", "SUCCESS");
        } else {
            System.out.printf("%-12s |%n", "FAILURE");
        }
    }
}