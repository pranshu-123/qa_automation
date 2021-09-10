package com.qa.testcases.jobs.applications.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.InefficientApps
@Marker.All
public class TC_JIA05 extends BaseClass {

    /**
     * Verify cluster filter in UI
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_JIA05_verifyClusterFilter(String clusterId) {
        test = extent.startTest("TC_JIA05_verifyClusterFilter" + clusterId, "Verify cluster filter" +
                " in UI");
        test.assignCategory(" Jobs / InEfficient Apps");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.INFO, "Passed Parameter Is : " + clusterId);

        //Click on Jobs tab
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        UserActions actions = new UserActions(driver);
        waitExecuter.sleep(2000);
        actions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);
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
