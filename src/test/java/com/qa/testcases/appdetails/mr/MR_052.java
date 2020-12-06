package com.qa.testcases.appdetails.mr;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class MR_052 extends BaseClass {

    java.util.logging.Logger logger = Logger.getLogger(MR_052.class.getName());

        @Test(dataProvider = "clusterid-data-provider")
        public void MR_052_verifyGraphsTabs(String clusterId) {
            test = extent.startTest("MR_052_verifyGraphsTabs: " + clusterId,
                    "Verify there are 2 tabs,Task Attempt (Map),Task Attempt(Reduce) and Graphs are doughnut charts and charts are available");
            test.assignCategory(" Apps Details-Mr");
            Log.startTestCase("MR_052_verifyGraphsTabs");

            MrAppsDetailsPage mrDetailsPage = new MrAppsDetailsPage(driver);
            mrDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Resources",logger, false);
            test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
    }
}
