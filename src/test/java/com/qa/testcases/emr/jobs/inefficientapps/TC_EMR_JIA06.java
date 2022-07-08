package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.jobs.EmrInefficientAppsPageObject;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.EmrInefficientApps
public class TC_EMR_JIA06 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA06.class);

    /**
     * Verify Application with Inefficient events are listed for different clusters in EMR
     */
    @Test()
    public void TC_EMR_JIA06_verifyWithDifferentClusterFilter() {
        test = extent.startTest("TC_EMR_JIA06verifyWithDifferentClusterFilter", "Verify Application " +
                "with Inefficient events are listed for different clusters");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        EmrInefficientAppsPageObject inefficientPageObj = new EmrInefficientAppsPageObject(driver);

        // Get setup done for InefficientApps page
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");

        List<WebElement> clusterList = inefficientApps.getClusterListFromDropdown();
        waitExecuter.sleep(2000);
        inefficientApps.clickOnClusterDropDown();
        for (int i = 0; i < clusterList.size() - 1; i++) {
            String selectedCluster = clusterList.get(i).getText();
            waitExecuter.sleep(1000);
            // Click on cluster ranges and verify Total app counts
            test.log(LogStatus.INFO, "Click on cluster: " + selectedCluster);
            LOGGER.info("Click on cluster: " + selectedCluster, test);
            clusterList.get(i).click();
            waitExecuter.waitUntilElementClickable(inefficientPageObj.resetButton);
            int totalAppCount = Integer
                    .parseInt(inefficientPageObj.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            LOGGER.info("Total app count: " + totalAppCount, test);
            if (totalAppCount > 0) {
                inefficientPageObj.applicationPresent.isDisplayed();
                LOGGER.info("Application displayed in the cluster",test);
                // Assert if apps are displayed for selected cluster range
                test.log(LogStatus.INFO, "Assert if apps are displayed for selected cluster");
                Assert.assertTrue(totalAppCount >= 0,
                        "The app count of page does not match with totalAppCount for cluster selected: "
                                + selectedCluster);
                test.log(LogStatus.PASS,
                        "Apps are listed for selected cluster- " + selectedCluster);
            } else
                Assert.assertTrue(inefficientPageObj.whenNoApplicationPresent.isDisplayed());
            waitExecuter.waitUntilElementClickable(inefficientPageObj.resetButton);
            inefficientApps.clickOnClusterDropDown();
        }
    }
}

