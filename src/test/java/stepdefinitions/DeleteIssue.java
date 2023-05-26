package stepdefinitions;

import com.mantis.*;
import com.utility.DriverLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DeleteIssue {

	private DriverLib driverLib = new DriverLib();
	private WebDriver driver = driverLib.getWebDriver();

	private POMDeleteIssue pomDeleteIssue = new POMDeleteIssue(driver);
	private POMReportIssue pomReportIssue = new POMReportIssue(driver);
	private POMNavbar pomNavbar = new POMNavbar(driver);
	private POMSidebar pomSidebar = new POMSidebar(driver);
	private POMIssue pomIssue = new POMIssue(driver);

	private String issueId = "";

	public DeleteIssue() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
		System.out.printf("| %-12s |", "DELETE ISSUE");
	}
	
	@Given("User is on now on homepage")
	public void user_is_on_now_on_homepage() {
		System.out.printf(" %-40s |","LOGIN");

		try {
			pomNavbar.checkHomePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in check home page");
		}
	}
	
	@Given("User creates an issue for delete with values {string} {string} {string} {string} {string} {string}")
	public void user_creates_an_issue_for_delete_with_values(String catog, String repro, String sever,
			String prior, String summary, String description) {
		System.out.printf("| %-12s | %-40s |","","CREATE ISSUE");

		try {
			pomSidebar.goToReportIssuePage();
		} catch (Exception e) {
			fail("Exception in Report issue page");
		}

		try {
			Thread.sleep(3000);
			issueId = pomReportIssue.createIssue(catog, repro, sever, prior, summary, description);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in create Issue of Update Issue");
		}
	}
	
	@When("User click on view issues button for delete")
	public void user_click_on_view_issues_button_for_delete() {
		System.out.printf("| %-12s | %-40s |","","VIEW ISSUE PAGE");

		try {
			pomSidebar.goToViewIssuePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in View Issue page button");
		}
	}
	
	@When("User clicks on a issue id for delete")
	public void user_clicks_on_a_issue_id_for_delete() {
		System.out.printf("| %-12s | %-40s |","","CLICK ON ISSUE ID");

		try {
			pomDeleteIssue.clickOnIssueId(issueId);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in Issue id button");
		}
	}
	
	@Then("User should reach on a delete issue page and click on delete")
	public void user_should_reach_on_a_delete_issue_page_and_click_on_delete() {
		System.out.printf("| %-12s | %-40s |","","CLICK ON DELETE BUTTON");

		try {
			pomIssue.clickOnDeleteButton();
			pomDeleteIssue.clickOnConfirmDeleteButton();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in delete Issue button");
		}
	}
	
	@Then("go to the view issue page")
	public void go_to_the_view_issue_page() {
		System.out.printf("| %-12s | %-40s |","","VIEW ISSUE PAGE");

		try {
			pomSidebar.goToViewIssuePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in view issue page button");
		}
	}
	
	@Then("validate for issue deleted")
	public void validate_for_issue_deleted() {
		boolean status = false;
		System.out.printf("| %-12s | %-40s |","","VALIDATE ISSUE ID DELETED");

		try {
			status = pomDeleteIssue.validateDelete(issueId);
			System.out.printf(" %-7s |","PASS");
		} catch (Exception e) {
			System.out.printf(" %-7s |","FAIL");
			fail("Exception in Validate delete");
		} 
		
		if (status) {
			System.out.printf("%-12s |%n","SUCCESS");
		} else {
			System.out.printf("%-12s |%n","FAILURE");
		}
	}
}