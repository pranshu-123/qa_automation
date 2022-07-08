package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrInefficientAppsPageObject;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA15 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA15.class);

    /**
     * Verify MapReduce Kpis
     */
    @Test()
    public void ValidateMRKpis() {
        test = extent.startTest("TC_EMR_JIA15.ValidateMRKpis", "All the KPIs should be listed and the data must be populated");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientAppsPageObject emrEfficientApps = new EmrInefficientAppsPageObject(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");
        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");


        inefficientApps.clickOnlyLink("MapReduce");
        waitExecuter.waitUntilPageFullyLoaded();
        int appMRCount = inefficientApps.clickOnlyLink("MRMapTimeSkewEvent");
        test.log(LogStatus.INFO, "selected one application each from Application Type and Application Event");
        int totalCount = Integer
                .parseInt(emrEfficientApps.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appMRCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);

        if (appMRCount > 0) {
            String headerAppId = inefficientApps.verifyAppId(emrEfficientApps);
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Hive Application Id is displayed in the Header: " + headerAppId);
            inefficientApps.verifyRightPaneKpis(emrEfficientApps);
            test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
            //Close apps details page
            MouseActions.clickOnElement(driver, emrEfficientApps.closeAppsPageTab);

        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            Assert.assertTrue(emrEfficientApps.whenNoApplicationPresent.isDisplayed(),
                    "No Application present in the Application page'." +
                            "Manually check to see if the data on the Application page is present.");
        }

    }
}


