package StepDefinitions;

import static org.testng.Assert.fail;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.mantis.POM.POMFilterIssues;
import com.utility.DriverLib;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class FilterIssue {

	boolean status = true, check = true;
	DriverLib driverLib = new DriverLib();
	WebDriver driver = driverLib.getWebDriver();
	POMFilterIssues oi = new POMFilterIssues(driver);

	public FilterIssue() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
		System.out.printf("| %-12s |", "OPEN FILTER");
	}
	
	@Given("user is on the home page")
	public void user_is_on_the_home_page() {
		System.out.printf(" %-40s |","LOGIN");

		try {
			oi.checkHomePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (NoSuchElementException e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in check home page");
		}
	}

	@Then("navigate to view issue page")
	public void navigate_to_view_issue_page() {
		System.out.printf("| %-12s | %-40s |","","VIEW ISSUE PAGE");		
		
		try {
			oi.goToViewIssuePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in View Page Button");
		}
	}

	@Then("apply priority filter as {string}")
	public void apply_priority_filter_as(String prior) {
		System.out.printf("| %-12s | %-40s |","","APPLY PRIORITY FILTER");
		
		try {
			oi.applyPriorityFilter(prior);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in Applying priority filter");
		}
	}

	@Then("apply severity filter as {string}")
	public void apply_severity_filter_as(String sever) {
		System.out.printf("| %-12s | %-40s |","","APPLY SEVERITY FILTER");
		
		try {
			oi.applySeverittyFilter(sever);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in Applying severity filter");
		}
	}

	@Then("apply status filter as {string}")
	public void apply_status_filter_as(String stat) {
		System.out.printf("| %-12s | %-40s |","","APPLY REPORTER FILTER");
		
		try {
			oi.applyStatusFilter(stat);
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in Applying status filter");
		}
	}

	@Then("validate all filters with values {string} {string} {string}")
	public void validate_all_filters(String prior, String sever, String stat) {
		boolean status = false;
		System.out.printf("| %-12s | %-40s |","","VALIDATE ALL FILTERS");	
		
		try {
			status = oi.validateFilter(prior, sever, stat);
			System.out.printf(" %-7s |","PASS");
		} catch (Exception e) {
			System.out.printf(" %-7s |","FAIL");
			fail("Exception in Validating filter");
		}

		if (status) {
			System.out.printf("%-12s |%n","SUCCESS");
		} else {
			System.out.printf("%-12s |%n","FAILURE");		
		}
	}
}