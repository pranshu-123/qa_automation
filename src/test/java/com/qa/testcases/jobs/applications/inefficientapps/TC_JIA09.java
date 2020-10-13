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

public class TC_JIA09 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA09.class);

    /**
     * Verify application type filter
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppTypeFilter(String clusterId) {
        test = extent.startTest("TC_JIA09.verifyAppTypeFilter" + clusterId, "Verify application type filter");
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

        inefficientApps.clickAllApplicationTypeChkBox();
        test.log(LogStatus.INFO, "Deselected one application each from Application Type and Application Event");

        inefficientApps.clickShowAll();
        test.log(LogStatus.INFO, "Clicked on Show All");
        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppTypeIsChecked(),"ApplicationType " +
                "checkbox is not checked after click on Show All");
        test.log(LogStatus.PASS, "Verified application type filter and clicked on Show All");
    }
}
