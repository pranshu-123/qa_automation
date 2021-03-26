package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.clusters.Tuning;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_07 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_07.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningScheduleWithSpecialCharAndNum (String clusterId) {
        test = extent.startTest("TC_CTR_07.validateTuningScheduleWithSpecialCharAndNum: " + clusterId,
                "Verify schedule is working fine.");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");

        Tuning tuning = new Tuning(driver);
        tuning.clickOnScheduleButton();
        test.log(LogStatus.INFO, "Clicked on Schedule Button");

        String scheduleName = "!@#123";
        tuning.createScheduleWithName(scheduleName);
        tuning.clickOnModalScheduleButton();
        test.log(LogStatus.INFO, "Clicked on modal Schedule Button");
        String scheduleSuccessMsg = "THE REPORT HAS BEEN SCHEDULED SUCCESSFULLY.";
        tuning.verifyScheduleSuccessMsg(scheduleSuccessMsg);
        test.log(LogStatus.PASS, "Verified schedule success message");

    }
}
