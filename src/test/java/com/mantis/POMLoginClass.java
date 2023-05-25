package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POMLoginClass {

	By usernameField = By.xpath("//*[@id='username']");
	By passwordField = By.xpath("//*[@id='password']");
	By submitButton = By.xpath("//input[@type='submit']");

	WebDriver driver;

	public POMLoginClass(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String un, String pwd) {
		try {
			driver.get("http://localhost/mantisbt/login_page.php");
			driver.findElement(usernameField).sendKeys(un);
			driver.findElement(submitButton).click();
			driver.findElement(passwordField).sendKeys(pwd);
			driver.findElement(submitButton).click();
		} catch (Exception e) {
			System.out.println("Login Failed");
		}
	}
}