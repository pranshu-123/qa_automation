package com.qa.testcases.emr.jobs.inefficientapps;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrInefficientAppsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.scripts.emr.jobs.EmrInefficientApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
@Marker.EmrInefficientApps
public class TC_EMR_JIA05 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_EMR_JIA04.class);

    /**
     * Verify cluster filter in UI
     */
    @Test()
    public void TC_JIA05_verifyClusterFilter() {
        test = extent.startTest("TC_JIA05_verifyClusterFilter" , "Verify cluster filter" +
                " in UI");
        test.assignCategory(" Jobs / InEfficient Apps");
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        //Click on Jobs tab
        EmrSubTopPanelModulePageObject subTopPanelModulePageObject = new EmrSubTopPanelModulePageObject(driver);
        UserActions actions = new UserActions(driver);
        waitExecuter.sleep(2000);
        actions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);
        test.log(LogStatus.INFO, "Click on Job tab");

        //Click on inefficientApps tab
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrInefficientApps inefficientApps = new EmrInefficientApps(driver);
        EmrInefficientAppsPageObject inefficientAppPageObject = new EmrInefficientAppsPageObject(driver);
        inefficientApps.setupInefficientApps();
        test.log(LogStatus.INFO, "setup for InefficientApps done.");


        //click on cluster search field
        waitExecuter.sleep(2000);
        inefficientAppPageObject.clusterIdsearchfield.click();
        waitExecuter.sleep(2000);
        System.out.println("All clusterId size: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.INFO, "All clusterId count: "+applicationsPageObject.clusterIdsList.size());
        test.log(LogStatus.PASS,"Validated cluster filter in UI");

    }
}
