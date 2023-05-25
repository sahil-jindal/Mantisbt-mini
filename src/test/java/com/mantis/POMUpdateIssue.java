package com.mantis;

import com.utility.IssueVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class POMUpdateIssue {

	By SummaryButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span");
	By ReportIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[3]/a/span");
	By ViewIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[2]/a/span");
	By viewissuebutton = By.xpath("//*[@id=\"sidebar\"]/ul/li[2]/a/i");
	By editButton = By.xpath("//input[@type='submit'][@value='Edit']");
	By statusButton = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-status']/select");
	By resolution = By.xpath("//*[@id=\"resolution\"]");
	By updateButton = By.xpath("//*[@id=\"update_bug_form\"]/div/div[3]/input");
	By newStatus = By.xpath("//*[@id=\"history\"]/div[2]/div/div/table/tbody/tr[last()-1]/td[last()]");
	By IssueStat = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-status']");
	By IssueRes = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-resolution']");
	String summarySeverity = "//th[contains(text(),'By Severity')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryStatus = "//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryCategory = "//th[contains(text(),'By Category')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String traverse = "')]/parent::tr/td[5]";
	By SummaryProject = By.xpath("//th[contains(text(),'By Project')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'automation')]/parent::tr/td[5]");

	String[] reqSummary = new String[4];
	WebDriver driver;

	public POMUpdateIssue(WebDriver driver) {
		this.driver = driver;
	}

	public boolean updateIssue(String stats, String reso) throws Exception {

		boolean status = false;

		new Select(driver.findElement(statusButton)).selectByVisibleText(stats);
		Thread.sleep(1000);
		new Select(driver.findElement(resolution)).selectByVisibleText(reso);
		Thread.sleep(1000);
		driver.findElement(updateButton).click();
		Thread.sleep(2000);

		if (driver.getCurrentUrl().equals("http://localhost/mantisbt/bug_update.php")) {
			status = false;
		} else {
			status = true;
		}

		return status;
	}

	public boolean checkHomePage() throws NoSuchElementException {
		return driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/a/span")).isDisplayed();
	}

	public void goToViewIssuePage() throws NoSuchElementException {
		driver.findElement(ViewIssueButton).click();
	}

	public void clickOnIssue(String issueId) throws NoSuchElementException {
		driver.findElement(By.linkText(issueId)).click();
	}

	public void clickOnEditButton() throws NoSuchElementException {
		driver.findElement(editButton).click();
	}

	public boolean validateUpdate(String stat, String resolution) throws NoSuchElementException {

		boolean status = stat.contains(driver.findElement(IssueStat).getText());

		// System.out.println("cat" + (driver.findElement(IssueStat).getText()) + stat);
		if (!resolution.contains(driver.findElement(IssueRes).getText())) {
			status = false;
			// System.out.println("repro" + (driver.findElement(IssueRes).getText()) +
			// resolution);
		}

		return status;
	}

	public void fetchSummaryDetails(String sever, String catog, String statusc) throws NoSuchElementException {

		IssueVariable iv = new IssueVariable();
		driver.findElement(SummaryButton).click();

		reqSummary[0] = driver.findElement(SummaryProject).getText();
		if (!driver.findElements(By.xpath(summaryStatus + statusc + traverse)).isEmpty())
			reqSummary[1] = driver.findElement(By.xpath(summaryStatus + statusc + traverse)).getText();
		else if (iv.status.containsKey(statusc))
			reqSummary[1] = Integer.toString(0);
		if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty())
			reqSummary[2] = driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText();
		else if (iv.sever.containsKey(sever))
			reqSummary[2] = Integer.toString(0);
		if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty())
			reqSummary[3] = driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText();
		else if (iv.catog.containsKey(catog))
			reqSummary[3] = Integer.toString(0);
	}

	public boolean validateSummary(String sever, String catog, String statusc) throws NoSuchElementException {

		boolean status = false;
		int count = 0;

		driver.findElement(SummaryButton).click();

		if (driver.findElement(SummaryProject).getText()
				.equals(Integer.toString(Integer.parseInt(reqSummary[0]) + 1))) {
			count += 1;
		}

		if (!driver.findElements(By.xpath(summaryStatus + statusc + traverse)).isEmpty()) {
			if (driver.findElement(By.xpath(summaryStatus + statusc + traverse)).getText()
					.equals(Integer.toString(Integer.parseInt(reqSummary[1]) + 1))) {

				count += 1;
			}
		}

		if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty()) {
			if (driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText()
					.equals(Integer.toString(Integer.parseInt(reqSummary[2]) + 1))) {
				count += 1;
			}
		}

		if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty()) {
			if (driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText()
					.equals(Integer.toString(Integer.parseInt(reqSummary[3]) + 1))) {
				count += 1;
			}
		}
		
		if(count == 4)
		{
			status = true;
		}

		return status;
	}
}
