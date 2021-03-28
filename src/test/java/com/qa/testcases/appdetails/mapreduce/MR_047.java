package com.qa.testcases.appdetails.mapreduce;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.MrAppsDetailsPage;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.spark.TC_spark_165;
import com.qa.testcases.appdetails.spark.TC_spark_229;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsMr
@Marker.All
public class MR_047 extends BaseClass {

    Logger logger = Logger.getLogger(MR_047.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void MR_047_verifyMRJobsSummary(String clusterId) {
        test = extent.startTest("MR_047_verifyMRJobsSummary: " + clusterId,
                "Verify job summary must be defined based the actual state of the apps");
        test.assignCategory(" Apps Details-Mr");
        Log.startTestCase("MR_047_verifyMRJobsSummary");

        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        appsDetailsPage.commonSetupCodeForSumarryTabValidation(test, clusterId, "Analysis", logger, false);
        test.log(LogStatus.PASS, "Verified the Analysis tab successfully");
        }

    }