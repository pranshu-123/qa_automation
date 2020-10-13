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

public class TC_JIA07 extends BaseClass {

    Logger LOGGER = LoggerFactory.getLogger(TC_JIA07.class);

    /**
     * Verify RESET functionality
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyRESETfunctionality(String clusterId) {
        test = extent.startTest("TC_JIA07.verifyRESETfunctionality" + clusterId, "Verify RESET functionality");
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

        // Select cluster
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        inefficientApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        inefficientApps.clickOnApplicationEvent();
        test.log(LogStatus.INFO, "Clicked on Application Event link");

        inefficientApps.clickFirstChkBoxOfAppTypeAndAppEventEach();
        test.log(LogStatus.INFO, "Deselected one application each from Application Type and Application Event");

        // Click on reset button
        test.log(LogStatus.INFO, "Click on reset button");
        LOGGER.info("Click on reset button");
        applicationsPageObject.resetButton.click();
        waitExecuter.sleep(2000);
        test.log(LogStatus.INFO, "Clicked on Reset button");

        inefficientApps.clickOnApplicationEvent();
        test.log(LogStatus.INFO, "Clicked on Application Event link");

        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppTypeIsChecked(),"ApplicationType " +
                "checkbox is not checked after Reset functionality fails");
        Assert.assertTrue(inefficientApps.verifyFirstChkBoxOfAppEventIsChecked(),"ApplicationEvent " +
                "checkbox is not checked after Reset functionality fails");
        test.log(LogStatus.PASS, "Verified Reset functionality.");

    }
}
