package com.qa.scripts;

import com.qa.enums.DatePickerOptions;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.utils.DateUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ankur Jaiswal
 * This class contains all datepicker related action methods
 */

public class DatePicker {
    private final WebDriver driver;
    private final UserActions userActions;
    public DatePickerPageObject datePickerPageObject;
    public WaitExecuter waitExecuter;

    /**
     * @param driver - WebDriver instance
     *               Constructor method initialize driver, datepicker object
     */
    public DatePicker(WebDriver driver) {
        this.driver = driver;
        datePickerPageObject = new DatePickerPageObject(driver);
        waitExecuter = new WaitExecuter(driver);
        userActions = new UserActions(driver);
    }

    /**
     * This method used to click on date picker to open datepicker dropdown
     */
    public void clickOnDatePicker() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
        userActions.performActionWithPolling(datePickerPageObject.dateRange, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
    }

    public void clickOnDatePickerForTopX() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRangeTopx);
        userActions.performActionWithPolling(datePickerPageObject.dateRangeTopx, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRangeTopx);
    }

    /**
     * Method to select 'Last 14 Days' in data picker
     */
    public void selectLast14Days() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last14Days);
        datePickerPageObject.last14Days.click();
    }

    public int getDatePickerYPosition() {
        return datePickerPageObject.dateRange.getLocation().getY();
    }

    public String getDefaultDate() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.dateRange);
        return datePickerPageObject.dateRange.getText();
    }

    /**
     * Select the Custom Range in the datepicker
     */
    public void selectCustomRange() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.customRange);
        datePickerPageObject.customRange.click();
        waitExecuter.sleep(1000);
    }

    public void clickOnCustomDateApplyBtn() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.applyBtn);
        datePickerPageObject.applyBtn.click();
        waitExecuter.sleep(1000);
    }

    /**
     * Method to select 'Last 1 hour' in data picker
     */
    public void selectLastOneHour() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.lastOneHour);
        datePickerPageObject.lastOneHour.click();
    }

    /**
     * Method to select 'Last 2 hour' in data picker
     */
    public void selectLastTwoHour() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.lastTwoHour);
        datePickerPageObject.lastTwoHour.click();
    }

    /**
     * Method to select 'Last 6 hour' in data picker
     */
    public void selectLastSixHour() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.lastSixHour);
        datePickerPageObject.lastSixHour.click();
    }

    /**
     * Method to select 'Last 12 hour' in data picker
     */
    public void selectLast12Hour() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last12Hour);
        datePickerPageObject.last12Hour.click();
    }

    /**
     * Method to select 'Today' in data picker
     */
    public void selectToday() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.today);
        datePickerPageObject.today.click();
    }

    /**
     * Method to select 'Yesterday' in data picker
     */
    public void selectYesterday() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.yesterday);
        datePickerPageObject.yesterday.click();
    }

    /**
     * Method to select 'Last 7 Days' in data picker
     */
    public void selectLast7Days() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last7Days);
        datePickerPageObject.last7Days.click();
    }

    /**
     * Method to select 'Last 30 Days' in data picker
     */
    public void selectLast30Days() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.last30Days);
        userActions.performActionWithPolling(datePickerPageObject.last30Days, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
    }

    /**
     * Method to select 'Last 60 Days' in data picker
     */
    public void selectLast60Days() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.last60Days);
        userActions.performActionWithPolling(datePickerPageObject.last60Days, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
    }

    /**
     * Method to select 'Last 90 Days' in data picker
     */
    public void selectLast90Days() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.last90Days);
        userActions.performActionWithPolling(datePickerPageObject.last90Days, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(datePickerPageObject.dateRange);
    }

    /**
     * Method to select 'Last 90 Days' in data picker
     */
    public void selectCurrentDay() {
        waitExecuter.waitUntilElementClickable(datePickerPageObject.currentDay);
        userActions.performActionWithPolling(datePickerPageObject.currentDay, UserAction.CLICK);
    }

    /**
     * Method to select 'This Month' in data picker
     */
    public void selectThisMonth() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.thisMonth);
        datePickerPageObject.thisMonth.click();
    }

    /**
     * Method to select 'Last Month' in data picker
     */
    public void selectLastMonth() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.lastMonth);
        datePickerPageObject.lastMonth.click();
    }

    /**
     * Set startdate in Custom Range in the datepicker
     */

    public void setStartDate(String startDate) {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.customRangeStartDate);
        datePickerPageObject.customRangeStartDate.clear();
        datePickerPageObject.customRangeStartDate.sendKeys(startDate);
    }

    /**
     * Set enddate in Custom Range in the datepicker
     */

    public void setEndDate(String endDate) {

        waitExecuter.waitUntilElementPresent(datePickerPageObject.customRangeEndDate);
        datePickerPageObject.customRangeEndDate.clear();
        datePickerPageObject.customRangeEndDate.sendKeys(endDate);

    }

    /**
     * Set startdate by giving number of days less than current date and enddate as
     * of current date in Custom Range in the datepicker
     */

    public void setCurrentAndPastDate(Integer minusDays) {
        waitExecuter.sleep(2000);
        setStartDate(DateUtils.getPastDate(minusDays));
        waitExecuter.sleep(2000);
        setEndDate(DateUtils.getCurrentDate());
    }

    /**
     * Select Current date as start/past date and End date as future date
     */
    public void setStartAndEndDateWithDaysDifference(Integer startDayDifferenceFromToday, Integer endDayFromToday) {
        setStartDate(DateUtils.getDateWithDayDifference(startDayDifferenceFromToday));
        setEndDate(DateUtils.getDateWithDayDifference(endDayFromToday));
    }

    /**
     * Get the set date from UI calendar bar
     */

    public String getSetDateFromCalendar() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.calendarDate);
        String setDateIs = datePickerPageObject.calendarDate.getText();
        return setDateIs;
    }

    /**
     * Change the format of the set date to the desired format
     */

    public String convertDate(String dateString, String fromDateFormat, String toDateFormat) {

        String convertedDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(fromDateFormat);
            Date date = dateFormat.parse(dateString);
            convertedDate = new SimpleDateFormat(toDateFormat).format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    /**
     * Change the format of the start date and end date
     */

    public String convertedSetDate(String startDate, String endDate) {

        String convertedStartDate = convertDate(startDate, "MM/dd/yyyy", "MMMM d, yyyy");

        String convertedEndDate = convertDate(endDate, "MM/dd/yyyy", "MMMM d, yyyy");

        String finalDate = convertedStartDate + " - " + convertedEndDate;

        return finalDate;

    }

    /**
     * Convert the current and past date from 'MM/dd/yyyy', 'MMMM d, yyyy' format
     */

    public String convertedSetCurrentAndPastDate(Integer minusDays) {

        String convertedStartDate = convertDate(DateUtils.getPastDate(minusDays), "MM/dd/yyyy", "MMMM d, yyyy");

        String convertedEndDate = convertDate(DateUtils.getCurrentDate(), "MM/dd/yyyy", "MMMM d, yyyy");

        String finalDate = convertedStartDate + " - " + convertedEndDate;

        System.out.println(finalDate);

        return finalDate;

    }

    /* Will return true if date is displayed in calendar tab */
    public Boolean isDateDisplayedInCalendar() {

        Boolean calendarDate = datePickerPageObject.calendarDate.isDisplayed();

        return calendarDate;
    }

    /**
     * This method used to get DatePicker options
     */
    public List<String> getDatePickerOptions() {
        List<String> list = new ArrayList<>();
        for (WebElement element : datePickerPageObject.dateRangeOptions) {
            list.add(element.getText());
        }
        return list;
    }

    /**
     * Select date from date range
     * @param dateOption
     */
    public void selectDateOptionFromDate(DatePickerOptions dateOption) {
        waitExecuter.sleep(3000);
        switch (dateOption) {
            case CURRENT_DAY:
                selectCurrentDay();
                break;
            case LAST_7_DAYS:
                selectLast7Days();
                break;
            case LAST_30_DAYS:
                selectLast30Days();
                break;
            case LAST_60_DAYS:
                selectLast60Days();
                break;
            case LAST_90_DAYS:
                selectLast90Days();
                break;
            case CUSTOM_RANGE:
                selectCustomRange();
                break;
        }
    }

    /*Select any date for start and end date through custom
    @startDateInMinus - Define days in minus from that of current date
    @endDateInMinus - Define days in minus from that of current date
     */
    public void setStartAndEndDateFromCurrentDate(Integer startDateInMinus, Integer endDateInMinus) {
        waitExecuter.sleep(2000);
        setStartDate(DateUtils.getPastDate(startDateInMinus));
        waitExecuter.sleep(2000);
        setEndDate(DateUtils.getPastDate(endDateInMinus));
    }
}
