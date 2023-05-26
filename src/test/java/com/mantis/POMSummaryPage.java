package com.mantis;

import com.utility.IssueVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class POMSummaryPage {
    String summarySeverity = "//th[contains(text(),'By Severity')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
    String summaryStatus = "//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
    String summaryCategory = "//th[contains(text(),'By Category')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'";
    String traverse = "')]/parent::tr/td[5]";
    By SummaryProject = By.xpath("//th[contains(text(),'By Project')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'automation')]/parent::tr/td[5]");
    By SummaryStatus = By.xpath("//th[contains(text(),'By Status')]/parent::tr/parent::thead/parent::table/tbody/tr//td[contains(text(),'new')]/parent::tr/td[5]");

    String[] reqSummary = new String[4];
    WebDriver driver;

    public POMSummaryPage(WebDriver driver) { this.driver = driver; }

    public void fetchSummaryDetails(String sever, String catog) {

        IssueVariable iv = new IssueVariable();

        reqSummary[0] = driver.findElement(SummaryProject).getText();

        if (!driver.findElements(By.xpath(summaryStatus + "new" + traverse)).isEmpty())
            reqSummary[1] = driver.findElement(By.xpath(summaryStatus + "new" + traverse)).getText();
        else if (iv.status.containsKey("new"))
            reqSummary[1] = Integer.toString(0);
        if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty())
            reqSummary[2] = driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText();
        else if (iv.sever.containsKey(sever))
            reqSummary[2] = Integer.toString(0);
        if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty())
            reqSummary[3] = driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText();
        else if (iv.catog.containsKey(catog))
            reqSummary[3] = Integer.toString(0);

        // System.out.println(reqSummary[0]);
        // System.out.println(reqSummary[1]);
        // System.out.println(reqSummary[2]);
        // System.out.println(reqSummary[3]);
    }

    public boolean validateSummary(String sever, String catog) throws NoSuchElementException {

        boolean status = driver.findElement(SummaryProject).getText().equals(Integer.toString(Integer.parseInt(reqSummary[0]) + 1));

        if (!driver.findElement(SummaryStatus).getText().equals(Integer.toString(Integer.parseInt(reqSummary[1]) + 1)))
            status = false;

        if (!driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText()
                .equals(Integer.toString(Integer.parseInt(reqSummary[2]) + 1)))
            status = false;

        if (!driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText()
                .equals(Integer.toString(Integer.parseInt(reqSummary[3]) + 1)))
            status = false;

        return status;
    }

    public void fetchSummaryDetails(String sever, String catog, String statusc) throws NoSuchElementException {

        IssueVariable iv = new IssueVariable();

        reqSummary[0] = driver.findElement(SummaryProject).getText();
        if (!driver.findElements(By.xpath(summaryStatus + statusc + traverse)).isEmpty())
            reqSummary[1] = driver.findElement(By.xpath(summaryStatus + statusc + traverse)).getText();
        else if (iv.status.containsKey(statusc))
            reqSummary[1] = Integer.toString(0);
        if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty())
            reqSummary[2] = driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText();
        else if (iv.sever.containsKey(sever))
            reqSummary[2] = Integer.toString(0);
        if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty())
            reqSummary[3] = driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText();
        else if (iv.catog.containsKey(catog))
            reqSummary[3] = Integer.toString(0);
    }

    public boolean validateSummary(String sever, String catog, String statusc) throws NoSuchElementException {

        boolean status = false;
        int count = 0;

        if (driver.findElement(SummaryProject).getText()
                .equals(Integer.toString(Integer.parseInt(reqSummary[0]) + 1))) {
            count += 1;
        }

        if (!driver.findElements(By.xpath(summaryStatus + statusc + traverse)).isEmpty()) {
            if (driver.findElement(By.xpath(summaryStatus + statusc + traverse)).getText()
                    .equals(Integer.toString(Integer.parseInt(reqSummary[1]) + 1))) {

                count += 1;
            }
        }

        if (!driver.findElements(By.xpath(summarySeverity + sever + traverse)).isEmpty()) {
            if (driver.findElement(By.xpath(summarySeverity + sever + traverse)).getText()
                    .equals(Integer.toString(Integer.parseInt(reqSummary[2]) + 1))) {
                count += 1;
            }
        }

        if (!driver.findElements(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).isEmpty()) {
            if (driver.findElement(By.xpath(summaryCategory + catog.split(" ")[2] + traverse)).getText()
                    .equals(Integer.toString(Integer.parseInt(reqSummary[3]) + 1))) {
                count += 1;
            }
        }

        if(count == 4) {
            status = true;
        }

        return status;
    }
}
