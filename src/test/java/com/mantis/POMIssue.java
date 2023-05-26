package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMIssue {

    By deleteButton = By.xpath("//input[@type='submit'][@value='Delete']");
    By editButton = By.xpath("//input[@type='submit'][@value='Edit']");

    WebDriver driver;

    public POMIssue(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnDeleteButton() {
        driver.findElement(deleteButton).click();
    }

    public void clickOnEditButton() throws NoSuchElementException {
        driver.findElement(editButton).click();
    }

}
