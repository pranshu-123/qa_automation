package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_CMP_07 extends BaseClass {
  private static final Logger LOGGER = Logger.getLogger(com.qa.testcases.migration.cloud_mapping_per_host.TC_CMP_07.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyCostChangeForDiffRegion(String clusterId) {
    test = extent.startTest("verifyCostChangeForDiffRegion: " + clusterId,
        "Verify the cost/hour changes according to different regions.");
    test.assignCategory(" Migration/Cloud Mappig Per Host ");
    Log.startTestCase("verifyCostChangeForDiffRegion");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    SubTopPanelModulePageObject subPanelPageObj = new SubTopPanelModulePageObject(driver);
    CloudMigrationPerHostPage cloudMappingPage = new CloudMigrationPerHostPage(driver);
    CloudMappingPerHostPageObject cmPageObj = new CloudMappingPerHostPageObject(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    // Navigate to Reports tab from header
    MouseActions.clickOnElement(driver, subPanelPageObj.migration);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.waitUntilElementClickable(cmPageObj.cloudMappingPerHostTab);
    MouseActions.clickOnElement(driver, cmPageObj.cloudMappingPerHostTab);
    waitExecuter.waitUntilElementClickable(cmPageObj.runButton);
    waitExecuter.waitUntilPageFullyLoaded();

    cloudMappingPage.verifyCostChangeForDiffRegions("Amazon EMR", "Asia Pacific (Mumbai)",
        "South America (Sao Paulo)");
    test.log(LogStatus.PASS, "Validated the cost/hour changes according to different regions successfully ");
  }
}
