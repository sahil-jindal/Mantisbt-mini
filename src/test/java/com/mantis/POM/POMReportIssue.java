package com.mantis.POM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.utility.DBConnection;
import com.utility.IssueVariable;

public class POMReportIssue {

	By ReportIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[3]/a/span");
	By ViewIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[2]/a/span");
	By SummaryButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span");
	By Category = By.xpath("//*[@id=\"category_id\"]");
	By Reproducibility = By.xpath("//*[@id=\"reproducibility\"]");
	By Severity = By.xpath("//*[@id=\"severity\"]");
	By Priority = By.xpath("//*[@id=\"priority\"]");
	By Summ = By.xpath("//*[@id=\"summary\"]");
	By Desc = By.xpath("//*[@id=\"description\"]");
	By Submit = By.xpath("//*[@id=\"report_bug_form\"]/div/div[2]/div[2]/input");
	By IssueCat = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr[@class='bug-header-data']//td[@class='bug-category']");
	By IssueRepro = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-reproducibility']");
	By IssueSev = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-severity']");
	By IssuePri = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-priority']");
	By IssueSum = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-summary']");
	By IssueDesc = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-description']");
	String summarySeverity = "//th[contains(text(),'By Severity')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryStatus = "//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String summaryCategory = "//th[contains(text(),'By Category')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
	String traverse = "')]/parent::tr/td[5]";
	By SummaryProject = By.xpath("//th[contains(text(),'By Project')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'automation')]/parent::tr/td[5]");
	By SummaryStatus = By.xpath("//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'new')]/parent::tr/td[5]");

	String[] reqSummary = new String[4];

	WebDriver driver;
	private static Statement st = null;
	
	static {
		DBConnection dbConn = new DBConnection();
		try {
			st = dbConn.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public POMReportIssue(WebDriver driver) {
		this.driver = driver;
	}

	public void goToReportIssuePage() throws NoSuchElementException {
		driver.findElement(ReportIssueButton).click();
	}

	public void goToViewIssuePage() throws NoSuchElementException {
		driver.findElement(ViewIssueButton).click();
	}

	public void clickOnIssue(String issueId) throws NoSuchElementException {
		driver.findElement(By.linkText(issueId)).click();
	}

	public boolean validateIssue(String catog, String repro, String sever, String prior, String summary,
			String description) throws NoSuchElementException {
		boolean status = true;

		if (!catog.contains(driver.findElement(IssueCat).getText())) {
			status = false;
			System.out.println("cat" + (driver.findElement(IssueCat).getText()) + catog);
		}
		
		if (!repro.contains(driver.findElement(IssueRepro).getText())) {
			status = false;
			System.out.println("repro" + (driver.findElement(IssueRepro).getText()) + repro);
		}
		
		if (!sever.contains(driver.findElement(IssueSev).getText())) {
			status = false;
			System.out.println("sever" + (driver.findElement(IssueSev).getText()) + sever);
		}
		
		if (!prior.contains(driver.findElement(IssuePri).getText())) {
			status = false;
			System.out.println("prior" + (driver.findElement(IssuePri).getText()) + prior);
		}
		
		if (!driver.findElement(IssueSum).getText().contains(summary)) {
			status = false;
			System.out.println("sum" + (driver.findElement(IssueSum).getText()) + summary);
		}
		
		if (!description.contains(driver.findElement(IssueDesc).getText())) {
			status = false;
			System.out.println("desc" + (driver.findElement(IssueDesc).getText()) + description);
		}
		
		return status;
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

	public boolean checkHomePage() throws NoSuchElementException {
		return driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/a/span")).isDisplayed();
	}

	public boolean validateDBIssue(String id, String catog, String repro, String sever, String prior, String summary,
			String description) throws Exception {

		IssueVariable iv = new IssueVariable();
		boolean status = true;

		ResultSet rs;
		String query = "select * from mantis_bug_table where id=" + Integer.parseInt(id);
		String query1 = "select * from mantis_bug_text_table where id=" + Integer.parseInt(id);

		rs = st.executeQuery(query);

		if (rs.next()) {
			if (!rs.getString(22).contains(summary))
				status = false;
			if (!(rs.getInt(26) == iv.catog.get(catog)))
				status = false;
			if (!(rs.getInt(8) == iv.repro.get(repro)))
				status = false;
			if (!(rs.getInt(7) == iv.sever.get(sever)))
				status = false;
			if (!(rs.getInt(6) == iv.prior.get(prior)))
				status = false;
		} else
			status = false;

		rs = st.executeQuery(query1);

		if (rs.next()) {
			if (!rs.getString(2).contains(description))
				status = false;
		} else
			status = false;

		return status;
	}

	public void fetchSummaryDetails(String sever, String catog) {

		IssueVariable iv = new IssueVariable();
		driver.findElement(SummaryButton).click();

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
		
//		System.out.println(reqSummary[0]);
//		System.out.println(reqSummary[1]);
//		System.out.println(reqSummary[2]);
//		System.out.println(reqSummary[3]);
	}

	public boolean validateSummary(String sever, String catog) throws NoSuchElementException {

		boolean status = true;

		driver.findElement(SummaryButton).click();

		if (!driver.findElement(SummaryProject).getText().equals(Integer.toString(Integer.parseInt(reqSummary[0]) + 1)))
			status = false;

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
