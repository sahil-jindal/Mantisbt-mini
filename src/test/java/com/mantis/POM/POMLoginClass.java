package com.mantis.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POMLoginClass {

	By UN = By.xpath("//*[@id=\"username\"]");
	By PWD = By.xpath("//*[@id=\"password\"]");
	By SUBMIT = By.xpath("//input[@type='submit']");

	WebDriver driver;

	public POMLoginClass(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver login(String un, String pwd) {
		try {

			driver.get("http://localhost/mantisbt/login_page.php");
			driver.findElement(UN).sendKeys(un);
			driver.findElement(SUBMIT).click();
			driver.findElement(PWD).sendKeys(pwd);
			driver.findElement(SUBMIT).click();
		} catch (Exception e) {
			System.out.println("Login Failed");
		}

		return driver;
	}
}