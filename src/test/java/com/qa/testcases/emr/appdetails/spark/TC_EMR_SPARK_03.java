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
public class TC_EMR_SPARK_03 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_EMR_SPARK_03.class);

    @Test()
    public void validateKPI() {
        test = extent.startTest("TC_EMR_SPARK_03.validateAppListedUI",
                "Verify App should have KPI defined");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        loggingUtils.info("Initialize all class objects", test);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPageObject sparkPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject);
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        wait.waitUntilElementClickable(sparkPageObj.statusHeader);
        sparkPageObj.statusHeader.click();

        wait.waitUntilElementClickable(sparkPageObj.statusHeader);
        appsDetailsPage.clickOnlyLink("Success");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");
        wait.sleep(2000);

        int totalCount = Integer
                .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        loggingUtils.info("AppCount is " + appCount + " total count is " + totalCount, test);
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        test.log(LogStatus.PASS,
                "The left pane has spark check box and the app counts match to that " + "displayed in the header");
        if (appCount > 0) {
            if (appCount > 0) {
                String headerAppId = appsDetailsPage.verifyAppId(sparkPageObj);
                test.log(LogStatus.PASS, "Emr Spark Application Id is displayed in the Header: " + headerAppId);
                appsDetailsPage.verifyRightPaneKpis(sparkPageObj);
                test.log(LogStatus.PASS, "All the KPIs are listed and the data is populated");
                //Close apps details page
                MouseActions.clickOnElement(driver, applicationsPageObject.closeIcon);
            } else {
                //Close apps details page
                MouseActions.clickOnElement(driver, sparkPageObj.homeTab);
                test.log(LogStatus.SKIP, "No Spark Application present");
                loggingUtils.info("No Application present in the Spark Summary page",test);
            }
        }
    }
}
