package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_04.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningDatePickerList(String clusterId) {
        test = extent.startTest("TC_CTR_04.validateTuningDatePickerList: " + clusterId,
                "Verify cluster filter in UI");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();
        Tuning tuning = new Tuning(driver);

        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button");
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        LOGGER.info("Clicked on Tuning Tab + icon");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab + icon");

        UserActions userActions = new UserActions(driver);
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
        List<String> allClusters = null;
        int clusterCount = commonPageObject.clustersList.size();
        if (clusterCount > 0) {
            //Get all clusters from UI
            allClusters = tuning.getClusterOptions(commonPageObject);

            Assert.assertTrue(allClusters.size() > 0,
                    "Cluster list is empty");
            test.log(LogStatus.PASS, "Cluster list is not empty");

        } else {
            Assert.assertTrue(false, "Clusters not available.");
        }

    }

}
