package com.qa.testcases.emr.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.emr.appDetailsPage.EmrSparkAppsDetailsPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrSubTopPanelModulePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.emr.appdetails.EmrSparkAppsDetailsPage;
import com.qa.scripts.emr.jobs.EMRAllApps;
import com.qa.testcases.databricks.jobs.jobs.TC_DJ_01;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EMRSpark
public class TC_EMR_SPARK_05 extends BaseClass {
    private final LoggingUtils logger = new LoggingUtils(TC_DJ_01.class);

    @Test()
    public void validateAppSummaryPage() {
        test = extent.startTest("TC_EMR_SPARK_05.validateAppSummaryPage",
                "Verify 1. All the spark apps must be listed on the UI\n" +
                        " 2. left pane must have spark check box and the apps number\n" +
                        " 3. clicking on the UI must go to apps detail page");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects",test);
        EmrSubTopPanelModulePageObject topPanelComponentPageObject = new EmrSubTopPanelModulePageObject(driver);
        EmrApplicationsPageObject applicationsPageObject = new EmrApplicationsPageObject(driver);
        EmrSparkAppsDetailsPageObject sparkPageObj = new EmrSparkAppsDetailsPageObject(driver);
        EmrSparkAppsDetailsPage appsDetailsPage = new EmrSparkAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        EMRAllApps allApps = new EMRAllApps(driver);
        WaitExecuter wait = new WaitExecuter(driver);
        try {
            appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                    applicationsPageObject);

            wait.waitUntilElementClickable(sparkPageObj.statusHeader);
            sparkPageObj.statusHeader.click();

            wait.waitUntilElementClickable(sparkPageObj.statusHeader);
            appsDetailsPage.clickOnlyLink("Success");
            int appCount = appsDetailsPage.clickOnlyLink("Spark");
            wait.sleep(2000);

            int totalCount = Integer
                    .parseInt(applicationsPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
            logger.info("AppCount is " + appCount + " total count is " + totalCount, test);
            Assert.assertEquals(appCount, totalCount,
                    "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
            test.log(LogStatus.PASS,
                    "The left pane has spark check box and the app counts match to that " + "displayed in the header");

            if (appCount > 0) {
                String headerAppId = appsDetailsPage.verifyAppId(sparkPageObj);
                test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
                test.log(LogStatus.INFO, "Verify if the user can execute the Load Diagnostics action");
                appsDetailsPage.verifyAppsComponent(sparkPageObj, false, false,
                        false);
                test.log(LogStatus.PASS, "Verified that the user can execute the Load Diagnostics action");
                test.log(LogStatus.PASS, "The basic components for an application is present");
                //Close apps details page
                MouseActions.clickOnElement(driver, sparkPageObj.closeAppsPageTab);
            } else {
                test.log(LogStatus.SKIP, "No Spark Application present");
                logger.error("No Job Application present in the last 30 days",test);
            }
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name",test);
            logger.info("Error- " + ex,test);
        }
    }
}
