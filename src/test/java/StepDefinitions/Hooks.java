package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.mantis.POM.POMLoginClass;
import com.utility.DriverLib;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {

	static DriverLib libDriver = new DriverLib();
	static WebDriver driver = libDriver.getWebDriver();

	@BeforeAll
	public static void beforeAll() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
		System.out.printf("| %-12s | %-40s | %-7s |%-12s |%n", "TEST", "STEPS", "RESULT", "VALIDATION");
	}
	
	@Before
	public void login() {
		POMLoginClass login = new POMLoginClass(driver);
		login.login("administrator", "root1");
	}

	@After
	public void logout() {
		driver = libDriver.getWebDriver();
		driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/a/i[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"navbar-container\"]/div[2]/ul/li[3]/ul/li[4]/a")).click();
		System.out.printf("| %-12s | %-40s | %-7s |%-12s |%n", "", "LOGOUT", "PASS", "");
	}

	@AfterAll
	public static void final_quit() {
		System.out.printf("-----------------------------------------------------------------------------------%n");
	}
}
