package com.qa.testcases.jobs.applications.all;

/*To test-
 * This testcase should list all the applications with events selected in events slider filter
 */

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */
@Marker.AllApps
@Marker.All
public class TC_JAL_13 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_13.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyEventsSliderFilter(String clusterId) {
        test = extent.startTest("TC_JAL_13.VerifyEventsSliderFilter",
                "Verify events selected in slider matches the events of application");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        allApps.inJobsSelectClusterAndLast7Days(clusterId);
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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.clickOnAppId);
        applicationsPageObject.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
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
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
        waitExecuter.sleep(1000);
        applicationsPageObject.closeIcon.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        // Click on the first app in table to get efficiency
        //int countEfficiency = Collections.frequency(badgeTitleTextList, "EFFICIENCY");
        int countEfficiency = badgeTitleTextList.size();
        test.log(LogStatus.INFO, "Count of Efficiency badges " + countEfficiency);
        LOGGER.info("Count of Efficiency badges " + countEfficiency);
        // Assert the efficiency count to events set in slider
        test.log(LogStatus.INFO, "Assert the efficiency count to events set in slider");
        LOGGER.info("eventInputLeft- " + eventInputLeft);
        LOGGER.info("eventInputRight- " + eventInputRight);
        LOGGER.info("countEvents- " + countEfficiency);
        Assert.assertTrue(countEfficiency >= eventInputLeft && countEfficiency <= eventInputRight,
                "The Efficiency count is not in between the set events");
        // Reset events to default
        test.log(LogStatus.INFO, "Reset events to default");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        applicationsPageObject.resetButton.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
