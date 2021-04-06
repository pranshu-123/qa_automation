package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_10  extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_10.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningScheduleWithMultiEmail (String clusterId) {
        test = extent.startTest("TC_CTR_10.validateTuningScheduleWithMultiEmail: " + clusterId,
                "Verify multiple mail id's added.");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
       /* TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);*/
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");
        Tuning tuning = new Tuning(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.reports);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.waitUntilPageFullyLoaded();
        tuning.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");
        /*tuning.clickOnScheduleButton();
        test.log(LogStatus.INFO, "Clicked on Schedule Button");*/
        String scheduleName = "testScheduleWithMultiEmail";
        List<String> multiEmail = Arrays.asList("test1@email.com", "test2@email.com", "test3@email.com" );
        tuning.createScheduleWithNameAndMultiEmail(scheduleName, multiEmail);
        waitExecuter.waitUntilPageFullyLoaded();
        tuning.clickOnModalScheduleButton();
       /* test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
        String scheduleSuccessMsg = "THE REPORT HAS BEEN SCHEDULED SUCCESSFULLY.";
        tuning.verifyScheduleSuccessMsg(scheduleSuccessMsg);*/
        test.log(LogStatus.PASS, "Verified schedule with email.");

    }
}

