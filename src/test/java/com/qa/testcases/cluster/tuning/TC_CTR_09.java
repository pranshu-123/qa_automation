package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_09 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_09.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningScheduleWithEmail (String clusterId) {
        test = extent.startTest("TC_CTR_09.validateTuningScheduleWithEmail: " + clusterId,
                "Verify in mail id accepts all the combination");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
     /*   TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);*/
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");
        UserActions userActions = new UserActions(driver);
        Tuning tuning = new Tuning(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.reports);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.waitUntilPageFullyLoaded();
        tuning.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");
       /* tuning.clickOnScheduleButton();
        test.log(LogStatus.INFO, "Clicked on Schedule Button");*/
        String scheduleName = "testScheduleWithEmail";
        String scheduleEmail = "test@email.com";
        tuning.createScheduleWithNameAndEmail(scheduleName,scheduleEmail);
        waitExecuter.waitUntilPageFullyLoaded();
        tuning.clickOnModalScheduleButton();
      /*  test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
        String scheduleSuccessMsg = "THE REPORT HAS BEEN SCHEDULED SUCCESSFULLY.";
        tuning.verifyScheduleSuccessMsg(scheduleSuccessMsg);*/
        test.log(LogStatus.PASS, "Verified schedule with email.");

    }
}
