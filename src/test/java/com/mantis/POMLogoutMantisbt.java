package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POMLogoutMantisbt {

	By accountDropDown = By.xpath("//*[@id='navbar-container']/div[2]/ul/li[3]/a/i[2]");
	By logoutButton = By.xpath("//*[@id='navbar-container']/div[2]/ul/li[3]/ul/li[4]/a");

	WebDriver driver;

	public POMLogoutMantisbt(WebDriver driver) { this.driver = driver; }

	public void logout() {
		try {
			driver.findElement(accountDropDown).click();
			driver.findElement(logoutButton).click();
			Thread.sleep(2000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
