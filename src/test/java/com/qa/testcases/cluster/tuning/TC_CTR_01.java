package com.qa.testcases.cluster.tuning;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.clusters.Tuning;
import com.qa.testcases.cluster.topx.TC_CTX_20;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/*
 * Verify cluster tuning report is generated for default filters
 */
public class TC_CTR_01 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTR_01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningReportGenerated(String clusterId) {
        test = extent.startTest("TC_CTR_01.validateTuningReportGenerated", "Verify cluster tuning report is generated for default filters");
        test.assignCategory(" Cluster - Tuning ");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);

        TuningPageObject tuningPageObject = new TuningPageObject(driver);

        Tuning tuning = new Tuning(driver);
        tuning.closeConfirmationMessageNotification();
        tuning.clickOnRunButton();
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        tuning.clickOnModalRunButton();
        waitExecuter.waitUntilElementPresent(tuningPageObject.runButton);
        waitExecuter.waitUntilElementClickable(tuningPageObject.runButton);

        try {
            waitExecuter.waitUntilTextToBeInWebElement(tuningPageObject.confirmationMessageElement,
                    "Tuning Report completed successfully.");
            test.log(LogStatus.PASS, "Verified Tuning report is loaded properly.");
        } catch (TimeoutException te) {
            throw new AssertionError("Tuning Report not completed successfully.");
        }
    }
}
