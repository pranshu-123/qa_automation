package com.qa.scripts.clusters;

import com.qa.pagefactory.UserReportPageObject;
import com.qa.scripts.Schedule;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.logging.Logger;
/**
 * @author Sarbashree Ray
 */
public class UserReport {

    private static final Logger LOGGER = Logger.getLogger(UserReport.class.getName());
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private UserReportPageObject userReportPageObject;
    Schedule schedule=new Schedule(driver);

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public UserReport(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        userReportPageObject = new UserReportPageObject(driver);
    }

    /**
     * Method to Click on HeaderMessage
     */
    public void HeaderMessage() {
        if (userReportPageObject.HeaderElement.size() > 0) {
            waitExecuter.waitUntilElementClickable(userReportPageObject.HeaderElement.get(0));
            JavaScriptExecuter.clickOnElement(driver, userReportPageObject.HeaderElement.get(0));
        }
    }

    /**
     * Method to Click on scheduledReportTbl
     */
    public Boolean scheduledReportTbl() {
        waitExecuter.waitUntilElementPresent(userReportPageObject.tblScheduledReportsApps);
        System.out.println(userReportPageObject.tblScheduledReportsRowsList.size());
        Boolean boolTbl = false;
        if (userReportPageObject.tblScheduledReportsRowsList.size() > 0) {
            boolTbl = true;
        }

        return boolTbl;
    }

    public void clickscheduleButton() {
        MouseActions.clickOnElement(driver, userReportPageObject.scheduleuserreportButton);
    }
    /**
     * Method to Click on schedule dropdown
     */
    public void scheduletorun() {
        schedule.clickOnSchedule();
        waitExecuter.sleep(1000);

        schedule.clicktimepicker();
        waitExecuter.sleep(2000);

        schedule.clickOndropdown();
        waitExecuter.sleep(2000);

        schedule.clickOnhours();
        waitExecuter.sleep(1000);

        schedule.selecttwentythreehours();
        waitExecuter.sleep(1000);

        schedule.clickOnminutes();
        waitExecuter.sleep(1000);

        schedule.selectFiftynine();
        waitExecuter.sleep(1000);
    }
    /**
     * Method to Click on Clusterstab
     */
    public void selectClusterstab() {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Click on userReport tab
        MouseActions.clickOnElement(driver, userReportPageObject.unravelLogo);
        // Click on userReport dropdown
        waitExecuter.sleep(3000);

    }

    /**
     * Method to Click on RealUser
     */
    public void selectRealUser() {
        List<WebElement> listOfUsers = userReportPageObject.dropdownOptions;
        userReportPageObject.readUsersDropdown.click();
        waitExecuter.sleep(1000);

        listOfUsers.get(0).click();

    }

    /**
     * Method to Click on Queue
     */
    public void selectQueue() {
        List<WebElement> listOfQueue = userReportPageObject.dropdownOptions;
        userReportPageObject.queuesDropdown.click();
        waitExecuter.sleep(1000);
        listOfQueue.get(0).click();
    }

    /**
     * Method to Click on Email
     */
    public void assignEmail(String emaiId) {
        userReportPageObject.emailNotification.click();
        waitExecuter.sleep(1000);
        userReportPageObject.emailNotification.sendKeys(emaiId);
    }

    /**
     * Method to Click on setTopXNumber
     */
    public void setTopXNumber(String number) {
        waitExecuter.waitUntilElementPresent(userReportPageObject.topXNumber);
        waitExecuter.sleep(1000);
        userReportPageObject.topXNumber.clear();
        JavaScriptExecuter.clearTextField(driver, userReportPageObject.topXNumber);
        userReportPageObject.topXNumber.sendKeys(number);
    }

    /**
     * Method to Click  add scheduler on user report page
     */
    public void addscheduler(String Shedulename) {
        userReportPageObject.Shedulename.click();
        waitExecuter.sleep(1000);
        userReportPageObject.Shedulename.sendKeys(Shedulename);
    }


    public void addtime() {
        List<WebElement> daysShedulerun = userReportPageObject.addtime;
        userReportPageObject.addhour.click();
        userReportPageObject.addminutes.click();
        waitExecuter.sleep(1000);
        daysShedulerun.get(0).click();
    }

    /**
     * Method to Click  saveschedule on user report page
     */
    public void clicksaveschedule() {
        MouseActions.clickOnElement(driver,
                userReportPageObject.saveschedule);
    }

    /**
     * Method to Click on addbutton
     */
    public void clickOnaddButton() {
        try {
            MouseActions.clickOnElement(driver, userReportPageObject.addbutton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, userReportPageObject.cancelbutton);
        }
    }

    /**
     * Method to Click on shedule user report
     */
    public void clicksheduleusereport() {
        try {
            MouseActions.clickOnElement(driver, userReportPageObject.scheduleuserreportButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, userReportPageObject.closebutton);
        }
    }
}

