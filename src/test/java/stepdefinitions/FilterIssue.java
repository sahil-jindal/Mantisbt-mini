package stepdefinitions;

import com.mantis.POMIssues;
import com.mantis.POMNavbar;
import com.mantis.POMSidebar;
import com.utility.DriverLib;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class FilterIssue {

	private DriverLib driverLib = new DriverLib();
	private WebDriver driver = driverLib.getWebDriver();

	private POMNavbar pomNavbar = new POMNavbar(driver);
	private POMSidebar pomSidebar = new POMSidebar(driver);
	private POMIssues pomIssues = new POMIssues(driver);

	public FilterIssue() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
		System.out.printf("| %-12s |", "OPEN FILTER");
	}
	
	@Given("user is on the home page")
	public void user_is_on_the_home_page() {
		System.out.printf(" %-40s |","LOGIN");

		try {
			pomNavbar.checkHomePage();
			System.out.printf(" %-7s |%-12s |%n","PASS", "");
		} catch (Exception e) {
			System.out.printf(" %-7s |%-12s |%n","FAIL", "");
			fail("Exception in check home page");
		}
	}

	@Then("navigate to view issue page")
	public void navigate_to_view_issue_page() {
		System.out.printf("| %-12s | %-40s |","","VIEW ISSUE PAGE");		
		
		try {
			pomSidebar.goToViewIssuePage();
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
			pomIssues.applyPriorityFilter(prior);
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
			pomIssues.applySeverittyFilter(sever);
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
			pomIssues.applyStatusFilter(stat);
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
			status = pomIssues.validateFilter(prior, sever, stat);
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