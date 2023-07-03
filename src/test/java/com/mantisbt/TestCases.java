package com.mantisbt;

import java.util.Scanner;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class TestCases {
	
	private static final Scanner sc = new Scanner(System.in);
	
	static WebDriver driver;

	static {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost/mantisbt/login_page.php");
	}

	public static void Login() throws Exception {

		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("administrator");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("root1");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	public static void createIssue() throws Exception {
		Login();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[3]/a/span")).click();
		Thread.sleep(4000);
		
		new Select(driver.findElement(By.xpath("//*[@id='category_id']"))).selectByValue("2");
		Thread.sleep(4000);
		
		new Select(driver.findElement(By.xpath("//*[@id='reproducibility']"))).selectByValue("50");
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='summary']")).sendKeys("Summary_05");
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='description']")).sendKeys("Desc_05");
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input")).click();
		Thread.sleep(4000);
	}

	public static void updateIssue(String id) throws Exception {
		Login();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[2]/a/i")).click();
		Thread.sleep(4000);

		try {
			driver.findElement(By.linkText(id)).click();
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//input[@type='submit'][@value='Edit']")).click();
			Thread.sleep(4000);
			
			new Select(driver.findElement(By.xpath("//*[@id='status']"))).selectByVisibleText("resolved");
			Thread.sleep(4000);
			
			new Select(driver.findElement(By.xpath("//*[@id='resolution']"))).selectByVisibleText("fixed");
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//*[@id='update_bug_form']/div/div[3]/input")).click();
			
			System.out.println(id + " resolved");
			
		} catch (Exception e) {
			System.out.println("enter a valid id");
		}
	}

	public static void deleteIssue(String id) throws Exception {
		Login();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[2]/a/i")).click();
		Thread.sleep(4000);
		
		try {
			driver.findElement(By.linkText(id)).click();
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//input[@type='submit'][@value='Delete']")).click();
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//input[@type='submit'][@value='Delete Issues']")).click();
			System.out.println(" issue id " + id + " deleted");
		} catch (Exception e) {
			System.out.println("enter a valid id");
		}
	}
	
	public static void main(String[] args) throws Exception {

		System.out.println("enter your choice 1.Login , 2.Report Issue, 3.Update Issue, 4.Delete Issue ");
		int act = sc.nextInt();

		switch (act) {
		
		case 1:
			Login();
			break;

		case 2:
			createIssue();
			break;
			
		case 3:
			System.out.println("enter the issue id ");
			String id1 = sc.next();
			updateIssue(id1);
			break;

		case 4:
			System.out.println("enter the issue id ");
			String id = sc.next();
			deleteIssue(id);
			break;
		}
	}
}