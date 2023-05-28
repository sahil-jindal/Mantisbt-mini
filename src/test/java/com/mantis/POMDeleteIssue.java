package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMDeleteIssue {

    By confirmDeleteButton = By.xpath("//input[@type='submit'][@value='Delete Issues']");

    WebDriver driver;

    public POMDeleteIssue(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnConfirmDeleteButton() throws NoSuchElementException {
        driver.findElement(confirmDeleteButton).click();
    }
}
