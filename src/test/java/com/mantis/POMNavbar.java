package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMNavbar {

    By accountDropDown = By.xpath("//*[@id='navbar-container']/div[2]/ul/li[3]/a/i[2]");
    By logoutButton = By.xpath("//*[@id='navbar-container']/div[2]/ul/li[3]/ul/li[4]/a");
    By accountName = By.xpath("//*[@id='navbar-container']/div[2]/ul/li[3]/a/span");

    WebDriver driver;

    public POMNavbar(WebDriver driver) {
        this.driver = driver;
    }

    public void logout() {
        try {
            driver.findElement(accountDropDown).click();
            driver.findElement(logoutButton).click();
            Thread.sleep(2000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void checkHomePage() throws NoSuchElementException {
        driver.findElement(accountName).isDisplayed();
    }
}
