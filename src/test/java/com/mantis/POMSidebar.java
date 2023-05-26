package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMSidebar {
    By ViewIssueButton = By.xpath("//*[@id='sidebar']/ul/li[2]/a/span");
    By ReportIssueButton = By.xpath("//*[@id='sidebar']/ul/li[3]/a/span");

    WebDriver driver;

    public POMSidebar(WebDriver driver) {
        this.driver = driver;
    }

    public void goToReportIssuePage() throws NoSuchElementException {
        driver.findElement(ReportIssueButton).click();
    }

    public void goToViewIssuePage() throws NoSuchElementException {
        driver.findElement(ViewIssueButton).click();
    }
}
