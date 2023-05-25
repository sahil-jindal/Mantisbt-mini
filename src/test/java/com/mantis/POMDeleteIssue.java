package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMDeleteIssue {

	By del = By.xpath("//input[@type='submit'][@value='Delete']");
	By deletebutton = By.xpath("//input[@type='submit'][@value='Delete Issues']");

	WebDriver driver;

	public POMDeleteIssue(WebDriver driver) {
		this.driver = driver;
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
			return false;
		} catch (Exception e) {
			return true;
		}
	}
}
