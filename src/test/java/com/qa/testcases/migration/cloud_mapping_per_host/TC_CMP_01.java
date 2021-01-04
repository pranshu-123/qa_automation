package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_CMP_01 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.migration.cloud_mapping_per_host.TC_CMP_01.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyCloudMappingPerHostReportGeneration(String clusterId) {
    test = extent.startTest("verifyCloudMappingPerHostReportGeneration: " + clusterId,
        "Verify the user is able to generate 'cloud mapping perhost' report to get information " +
            "on the instances used and their pricing");
    test.assignCategory(" Migration/Cloud Mappig Per Host ");
    Log.startTestCase("verifyCloudMappingPerHostReportGeneration");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    TopPanelPageObject topPanelPageObj = new TopPanelPageObject(driver);
    CloudMigrationPerHostPage cloudMappingPage = new CloudMigrationPerHostPage(driver);
    CloudMappingPerHostPageObject cmPageObj = new CloudMappingPerHostPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    MouseActions.clickOnElement(driver, topPanelPageObj.migrationTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.waitUntilElementClickable(cmPageObj.cloudMappingPerHostTab);
    MouseActions.clickOnElement(driver, cmPageObj.cloudMappingPerHostTab);
    waitExecuter.waitUntilElementClickable(cmPageObj.runButton);
    waitExecuter.waitUntilPageFullyLoaded();
    String[] expectedValList = {"Amazon EMR", "Asia Pacific (Mumbai)", "Local attached storage"};
    cloudMappingPage.verifyCloudMappingPerHostReports(expectedValList, "i3.16xlarge");
    test.log(LogStatus.PASS, "Validated that all the reports with report status are present ");
  }
}
