package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class POMFilterIssues {

	By prioritylink = By.xpath("//form[@id='filters_form_open']//table//tr[1]/td[5]/a");
	By priorityselect = By.xpath("//form[@id='filters_form_open']//table//tr[2]/td[5]/select");
	By severitylink = By.xpath("//form[@id='filters_form_open']//table//tr[1]/td[6]/a");
	By severityselect = By.xpath("//form[@id='filters_form_open']//table//tr[2]/td[6]/select");
	By Statuslink = By.xpath("//form[@id='filters_form_open']//table//tr[3]/td[3]/a");
	By Statusselect = By.xpath("//form[@id='filters_form_open']//table//tr[4]/td[3]/select");

	By SubmitButton = By.xpath("//form[@id='filters_form_open']//input[@type='submit']");
	By IssueList = By.xpath("//*[@class='column-id']");
	By PriorityList = By.xpath("//*[@class='column-priority']/i");
	By SeverityList = By.xpath("//*[@class='column-severity']");
	By StatusList = By.xpath("//*[@class='column-status']");
	By NextButton = By.xpath("//div[@class='btn-toolbar']//a[contains(text(),'Next')]");
	By Per_page_index = By.xpath("//td[@id='per_page_filter_target']");

	WebDriver driver;

	public POMFilterIssues(WebDriver driver) {
		this.driver = driver;
	}

	public void applyPriorityFilter(String prior) throws Exception {
		driver.findElement(prioritylink).click();
		Thread.sleep(1000);
		new Select(driver.findElement(priorityselect)).selectByVisibleText(prior);
		Thread.sleep(1000);
		driver.findElement(SubmitButton).click();
	}

	public void applySeverittyFilter(String sever) throws Exception {
		driver.findElement(severitylink).click();
		Thread.sleep(1000);
		new Select(driver.findElement(severityselect)).selectByVisibleText(sever);
		Thread.sleep(1000);
		driver.findElement(SubmitButton).click();
	}

	public void applyStatusFilter(String status) throws Exception {
		driver.findElement(Statuslink).click();
		Thread.sleep(1000);
		new Select(driver.findElement(Statusselect)).selectByVisibleText(status);
		Thread.sleep(1000);
		driver.findElement(SubmitButton).click();
	}

	public boolean validateFilter(String prior, String sever, String stats) throws NoSuchElementException {
		boolean repeat_for_next_page = false;
		boolean status = true;

		do {
			repeat_for_next_page = false;
			int total_issues_per_page = Integer.parseInt(driver.findElement(Per_page_index).getText());
			List<WebElement> ilist = driver.findElements(IssueList);
			List<WebElement> plist = driver.findElements(PriorityList);
			List<WebElement> severlist = driver.findElements(SeverityList);
			List<WebElement> statlist = driver.findElements(StatusList);
			int issues_size = ilist.size();
			if (prior.contains("none"))
				if (plist.size() != 0)
					status = false;
			for (int i = 1; i < issues_size; i++) {
				if ((!prior.contains("none")) && (!prior.contains("any"))) {
					if (!(plist.get(i - 1).getAttribute("title")).contains(prior)) {
						status = false;
					}
				}
				if ((!(severlist.get(i).getText()).contains(sever)) && (!sever.contains("any"))) {
					status = false;
				}
				if ((!(statlist.get(i).getText()).contains(stats)) && (!stats.contains("any"))) {
					status = false;
				}
			}
			if (total_issues_per_page == (issues_size - 1) && !driver.findElements(NextButton).isEmpty()) {
				if (driver.findElement(NextButton).isEnabled()) {
					repeat_for_next_page = true;
					driver.findElement(NextButton).click();
				}
			}
		} while (repeat_for_next_page);
		return status;
	}
}