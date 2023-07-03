package com.mantisbt;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment {

	private static final String entryPoint = "http://localhost/mantisbt";
	private static final String username = "administrator";
	private static final String password = "root1";
	
	public static void main(String[] args) {
		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		driver.get(entryPoint);
		
//		----------------------------------------------------------------------------------------
		
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		
// 		----------------------------------------------------------------------------------------
		
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[3]/a")).click();
		
		WebElement category = driver.findElement(By.id("category_id"));
		new Select(category).selectByVisibleText("[All Projects] Selenium");  
		
		WebElement reproducibilty = driver.findElement(By.id("reproducibility"));
		new Select(reproducibilty).selectByVisibleText("sometimes");
		
		WebElement severity = driver.findElement(By.id("severity"));
		new Select(severity).selectByVisibleText("trivial");
		
		WebElement priority = driver.findElement(By.id("priority"));
		new Select(priority).selectByVisibleText("low");
		
		driver.findElement(By.id("platform")).sendKeys("Tablet");
		driver.findElement(By.id("os")).sendKeys("Fedora");
		driver.findElement(By.id("os_build")).sendKeys("37.0");
		
		driver.findElement(By.id("summary")).sendKeys("Summary_02");
		driver.findElement(By.id("description")).sendKeys("Description_02");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 450)", "");
		
		driver.findElement(By.id("steps_to_reproduce")).sendKeys("Some steps to reproduce");
		
		js.executeScript("window.scrollBy(0, 450)", "");
		
		driver.findElement(By.id("additional_info")).sendKeys("Some additional info");
		driver.findElement(By.id("tag_string")).sendKeys("tag1, tag2");
		
//		Skipping the automation process to upload the file
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Submit Issue']")).click();
	}
}
