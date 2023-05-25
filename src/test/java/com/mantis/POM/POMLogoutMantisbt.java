package com.mantis.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.DriverLib;

public class POMLogoutMantisbt {

	WebDriver driver;
	DriverLib libDriver = new DriverLib();

	public void logout() throws Exception {
		driver = libDriver.getWebDriver();
		driver.findElement(By.xpath("//a[@href='#']//span[contains(text(),'administrator')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		System.out.println("user logout");
		Thread.sleep(2000);
	}
}
