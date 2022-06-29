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
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.EMRSpark
public class TC_EMR_SPARK_06 extends BaseClass {
    private final LoggingUtils logger = new LoggingUtils(TC_EMR_SPARK_06.class);

    @Test()
    public void verifyAppDetailsPageCompKpis() {
        test = extent.startTest("TC_EMR_SPARK_06.verifyAppDetailsPageCompKpis",
                "Verify 1. Application details page should be opened\\n\" +\n" +
                        "                    \" 2. If there are failed attempts then, there should be attempts tab under which attempts attempts for \\\"failed\\\"\\n\" +\n" +
                        "                    \"  and success must be displayed in the form of bar graph." +
                        "                    \" 3. Verify that the navigation and the gantt chart should show jobs run under the attempts");
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
        try {
            String headerAppId = appsDetailsPage.verifyAppId(sparkPageObj);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            appsDetailsPage.navigateToFailedAppsAppPage(applicationsPageObject,sparkPageObj, test, false);
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with attempt data ");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name",test);
            logger.info("Error- " + ex,test);
        }
    }
}
