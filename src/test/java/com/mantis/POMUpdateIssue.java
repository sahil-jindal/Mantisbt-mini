package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class POMUpdateIssue {
	By statusButton = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-status']/select");
	By resolution = By.xpath("//*[@id='resolution']");
	By updateButton = By.xpath("//*[@id='update_bug_form']/div/div[3]/input");
	By newStatus = By.xpath("//*[@id='history']/div[2]/div/div/table/tbody/tr[last()-1]/td[last()]");
	WebDriver driver;

	public POMUpdateIssue(WebDriver driver) {
		this.driver = driver;
	}

	public boolean updateIssue(String stats, String reso) throws Exception {
		new Select(driver.findElement(statusButton)).selectByVisibleText(stats);
		Thread.sleep(1000);
		new Select(driver.findElement(resolution)).selectByVisibleText(reso);
		Thread.sleep(1000);
		driver.findElement(updateButton).click();
		Thread.sleep(2000);

		return !driver.getCurrentUrl().equals("http://localhost/mantisbt/bug_update.php");
	}

	public void clickOnIssue(String issueId) throws NoSuchElementException {
		driver.findElement(By.linkText(issueId)).click();
	}
}
