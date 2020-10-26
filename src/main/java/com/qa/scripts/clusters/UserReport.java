package com.qa.scripts.clusters;

import com.qa.pagefactory.UserReportPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */
public class UserReport {

    private static final Logger LOGGER = Logger.getLogger(UserReport.class.getName());
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private UserReportPageObject userReportPageObject;


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
    /* This method is to close the confirmation message that appears on landing of page */
    public void scheduleConfirmationMessageNotification() {
        if (userReportPageObject.confirmationMessageschedule.size() > 0) {
            waitExecuter.waitUntilElementClickable(userReportPageObject.confirmationMessageschedule.get(0));
            JavaScriptExecuter.clickOnElement(driver, userReportPageObject.confirmationMessageschedule.get(0));
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
        userReportPageObject.readUsersDropdown.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(1000);

    }


    /**
     * Method to Click on Queue
     */
    public void selectQueue() {
        List<WebElement> listOfQueue = userReportPageObject.dropdownOptions;
        userReportPageObject.queuesDropdown.click();
        waitExecuter.sleep(1000);
        listOfQueue.get(0).click();
        waitExecuter.sleep(1000);
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
        waitExecuter.sleep(1000);
        userReportPageObject.topXNumber.clear();
        userReportPageObject.topXNumber.stream().filter(WebElement::isDisplayed)
                .findFirst().get().sendKeys(number);
    }

    /**
     * Method to Click  add scheduler on user report page
     */
    public void addschedule() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        userReportPageObject.Shedulename.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(1000);
        String Char = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        userReportPageObject.Shedulename.stream().filter(WebElement::isDisplayed)
                .findFirst().get().sendKeys(Char);


    }

    /**
     * Method to Click  verify scheduler on user report page
     */

    public void schedule(String schedulename) {
        userReportPageObject.Shedulename.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(1000);
        userReportPageObject.Shedulename.stream().filter(WebElement::isDisplayed)
                .findFirst().get().sendKeys(schedulename);

    }

    /**
     * Method to Click  verify scheduler on user report table
     */
    public void verifyschedule(String schedulename) {
        userReportPageObject.searchShedulename.stream().filter(WebElement::isDisplayed)
                .findFirst().get().sendKeys(schedulename);
        waitExecuter.sleep(1000);
    }


    /**
     * Method to Click  save schedule on user report page
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
            LOGGER.info("Click On add Button");
            MouseActions.clickOnElement(driver, userReportPageObject.addbutton);
        } catch (TimeoutException e) {
            LOGGER.severe("Class UserReport | Method clickOnaddButton | Exception desc" + e.getMessage());
            throw (e);

        }
    }

    /**
     * Method to Click on shedule user report
     */
    public void clicksheduleusereport() {
        try {
            LOGGER.info("Click On shedule usereport");
            MouseActions.clickOnElement(driver, userReportPageObject.scheduleuserreportButton);
        } catch (TimeoutException e) {
            LOGGER.severe("Class UserReport | Method clickOnaddButton | Exception desc" + e.getMessage());
            throw (e);
        }
    }
}

