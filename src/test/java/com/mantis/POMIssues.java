package com.mantis;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMIssues {
    By IssueCat = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr[@class='bug-header-data']//td[@class='bug-category']");
    By IssueRepro = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-reproducibility']");
    By IssueSev = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-severity']");
    By IssuePri = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-priority']");
    By IssueSum = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-summary']");
    By IssueDesc = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-description']");
    By IssueStat = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-status']");
    By IssueRes = By.xpath("//div[@class='table-responsive'][1]/table/tbody//tr/td[@class='bug-resolution']");

    WebDriver driver;

    public POMIssues(WebDriver driver) { this.driver = driver; }

    public boolean validateIssue(String catog, String repro, String sever, String prior, String summary,
                                 String description) throws NoSuchElementException {
        boolean status = true;

        if (!catog.contains(driver.findElement(IssueCat).getText())) {
            status = false;
            System.out.println("cat" + (driver.findElement(IssueCat).getText()) + catog);
        }

        if (!repro.contains(driver.findElement(IssueRepro).getText())) {
            status = false;
            System.out.println("repro" + (driver.findElement(IssueRepro).getText()) + repro);
        }

        if (!sever.contains(driver.findElement(IssueSev).getText())) {
            status = false;
            System.out.println("sever" + (driver.findElement(IssueSev).getText()) + sever);
        }

        if (!prior.contains(driver.findElement(IssuePri).getText())) {
            status = false;
            System.out.println("prior" + (driver.findElement(IssuePri).getText()) + prior);
        }

        if (!driver.findElement(IssueSum).getText().contains(summary)) {
            status = false;
            System.out.println("sum" + (driver.findElement(IssueSum).getText()) + summary);
        }

        if (!description.contains(driver.findElement(IssueDesc).getText())) {
            status = false;
            System.out.println("desc" + (driver.findElement(IssueDesc).getText()) + description);
        }

        return status;
    }

    public boolean validateUpdate(String stat, String resolution) throws NoSuchElementException {

        boolean status = stat.contains(driver.findElement(IssueStat).getText());

        // System.out.println("cat" + (driver.findElement(IssueStat).getText()) + stat);
        if (!resolution.contains(driver.findElement(IssueRes).getText())) {
            status = false;
            // System.out.println("repro" + (driver.findElement(IssueRes).getText()) +
            // resolution);
        }

        return status;
    }
}
