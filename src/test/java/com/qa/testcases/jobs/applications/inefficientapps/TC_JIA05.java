package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class TC_JIA05 extends BaseClass {

    /**
     * Verify cluster filter in UI
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA05_verifyClusterFilter(String clusterId) {
        test = extent.startTest("TC_JIA05_verifyClusterFilter" + clusterId, "Verify cluster filter" +
                " in UI");
        test.assignCategory("4620 Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.sleep(2000);
        topPanelComponentPageObject.jobs.click();
        test.log(LogStatus.INFO, "Click on Job tab");

        //Click on inefficientApps tab
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();
        test.log(LogStatus.INFO, "Click on inefficient Apps tab");

        //click on cluster search field
        ApplicationsPageObject applicationsPageObject1 = new ApplicationsPageObject(driver);
        waitExecuter.sleep(2000);
        applicationsPageObject.clusterIdsearchfield.click();
        waitExecuter.sleep(2000);
        System.out.println("All clusterId size: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.INFO, "All clusterId count: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.PASS,"Validated cluster filter in UI");

    }
}
