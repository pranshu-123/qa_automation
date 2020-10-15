package com.qa.testcases.jobs.applications.all;

/*To test-
 * This testcase should list all the applications with events selected in events slider filter
 */

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */
public class TC_JAL_13 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_13.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyEventsSliderFilter(String clusterId) {
        test = extent.startTest("TC_JAL_13.VerifyEventsSliderFilter",
                "Verify events selected in slider matches the events of application");
        test.assignCategory("4620 Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 7 days from date picker
        test.log(LogStatus.INFO, "Select last 7 days");
        LOGGER.info("Select last 7 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        // Slide the event slider to get min and max value of events
        test.log(LogStatus.INFO, "Slide the event slider to get min and max value of events");
        LOGGER.info("Slide the event slider to get min and max value of events");
        WebElement eventSlider = applicationsPageObject.eventsSlider;
        allApps.moveTheSlider(eventSlider, 50);
        int eventInputLeft = Integer.parseInt(applicationsPageObject.eventsSliderInputLeft.getAttribute("value"));
        waitExecuter.sleep(2000);
        int eventInputRight = Integer.parseInt(applicationsPageObject.eventsSliderInputRight.getAttribute("value"));
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Event Slider From : " + eventInputLeft + " To : " + eventInputRight);
        LOGGER.info("Event Slider From : " + eventInputLeft + " To : " + eventInputRight);
        waitExecuter.sleep(2000);
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency");
        applicationsPageObject.getDurationFromTable.click();
        waitExecuter.sleep(5000);
        driver.getWindowHandle();
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency");
        List<WebElement> badgeTitlesList = applicationsPageObject.getBadgeTitle;
        List<String> badgeTitleTextList = new ArrayList<>();
        for (int i = 0; i < badgeTitlesList.size(); i++) {
            badgeTitleTextList.add(badgeTitlesList.get(i).getText());
            LOGGER.info("Badge tag- " + badgeTitlesList.get(i).getText());
        }
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Navigate back to parent window");
        LOGGER.info("Navigate back to parent window");
        driver.navigate().back();
        waitExecuter.sleep(5000);
        // Click on the first app in table to get efficiency
        int countEfficiency = Collections.frequency(badgeTitleTextList, "EFFICIENCY");
        test.log(LogStatus.INFO, "Count of Efficiency badges " + countEfficiency);
        LOGGER.info("Count of Efficiency badges " + countEfficiency);
        // Assert the efficiency count to events set in slider
        test.log(LogStatus.INFO, "Assert the efficiency count to events set in slider");
        Assert.assertTrue(countEfficiency >= eventInputLeft && countEfficiency <= eventInputRight,
                "The Efficiency count is not in between the set events");
        // Reset events to default
        test.log(LogStatus.INFO, "Reset events to default");
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
    }
}
