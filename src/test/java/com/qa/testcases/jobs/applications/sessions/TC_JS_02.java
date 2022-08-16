package com.qa.testcases.jobs.applications.sessions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.scripts.jobs.applications.SessionsApps;
import com.qa.testcases.jobs.pipelines.workflow.TC_PWF_05;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;
@Marker.JobsSessions
public class TC_JS_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JS_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAppsCount(String clusterId) {
        test = extent.startTest("TC_PWF_05.verifyAppsCount",
                "Verify Spark total count of heading");
        test.assignCategory("Sessions Page");
        LOGGER.info("Click on Jobs Sessions tab");
        test.log(LogStatus.INFO, "Initialize all classes");
        SessionsApps sessionsApps = new SessionsApps(driver, test);
        AllApps allApps = new AllApps(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.INFO, "Navigate to pipeline tab through Jobs page");
        sessionsApps.clickOnJobsSessionsTab();
        test.log(LogStatus.INFO, "Select clusterid : " + clusterId);
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);

        test.log(LogStatus.INFO, "Verify that the left pane has MapReduce check box and the apps number");
        int appCount = sessionsApps.clickOnlyLink("Spark");

        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        LOGGER.info("AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The MapReduce app count is not equal to " +
                "the total count of heading.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "The left pane has MapReduce check box and the app counts match to that " +
                "displayed in the header");

    }
}
