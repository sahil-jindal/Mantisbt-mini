package stepdefinitions;

import com.mantis.POMDeleteIssue;
import com.mantis.POMIssue;
import com.mantis.POMSidebar;
import com.utility.DriverLib;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DeleteIssue {

    private final DriverLib driverLib = new DriverLib();
    private final WebDriver driver = driverLib.getWebDriver();

    private final POMDeleteIssue pomDeleteIssue = new POMDeleteIssue(driver);
    private final POMSidebar pomSidebar = new POMSidebar(driver);
    private final POMIssue pomIssue = new POMIssue(driver);

    public DeleteIssue() {
        System.out.printf("-----------------------------------------------------------------------------------%n");
        System.out.printf("| %-12s |", "DELETE ISSUE");
    }

    @When("user click on report issue button")
    public void user_click_on_report_issue_button() {
        System.out.printf("| %-12s | %-40s |", "", "REPORT ISSUE BUTTON");

        try {
            pomSidebar.goToReportIssuePage();
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (NoSuchElementException e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Report Issue button doesn't work");
        }
    }

    @Then("go to view issue page")
    public void go_to_view_issue_page() {
        System.out.printf("| %-12s | %-40s |", "", "VIEW ISSUE PAGE");

        try {
            pomSidebar.goToViewIssuePage();
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (NoSuchElementException e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in View Issue page button");
        }
    }

    @Then("User should reach on a delete issue page and click on delete")
    public void user_should_reach_on_a_delete_issue_page_and_click_on_delete() {
        System.out.printf("| %-12s | %-40s |", "", "CLICK ON DELETE BUTTON");

        try {
            pomIssue.clickOnDeleteButton();
            pomDeleteIssue.clickOnConfirmDeleteButton();
            System.out.printf(" %-7s |%-12s |%n", "PASS", "");
        } catch (Exception e) {
            System.out.printf(" %-7s |%-12s |%n", "FAIL", "");
            fail("Exception in delete Issue button");
        }
    }
}