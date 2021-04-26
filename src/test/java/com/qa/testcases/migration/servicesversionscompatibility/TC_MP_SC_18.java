package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_18 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_18.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateScheduleReportOnDailyBasisForGoogleDataProc(String clusterId) {

        test = extent.startTest("TC_MP_SC_18.validateScheduleReportOnDailyBasisForGoogleDataProc: " + clusterId,
                " Validate scheduling a report on a daily basis Cloud Provider - Google Data Proc");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject =
                new ServicesAndVersionsCompatibilityPageObject(driver);
        UserActions actions = new UserActions(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        LOGGER.info("Clicked on Migration and accessing Services And Versions Compatibility page ");
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        LOGGER.info("Clicked on Services And Versions Compatibility tab");
//        servicesAndVersionsCompatibility.closeMessageBanner();
//        LOGGER.info("Clicked on close banner");

        String cloudProductName = "Google Dataproc";
        String scheduleName = "Serv_Com_An_Test1_Google";
        String schedule_to_Run = "Daily";
        String scheduleTime = "10:30";
        String notification = "bkumar@unraveldata.com";

        //Set all properties in schedule report
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.scheduleBtn, UserAction.CLICK);
        LOGGER.info("Clicked on Schedule button.");
        servicesAndVersionsCompatibility.setScheduleCloudName(cloudProductName);
        servicesAndVersionsCompatibility.setScheduleName(scheduleName);
        servicesAndVersionsCompatibility.setScheduleToRun(schedule_to_Run);
        servicesAndVersionsCompatibility.setScheduleTime(scheduleTime);
        servicesAndVersionsCompatibility.setScheduleNotification(notification);
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleRunBtn);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.scheduleRunBtn,
                UserAction.CLICK);
        Assert.assertTrue(servicesAndVersionsCompatibilityPageObject.scheduleMsg.isDisplayed(),"Not able to " +
                "schedule report");
        test.log(LogStatus.PASS, "Verified schedule report on daily basis is scheduled for Google Dataproc.");

    }
}
