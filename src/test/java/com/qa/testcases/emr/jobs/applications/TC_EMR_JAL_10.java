package com.qa.testcases.emr.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@Marker.EMRAllApps
public class TC_EMR_JAL_10 extends BaseClass {
    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JAL_10.class);

    @Test()
    public void VerifyEventsSliderFilter() {
        test = extent.startTest("TC_EMR_JAL_10.VerifyEventsSliderFilter",
                "Verify events selected in slider matches the events of application");
        test.assignCategory("Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects",test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        test.log(LogStatus.INFO, "Select last 7 days");
        allApps.inJobsSelectLast7Days();
        // Slide the event slider to get min and max value of events
        test.log(LogStatus.INFO, "Slide the event slider to get min and max value of events");
        LOGGER.info("Slide the event slider to get min and max value of events",test);
        WebElement eventSlider = applicationsPageObject.eventsSlider;
        allApps.moveTheSlider(eventSlider, 50);
        int eventInputLeft = Integer.parseInt(applicationsPageObject.eventsSliderInputLeft.getAttribute("value"));
        waitExecuter.sleep(2000);
        int eventInputRight = Integer.parseInt(applicationsPageObject.eventsSliderInputRight.getAttribute("value"));
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Event Slider From : " + eventInputLeft + " To : " + eventInputRight);
        LOGGER.info("Event Slider From : " + eventInputLeft + " To : " + eventInputRight,test);
        waitExecuter.sleep(2000);
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.clickOnAppId);
        applicationsPageObject.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
        driver.getWindowHandle();
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Click on the first app in table to get efficiency");
        LOGGER.info("Click on the first app in table to get efficiency",test);
        List<WebElement> badgeTitlesList = applicationsPageObject.getBadgeTitle;
        List<String> badgeTitleTextList = new ArrayList<>();
        for (int i = 0; i < badgeTitlesList.size(); i++) {
            badgeTitleTextList.add(badgeTitlesList.get(i).getText());
            LOGGER.info("Badge tag- " + badgeTitlesList.get(i).getText(),test);
        }
        // Click on the first app in table to get efficiency
        test.log(LogStatus.INFO, "Navigate back to parent window");
        LOGGER.info("Navigate back to parent window",test);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.closeIcon);
        waitExecuter.sleep(1000);
        applicationsPageObject.closeIcon.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);

        // Click on the first app in table to get efficiency
        //int countEfficiency = Collections.frequency(badgeTitleTextList, "EFFICIENCY");
        int countEfficiency = badgeTitleTextList.size();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Count of Efficiency badges " + countEfficiency);
        LOGGER.info("Count of Efficiency badges " + countEfficiency,test);
        // Assert the efficiency count to events set in slider
        test.log(LogStatus.INFO, "Assert the efficiency count to events set in slider");
        LOGGER.info("eventInputLeft- " + eventInputLeft,test);
        LOGGER.info("eventInputRight- " + eventInputRight,test);
        LOGGER.info("countEvents- " + countEfficiency,test);
        Assert.assertTrue(countEfficiency >= eventInputLeft && countEfficiency <= eventInputRight,
                "The Efficiency count is not in between the set events");
        // Reset events to default
        test.log(LogStatus.INFO, "Reset events to default");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        applicationsPageObject.resetButton.click();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }
}
