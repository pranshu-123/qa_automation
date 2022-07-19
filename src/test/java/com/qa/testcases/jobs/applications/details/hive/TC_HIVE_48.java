package com.qa.testcases.jobs.applications.details.hive;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsHive
@Marker.EMRHive
@Marker.All
public class TC_HIVE_48 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_HIVE_48.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyGearIcon(String clusterId) {
        test = extent.startTest("TC_HIVE_48.VerifyGearIcon", "Verify that on setting icon check-uncheck works");
        test.assignCategory("App Details - Hive");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        AllApps allApps = new AllApps(driver);
        DatePicker datePicker = new DatePicker(driver);
        UserActions userActions = new UserActions(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userActions.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);
        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        if (applicationsPageObject.isTableExist.isDisplayed()) {
            applicationsPageObject.settings.click();
            waitExecuter.sleep(1000);
            List<WebElement> listOfItems = applicationsPageObject.settingsList;
            for (int i = 1; i < listOfItems.size(); i++) {
                listOfItems.get(i).click();
            }
            waitExecuter.sleep(1000);
            List<WebElement> headers = applicationsPageObject.getTableHeaders;
            Assert.assertTrue(headers.size() == 1,
                    "Header should contain only 'Type' column but it contains more than that");
            test.log(LogStatus.PASS, "Verified sorting on Parent App.");
            allApps.reset();
        } else {
            test.log(LogStatus.SKIP, "There are no apps for selected duration and clusterId.");
            allApps.reset();
        }

    }
}
