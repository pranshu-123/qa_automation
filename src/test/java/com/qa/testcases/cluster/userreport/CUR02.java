package com.qa.testcases.cluster.userreport;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.UserReportPageObject;
import com.qa.scripts.clusters.UserReport;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Sarbashree Ray
 */
public class CUR02 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(CUR02.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void CUR02_Verifyspecialcharacter(String clusterId) {
        test = extent.startTest("CUR02_Verifyspecialcharacter", "Verify in schedule name accepts special character and numerical value");
        test.assignCategory("Cluster - User Report");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        UserReportPageObject userReportPageObject = new UserReportPageObject(driver);

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.topXTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.topXTab);
        waitExecuter.sleep(3000);
        topPanelPageObject.topXTab.click();

        UserReport userReport = new UserReport(driver);
        waitExecuter.waitUntilElementPresent(userReportPageObject.scheduleuserreportButton);
        userReport.HeaderMessage();
        Log.info("User Report Header is displayed.");
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully verified open new schedule user report page.");
        waitExecuter.sleep(1000);
        Log.info("Closing About window");
        userReportPageObject.closeWindow.click();
        waitExecuter.sleep(1000);
        Log.info("Loging off the app");
        test.log(LogStatus.PASS, "Successfully completed new schedule user report page.");
        waitExecuter.sleep(2000);

    }

}
