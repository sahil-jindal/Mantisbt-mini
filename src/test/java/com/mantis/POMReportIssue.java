package com.mantis;

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
}
