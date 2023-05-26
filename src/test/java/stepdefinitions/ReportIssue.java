package stepdefinitions;

import com.mantis.*;
import com.utility.DriverLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class ReportIssue {

	private DriverLib driverLib = new DriverLib();
	private WebDriver driver = driverLib.getWebDriver();

	private POMReportIssue pomReportIssue = new POMReportIssue(driver);
	private POMNavbar pomNavbar = new POMNavbar(driver);
	private POMSidebar pomSidebar = new POMSidebar(driver);
	private POMIssues pomIssues = new POMIssues(driver);

	private String issueId;

	public ReportIssue() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
		System.out.printf("| %-12s |", "REPORT ISSUE");
	}

	@Given("user is on homepage")
	public void user_is_on_homepage() {
		System.out.printf(" %-40s |","LOGIN");
		
		try {
			pomNavbar.checkHomePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in check home page");
		}
	}

	@When("user click on report issue button")
	public void user_click_on_report_issue_button() {
		System.out.printf("| %-12s | %-40s |","","REPORT ISSUE BUTTON");
		
		try {
			pomSidebar.goToReportIssuePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Report Issue button doesn't work");
		}
	}

	@When("user enter the issue details as {string} and {string} and {string} and {string} and {string} and {string} click on Submit issue")
	public void user_enter_the_issue_details_click_on_submit_issue(String catog, String repro, String sever,
			String prior, String summary, String description) {
		
		System.out.printf("| %-12s | %-40s |","","CREATE ISSUE");
		
		try {
			pomSidebar.goToReportIssuePage();
			issueId = pomReportIssue.createIssue(catog, repro, sever, prior, summary, description);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in create Issue");
		}
	}

	@Then("go to view issue page")
	public void go_to_view_issue_page() {
		System.out.printf("| %-12s | %-40s |","","VIEW ISSUE PAGE");
		
		try {
			pomSidebar.goToViewIssuePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			fail("Exception in View Issue page button");
		}
	}

	@Then("click on issue generated")
	public void click_on_issue_generated() {
		System.out.printf("| %-12s | %-40s |","","CLICK ON ISSUE GENERATED");
		
		try {
			pomIssues.clickOnIssue(issueId);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in issue generated button");
		}
	}

	@Then("validate on issue page for values {string} and {string} and {string} and {string} and {string} and {string}")
	public void validate_on_issue_page_for_values(String catog, String repro, String sever, String prior,
			String summary, String description) {

		boolean status = false;
		System.out.printf("| %-12s | %-40s |","","VALIDATE ON ISSUE PAGE");
		
		try {
			status = pomIssues.validateIssue(catog, repro, sever, prior, summary, description);
			System.out.printf(" %-7s |","PASS");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |","FAIL");
			fail("Exception in validate Report Issue");	
		}

		if (status) {
			//System.out.println("Values validated successfully");
			System.out.printf("%-12s |%n","SUCCESS");
		} else {
			System.out.printf("%-12s |%n","FAILURE");
		}
	}
}
