package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;

import java.util.List;

public class TC_JIA06 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA06.class);

    /**
     * Verify Application with Inefficient events are listed for different clusters
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA06_verifyWithDifferentClusterFilter(String clusterId) {
        test = extent.startTest("TC_JIA06_verifyWithDifferentClusterFilter" + clusterId, "Verify Application " +
                "with Inefficient events are listed for different clusters");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        InefficientApps inefficientApps = new InefficientApps(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

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
        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days");
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);

        //Get all the cluster Ids
        List<String> listClusterIds = inefficientApps.getAllClusterIds();
        System.out.println("List all cluster Ids are: "+ listClusterIds);

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(1000);

        Assert.assertTrue(inefficientApps.verifyInefficientTbl(), "Inefficient Apps table is missing.");
        test.log(LogStatus.PASS, "Verified Application with Inefficient events are " +
                "listed for different clusters");
    }
}
