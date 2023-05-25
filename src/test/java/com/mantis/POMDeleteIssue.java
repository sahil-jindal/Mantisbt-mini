package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMDeleteIssue {

	By del = By.xpath("//input[@type='submit'][@value='Delete']");
	By deletebutton = By.xpath("//input[@type='submit'][@value='Delete Issues']");
	By ViewIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[2]/a/span");
	By ReportIssueButton = By.xpath("//*[@id=\"sidebar\"]/ul/li[3]/a/span");

	WebDriver driver;

	public POMDeleteIssue(WebDriver driver) {
		this.driver = driver;
	}

	public void goToReportIssuePage() throws NoSuchElementException {
		driver.findElement(ReportIssueButton).click();
	}

	public void goToViewIssuePage() throws NoSuchElementException {
		driver.findElement(ViewIssueButton).click();
	}

	public void clickOnIssueId(String id) throws Exception {
		driver.findElement(By.linkText(id)).click();
	}

	public void clickOnDeleteButton() throws NoSuchElementException {
		driver.findElement(del).click();
		driver.findElement(deletebutton).click();
	}

	public boolean validateDelete(String id) throws Exception {
		try {
			driver.findElement(By.linkText(id)).click();
		} catch (Exception e) {
			return true;
		}

		return false;
	}
}
