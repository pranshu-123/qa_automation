package com.qa.scripts.clusters;

import com.qa.pagefactory.UserReportPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author Sarbashree Ray
 * This class contains all User report related  action methods
 * An implementation of the page object model can be
 * achieved by separating the abstraction of the test object and the test scripts
 */
public class UserReport {

    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private UserReportPageObject userReportPageObject;


    public UserReport(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        userReportPageObject = new UserReportPageObject(driver);
    }

    /**
     * This method verify Header Message on UserReport page
     */
    public void HeaderMessage() {
        if (userReportPageObject.HeaderElement.size() > 0) {
            waitExecuter.waitUntilElementClickable(userReportPageObject.HeaderElement.get(0));
            JavaScriptExecuter.clickOnElement(driver, userReportPageObject.HeaderElement.get(0));
        }
    }

    /**
     * This method verify the schedule Button on UserReport page
     */
    public void clickscheduleButton() {
        MouseActions.clickOnElement(driver, userReportPageObject.scheduleuserreportButton);
    }

}

