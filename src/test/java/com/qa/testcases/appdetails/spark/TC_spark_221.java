package com.qa.testcases.appdetails.spark;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TC_spark_221 extends BaseClass {
    /**
     * Verify the attempts and the KPIs for a Spark App:
     * 1. Application details page should be opened
     * 2. If there are failed attempts then, there should be attempts tab under which attempts attempts for "failed"
     *    and "success" must be displayed in the form of bar graph.
     * 3. Verify that the navigation and the gantt chart should show jobs run under the attempts
     */

    org.slf4j.Logger logger = LoggerFactory.getLogger(TC_spark_221.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_spark_221_verifyAttemptsAndComponents(String clusterId) {
        test = extent.startTest("TC_spark_221_verifyAttemptsAndComponents: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TC_spark_221_verifyAttemptsAndComponents");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        SparkAppsDetailsPageObject sparkPageObj = new SparkAppsDetailsPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has spark check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");
        int appCount = appsDetailsPage.clickOnlyLink("Spark");

        //Clicking on the Spark app must go to apps detail page
        if (appCount > 0) {
            appsDetailsPage.navigateToFailedAppsAppPage(applicationsPageObject, sparkPageObj, test, true);
        }
        else
        {
            logger.info("No Spark Application present in the "+ clusterId+ " cluster for the time span " +
                    "of 90 days");
        }
        //Close apps details page
        sparkPageObj.closeAppsPageTab.click();
    }

}
