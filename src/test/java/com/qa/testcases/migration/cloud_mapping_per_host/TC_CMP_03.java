package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
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

@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_03 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.migration.cloud_mapping_per_host.TC_CMP_03.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyVMTypesForEC2(String clusterId) {
    test = extent.startTest("verifyVMTypesForEC2: " + clusterId,
        "Verify Unravel UI displays the right VM types for EC2.");
    test.assignCategory("Migration/Cloud Mapping Per Host");
    Log.startTestCase("verifyVMTypesForEC2");

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

    cloudMappingPage.verifyEMRVMTypes("Asia Pacific (Seoul)", "Amazon EC2 (IaaS)");
    test.log(LogStatus.PASS, "Validated Unravel UI displays the right VM types for EC2 successfully ");
  }
}
