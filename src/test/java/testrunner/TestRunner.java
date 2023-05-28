package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(ListenerTest.class)
@CucumberOptions(
	features = "features",
	glue = "stepdefinitions",
//	tags = "@ReportIssue or @UpdateIssue or @DeleteIssue"
//	tags = "@ReportIssue"
	tags = "@UpdateIssue"
//	tags = "@DeleteIssue"
)
public class TestRunner extends AbstractTestNGCucumberTests { }
