package com.qa.testcases.appdetails.spark;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.AppDetailsSpark
@Marker.All
public class TC_spark_165 extends BaseClass {
  /**
   * 1. Execute a spark application
   * 2. From Unravel UI open the spark application.
   * 3. Wait for the spark application to complete.
   * 4. Click on actions and verify if the user can execute the action "Load Diagnostics".
   */

  Logger logger = LoggerFactory.getLogger(TC_spark_165.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_spark_165_verifyLoadDiagnosticAction(String clusterId) {
    test = extent.startTest("TC_spark_165_verifyLoadDiagnosticAction: " + clusterId,
        "Verify  Clicking on actions the user can execute the action Load Diagnostics");
    test.assignCategory(" Apps Details-Spark");
    Log.startTestCase("TC_spark_165_verifyLoadDiagnosticAction");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
    SparkAppsDetailsPageObject sparkAppsDetailsPageObject = new SparkAppsDetailsPageObject(driver);
    SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
    DatePicker datePicker = new DatePicker(driver);
    AllApps allApps = new AllApps(driver);

    // Navigate to Jobs tab from header
    test.log(LogStatus.INFO, "Navigate to jobs tab from header");
    appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
        applicationsPageObject, clusterId);
    test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
    int appCount = appsDetailsPage.clickOnlyLink("Spark");
    int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
        replaceAll("[^\\dA-Za-z ]", "").trim());
    logger.info("AppCount is " + appCount + " total count is " + totalCount);
    Assert.assertEquals(appCount, totalCount, "The Spark app count of SparkApp is not equal to " +
        "the total count of heading.");
    test.log(LogStatus.PASS, "The left pane has spark check box and the app counts match to that " +
        "displayed in the header");

    //Clicking on the Spark app must go to apps detail page
    if (appCount > 0) {
      String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject, applicationsPageObject);
      test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
      test.log(LogStatus.INFO,"Verify if the user can execute the Load Diagnostics action");
      appsDetailsPage.verifyLoadDiagnosticAction(sparkAppsDetailsPageObject);
      test.log(LogStatus.PASS, "Verified that the user can execute the Load Diagnostics action");
      //Close apps details page
      MouseActions.clickOnElement(driver, sparkAppsDetailsPageObject.closeAppsPageTab);
    } else {
      test.log(LogStatus.SKIP, "No Spark Application present");
      logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
          "of 90 days");
    }
  }
}