package com.mantis;

import com.utility.IssueVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class POMReportIssue {
	By Category = By.xpath("//*[@id='category_id']");
	By Reproducibility = By.xpath("//*[@id='reproducibility']");
	By Severity = By.xpath("//*[@id='severity']");
	By Priority = By.xpath("//*[@id='priority']");
	By Summ = By.xpath("//*[@id='summary']");
	By Desc = By.xpath("//*[@id='description']");
	By Submit = By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input");
	String summarySeverity = "//th[contains(text(),'By Severity')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryStatus = "//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryCategory = "//th[contains(text(),'By Category')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String traverse = "')]/parent::tr/td[5]";
	By SummaryProject = By.xpath("//th[contains(text(),'By Project')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'automation')]/parent::tr/td[5]");
	By SummaryStatus = By.xpath("//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'new')]/parent::tr/td[5]");

	String[] reqSummary = new String[4];

	WebDriver driver;

	public POMReportIssue(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnIssue(String issueId) throws NoSuchElementException {
		driver.findElement(By.linkText(issueId)).click();
	}



	public String createIssue(String catog, String repro, String sever, String prior, String summary,
			String description) throws Exception {

		String issueId = "";

		new Select(driver.findElement(Category)).selectByVisibleText(catog);
		Thread.sleep(1000);

		new Select(driver.findElement(Reproducibility)).selectByVisibleText(repro);
		Thread.sleep(1000);

		new Select(driver.findElement(Severity)).selectByVisibleText(sever);
		Thread.sleep(1000);

		new Select(driver.findElement(Priority)).selectByVisibleText(prior);
		Thread.sleep(1000);

		driver.findElement(Summ).sendKeys(summary);
		Thread.sleep(1000);

		driver.findElement(Desc).sendKeys(description);
		Thread.sleep(1000);

		driver.findElement(Submit).click();
		Thread.sleep(1000);

		issueId = driver.findElement(By.xpath("//td[@class='bug-id']")).getText();

		return issueId;
	}

	public void fetchSummaryDetails(String sever, String catog) {

		IssueVariable iv = new IssueVariable();

		reqSummary[0] = driver.findElement(SummaryProject).getText();

		if (!driver.findElements(By.xpath(summaryStatus + "new" + traverse)).isEmpty())
			reqSummary[1] = driver.findElement(By.xpath(summaryStatus + "new" + traverse)).getText();
		else if (iv.status.containsKey("new"))
			reqSummary[1] = Integer.toString(0);
		if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty())
			reqSummary[2] = driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText();
		else if (iv.sever.containsKey(sever))
			reqSummary[2] = Integer.toString(0);
		if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty())
			reqSummary[3] = driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText();
		else if (iv.catog.containsKey(catog))
			reqSummary[3] = Integer.toString(0);

		// System.out.println(reqSummary[0]);
		// System.out.println(reqSummary[1]);
		// System.out.println(reqSummary[2]);
		// System.out.println(reqSummary[3]);
	}

	public boolean validateSummary(String sever, String catog) throws NoSuchElementException {

		boolean status = driver.findElement(SummaryProject).getText().equals(Integer.toString(Integer.parseInt(reqSummary[0]) + 1));

		if (!driver.findElement(SummaryStatus).getText().equals(Integer.toString(Integer.parseInt(reqSummary[1]) + 1)))
			status = false;

		if (!driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText()
				.equals(Integer.toString(Integer.parseInt(reqSummary[2]) + 1)))
			status = false;

		if (!driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText()
				.equals(Integer.toString(Integer.parseInt(reqSummary[3]) + 1)))
			status = false;

		return status;
	}
}
