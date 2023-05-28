package stepdefinitions;

import com.mantis.POMIssues;
import com.mantis.POMNavbar;
import com.mantis.POMReportIssue;
import com.utility.DriverLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class ReportIssue {

    private final DriverLib driverLib = new DriverLib();
    private final WebDriver driver = driverLib.getWebDriver();

    private final POMReportIssue pomReportIssue = new POMReportIssue(driver);
    private final POMNavbar pomNavbar = new POMNavbar(driver);
    private final POMIssues pomIssues = new POMIssues(driver);

    private String issueId;
    private boolean issuePresent;

    public ReportIssue() {
        System.out.printf("-----------------------------------------------------------------------------------%n");
        System.out.printf("| %-12s |", "REPORT ISSUE");
    }

    @Given("user is on homepage")
    public void user_is_on_homepage() {
        System.out.printf(" %-40s |", "LOGIN");

        try {
            pomNavbar.checkHomePage();
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (NoSuchElementException e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in check home page");
        }
    }

    @When("user enter the issue details as {string} and {string} and {string} and {string} and {string} and {string} click on Submit issue")
    public void user_enter_the_issue_details_click_on_submit_issue(String catog, String repro, String sever, String prior, String summary, String description) {

        System.out.printf("| %-12s | %-40s |", "", "CREATE ISSUE");

        try {
            issueId = pomReportIssue.createIssue(catog, repro, sever, prior, summary, description);
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (Exception e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in create Issue");
        }
    }

    @Then("User clicks on a issue id")
    public void user_clicks_on_a_issue_id() {
        System.out.printf("| %-12s | %-40s |", "", "CLICK ON ISSUE ID");

        try {
            pomIssues.clickOnIssue(issueId);
            issuePresent = true;
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (Exception e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            issuePresent = false;
            fail("Exception in issue generated button");
        }
    }

    @Then("validate for issue deleted")
    public void validate_for_issue_deleted() {
        System.out.printf("| %-12s | %-40s |", "", "VALIDATE ISSUE ID DELETED");

        if(issuePresent) {
            System.out.printf("%-12s |%n", "FAILURE");
        } else {
            System.out.printf("%-12s |%n", "SUCCESS");
            fail("Exception in issue deletion");
        }
    }

    @Then("validate on issue page for values {string} and {string} and {string} and {string} and {string} and {string}")
    public void validate_on_issue_page_for_values(String catog, String repro, String sever, String prior, String summary, String description) {

        boolean status = false;
        System.out.printf("| %-12s | %-40s |", "", "VALIDATE ON ISSUE PAGE");

        try {
            status = pomIssues.validateIssue(catog, repro, sever, prior, summary, description);
            System.out.printf(" %-7s |", "PASS");
        } catch (NoSuchElementException e) {
            System.out.printf(" %-7s |", "FAIL");
            fail("Exception in validate Report Issue");
        }

        if (status) {
            System.out.printf("%-12s |%n", "SUCCESS");
        } else {
            System.out.printf("%-12s |%n", "FAILURE");
        }
    }
}
