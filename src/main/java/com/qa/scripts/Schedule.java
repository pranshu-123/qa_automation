package com.qa.scripts;

import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SchedulePageObject;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */

public class Schedule {

    private static final Logger LOGGER = Logger.getLogger(Schedule.class.getName());
    public SchedulePageObject schedulePageObject;
    public WaitExecuter waitExecuter;
    private WebDriver driver;

    /**
     * @param driver - WebDriver instance
     *               Constructor method initialize driver, Schedule object
     */
    public Schedule(WebDriver driver) {
        this.driver = driver;
        schedulePageObject = new SchedulePageObject(driver);
        waitExecuter = new WaitExecuter(driver);
    }

    public void clickOnSchedule() {
        try {
            LOGGER.info("Click On Schedule dropdown");
            List<WebElement> ScheduleList = schedulePageObject.schedule;
            Assert.assertFalse(ScheduleList.isEmpty(), "No ScheduleList loaded ");
            waitExecuter.sleep(3000);
            schedulePageObject.schedule.clear();
            schedulePageObject.schedule.stream().findFirst().get().click();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Schedule | Method clickOnSchedule | Exception desc" + e.getMessage());
            throw (e);
        }
    }


    /**
     * Method to select 'Daily' in Schedule
     */
    public void selectDaily() {
        schedulePageObject.Daily.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'sunday' in Schedule
     */
    public void selectSunday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Sunday);
        schedulePageObject.Sunday.click();
    }

    /**
     * Method to select 'Monday' in Schedule
     */
    public void selectMonday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Monday);
        schedulePageObject.Monday.click();
    }

    /**
     * Method to select 'Tuesday' in Schedule
     */
    public void selectTuesday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Tuesday);
        schedulePageObject.Tuesday.click();
    }

    /**
     * Method to select 'Wednesday' in Schedule
     */
    public void selectWednesday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Wednesday);
        schedulePageObject.Wednesday.click();
    }

    /**
     * Method to select 'Thursday' in Schedule
     */
    public void selectThursday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Thursday);
        schedulePageObject.Thursday.click();
    }

    /**
     * Method to select 'Friday' in Schedule
     */
    public void selectFriday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Friday);
        schedulePageObject.Friday.click();
    }

    /**
     * Method to select 'Saturday' in Schedule
     */
    public void selectSaturday() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Saturday);
        schedulePageObject.Saturday.click();
    }

    /**
     * Method to select 'Everytwoweeks' in Schedule
     */
    public void selectEverytwoweeks() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Everytwoweeks);
        schedulePageObject.Everytwoweeks.click();
    }

    /**
     * Method to select 'Everymonths' in Schedule
     */
    public void selectEverymonths() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Everymonths);
        schedulePageObject.Everymonths.click();
    }

    /**
     * Method to select timepicker
     */
    public void clicktimepicker() {
        Actions actions = new Actions(driver);
        waitExecuter.waitUntilElementPresent(schedulePageObject.timepicker);
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(schedulePageObject.timepicker)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
    }

    /**
     * Method to select timepicker
     */
    public void clickOndropdown() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.timepickerdropdown);
        JavaScriptExecuter.clickOnElement(driver, schedulePageObject.timepickerdropdown);
        waitExecuter.sleep(3000);
    }

    /**
     * Method to select timepicker hours
     */
    public void clickOnhours() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.hoursRange);
        JavaScriptExecuter.clickOnElement(driver, schedulePageObject.hoursRange);
        waitExecuter.sleep(3000);
    }

    public int getTimePickerYPosition() {
        return schedulePageObject.hoursRange.getLocation().getY();
    }

    /**
     * Method to select timepicker twentythreehours
     */
    public void selecttwentythreehours() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.twentythreehours);
        schedulePageObject.twentythreehours.click();
        waitExecuter.sleep(3000);
    }

    /**
     * Method to select timepicker in  minutes
     */
    public void clickOnminutes() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.minutes);
        JavaScriptExecuter.clickOnElement(driver, schedulePageObject.minutes);
        waitExecuter.sleep(3000);
    }

    /**
     * select timepicker  'Fiftynine' seconds
     */
    public void selectFiftynine() {
        waitExecuter.waitUntilElementPresent(schedulePageObject.Fiftynine);
        schedulePageObject.Fiftynine.click();
        waitExecuter.sleep(3000);
    }
}
