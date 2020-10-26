package com.qa.scripts;

import com.qa.pagefactory.DatePickerPageObject;
import com.qa.utils.DateUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ankur Jaiswal
 * This class contains all datepicker related action methods
 */

public class DatePicker {
    public DatePickerPageObject datePickerPageObject;
    public WaitExecuter waitExecuter;
    private WebDriver driver;

    /**
     * @param driver - WebDriver instance
     *               Constructor method initialize driver, datepicker object
     */
    public DatePicker(WebDriver driver) {
        this.driver = driver;
        datePickerPageObject = new DatePickerPageObject(driver);
        waitExecuter = new WaitExecuter(driver);
    }

    /**
     * This method used to click on date picker to open datepicker dropdown
     */
    public void clickOnDatePicker() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.dateRange);
        JavaScriptExecuter.clickOnElement(driver, datePickerPageObject.dateRange);
        waitExecuter.sleep(3000);
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
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last30Days);
        datePickerPageObject.last30Days.click();
    }

    /**
     * Method to select 'Last 60 Days' in data picker
     */
    public void selectLast60Days() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last60Days);
        datePickerPageObject.last60Days.click();
    }

    /**
     * Method to select 'Last 90 Days' in data picker
     */
    public void selectLast90Days() {
        waitExecuter.waitUntilElementPresent(datePickerPageObject.last90Days);
        datePickerPageObject.last90Days.click();
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

        if (calendarDate)
            return true;
        else
            return false;
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
}
