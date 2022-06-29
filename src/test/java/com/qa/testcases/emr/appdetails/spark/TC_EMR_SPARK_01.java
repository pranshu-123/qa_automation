package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.appDetailsPage.EmrSparkAppsDetailsPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EMRSpark
public class TC_EMR_SPARK_01 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_EMR_SPARK_01.class);

    @Test()
    public void validateAppListedUI() {
        test = extent.startTest("TC_EMR_SPARK_01.validateAppListedUI",
                "Verify EMR Spark app must be available in app list");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects",test);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPageObject sparkAppsDetailsPageObject = new EmrSparkAppsDetailsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        wait.waitUntilElementClickable(sparkAppsDetailsPageObject.statusHeader);
        sparkAppsDetailsPageObject.statusHeader.click();

        wait.waitUntilElementClickable(sparkAppsDetailsPageObject.statusHeader);
        appsDetailsPage.clickOnlyLink("Success");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");

        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        loggingUtils.info("AppCount is " + appCount + " total count is " + totalCount,test);
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of Emr SparkApp is not equal to " + "the total count of heading.");
        test.log(LogStatus.PASS,
                "The left pane has Emr spark check box and the app counts match to that " + "displayed in the header");
            if (appCount > 0) {
                String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject);
                test.log(LogStatus.PASS, "Emr Spark Application Id is displayed in the Header: " + headerAppId);
                //Close apps details page
                MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
                wait.sleep(3000);

            } else {
                test.log(LogStatus.SKIP, "No Application present ");
                loggingUtils.error("No Application present in the Runs page", test);
            }
    }
}

