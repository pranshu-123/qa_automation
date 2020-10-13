package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_JIA11 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA11.class);

    /**
     * Verify sorting option
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppEventSortOption(String clusterId) {
        test = extent.startTest("TC_JIA11.verifyAppEventSortOption" + clusterId, "Verify sorting option");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
        test.log(LogStatus.INFO, "ClusterId parameter passed is : " + clusterId);

        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Click on Job tab");

        //Click on inefficientApps tab
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Click on inefficient Apps tab");

        DatePicker datePicker = new DatePicker(driver);
        // Select last 7 days from date picker
        test.log(LogStatus.INFO, "Select last 7 days");
        LOGGER.info("Select last 7 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.sleep(2000);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);

        //Validate InefficientApps table is populated
        waitExecuter.sleep(2000);
        Assert.assertTrue(inefficientApps.verifyInefficientAppsTblPresent(), "InefficientApps table not found");
        test.log(LogStatus.INFO, "Verified InefficientApps table populated.");

        //Click on InefficientApps table headers to sort it
        waitExecuter.sleep(2000);
        inefficientApps.clickOnInefficientAppsTblColSort();
        test.log(LogStatus.PASS, "Verified InefficientApps table headers sorting option on all applicable tab.");

    }

}
