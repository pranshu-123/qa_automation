package com.qa.scripts;

import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SchedulePageObject;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.*;
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
        schedulePageObject.Sunday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Monday' in Schedule
     */
    public void selectMonday() {
        schedulePageObject.Monday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Tuesday' in Schedule
     */
    public void selectTuesday() {
        schedulePageObject.Tuesday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Wednesday' in Schedule
     */
    public void selectWednesday() {
        schedulePageObject.Wednesday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Thursday' in Schedule
     */
    public void selectThursday() {
        schedulePageObject.Thursday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Friday' in Schedule
     */
    public void selectFriday() {
        schedulePageObject.Friday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
    }

    /**
     * Method to select 'Saturday' in Schedule
     */
    public void selectSaturday() {
        schedulePageObject.Saturday.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();

    }

    /**
     * Method to select 'Everytwoweeks' in Schedule
     */
    public void selectEverytwoweeks() {
        schedulePageObject.Everytwoweeks.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();

    }

    /**
     * Method to select 'Everymonths' in Schedule
     */
    public void selectEverymonths() {
        schedulePageObject.Everymonths.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();

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
        schedulePageObject.timepickerdropdown.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(3000);
    }

    /**
     * Method to select timepicker hours
     */
    public void clickOnhours() {
        schedulePageObject.hoursRange.stream()
                        .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(3000);
    }


    /**
     * Method to select timepicker twentythreehours
     */
    public void selecttwentythreehours() {
        schedulePageObject.twentythreehours.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(3000);
    }
    /**
     * Method to Click on schedule dropdown
     */

    public void scheduletorun(Schedule schedule) {

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
     * Method to select timepicker in  minutes
     */
    public void clickOnminutes() {
        schedulePageObject.minutes.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(3000);
    }

    /**
     * select timepicker  'Fiftynine' seconds
     */
    public void selectFiftynine() {
        schedulePageObject.Fiftynine.stream()
                .filter(WebElement::isDisplayed).findFirst().get().click();
        waitExecuter.sleep(3000);
    }
}
