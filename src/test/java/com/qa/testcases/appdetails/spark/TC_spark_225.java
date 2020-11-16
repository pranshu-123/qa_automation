package com.qa.testcases.appdetails.spark;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TC_spark_225 extends BaseClass {
  /**
   * Verify the following for a Spark App:
   * 1. Stage for the Job should be displayed
   * 2. Stage must contain these fields ,
   * Stage ID, Start Time, Duration, tasks, shuffle read, Shuffle write, Input, Output
   * 3. Once clicked on Stage ID, Taskattempts, program, timeline, timing will open on right pane of the UI
   * 4. Task attempt should be doughnut graph
   * 5. Program will have the program and should be able to copy by clicking on the copy code
   * 6. Bargraphs should open for these - Shuffle Map Time(sec), ShuffleMap Input (KB), ShuffleMap Output (KB),
   * Disk Bytes Spilled (KB), Memory Bytes Spilled (KB), Records Read (count)
   * 7. Timeline and SelectedTasks must be present
   * 8. Time line should contain taskstatus and TaskBreakdown in dropdown
   * 9. Verify if "Stage timing Distribution " is available
   * 10. Pie graphs should be available
   * 11. IO. metrics must be available - Bar graphs
   * 12. Time Metrics must be available - Bargraphs
   */
  Logger logger = LoggerFactory.getLogger(TC_spark_225.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_spark_225_verifyAppDetailsPageStageTabs(String clusterId) {
    test = extent.startTest("TC_spark_225_verifyAppDetailsPageStageTabs: " + clusterId,
        "Verify 1. Stage for the Job should be displayed\n" +
            " 2. Stage must contain these fields ,\n" +
            "   Stage ID, Start Time, Duration, tasks, shuffle read, Shuffle write, Input, Output\n" +
            " 3. Once clicked on Stage ID, Taskattempts, program, timeline, timing will open on right pane of the UI\n" +
            " 4. Task attempt should be doughnut graph\n" +
            " 5. Program will have the program and should be able to copy by clicking on the copy code\n" +
            " 6. Bargraphs should open for these - Shuffle Map Time(sec), ShuffleMap Input (KB), ShuffleMap Output (KB),\n" +
            "   Disk Bytes Spilled (KB), Memory Bytes Spilled (KB), Records Read (count)\n" +
            "  7. Timeline and SelectedTasks must be present\n" +
            "  8. Time line should contain taskstatus and TaskBreakdown in dropdown\n" +
            "  9. Verify if \"Stage timing Distribution \" is available\n" +
            "  10. Pie graphs should be available\n" +
            "  11. IO. metrics must be available - Bar graphs\n" +
            "  12. Time Metrics must be available - Bargraphs");
    test.assignCategory(" Apps Details-Spark");
    Log.startTestCase("TC_spark_225_verifyAppDetailsPageStageTabs");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    logger.info("Initialize all class objects");
    TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
    ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
    SparkAppsDetailsPageObject sparkAppsDetailsPageObject = new SparkAppsDetailsPageObject(driver);
    SparkAppsDetailsPage appsDetailsPage = new SparkAppsDetailsPage(driver);
    DatePicker datePicker = new DatePicker(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    AllApps allApps = new AllApps(driver);

    // Navigate to Jobs tab from header
    test.log(LogStatus.INFO, "Navigate to jobs tab from header");
    appsDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
        applicationsPageObject, clusterId);

    //Verify that the left pane has spark check box and the apps number
    test.log(LogStatus.INFO, "Verify that the left pane has spark check box and the apps number");
    logger.info("Select individual app and assert that table contain its data");
    appsDetailsPage.clickOnlyLink("Spark");
    applicationsPageObject.expandStatus.click();
    int appCount = appsDetailsPage.clickOnlyLink("Success");

    //Clicking on the Spark app must go to apps detail page
    if (appCount > 0) {
      String headerAppId = appsDetailsPage.verifyAppId(sparkAppsDetailsPageObject, applicationsPageObject);
      test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);

      /**clicking on the UI must go to apps detail page and verify the basic tabs present */
      test.log(LogStatus.INFO, "Verify that the job stages tabs are displayed and that each one of them" +
          " have the required data in it");
      String tagValue = appsDetailsPage.verifyAppSummaryTabs(sparkAppsDetailsPageObject, "Tags", test);
      if (!tagValue.equals("spark-streaming"))
        appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, false, true);
      else
      {
        sparkAppsDetailsPageObject.closeAppsPageTab.click();
        waitExecuter.sleep(1000);
        applicationsPageObject.getAnotherAppFromTable.click();
        waitExecuter.sleep(2000);
        appsDetailsPage.verifyAppsComponent(sparkAppsDetailsPageObject, false, false, true);
      }
      test.log(LogStatus.PASS, "The job stage table has jobs and corresponding details displayed per " +
          "job id along with tabs and its respective data");
      //Close apps details page
      MouseActions.clickOnElement(driver, sparkAppsDetailsPageObject.closeAppsPageTab);
    } else {
      test.log(LogStatus.SKIP, "No Spark Application present");
      logger.error("No Spark Application present in the " + clusterId + " cluster for the time span " +
          "of 90 days");
    }
  }
}

