package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.Log;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
public class TC_CMP_02 extends BaseClass {
  private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_02.class);

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyVMTypesForEMR(String clusterId) {
    test = extent.startTest("TC_CMP_02.verifyVMTypesForEMR", "Verify Unravel UI displays the right VM types for " +
            "AMAZON_EMR.");
    test.assignCategory("Migration/Cloud Mapping Per Host");
    CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
    LOGGER.info("Navigate to migration host page", test);
    cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
    LOGGER.info("Click on Run button", test);
    cloudMigrationPerHostPage.clickOnRunButton();
    cloudMigrationPerHostPage.waitTillLoaderPresent();
    LOGGER.info("Select azure as cloud product", test);
    cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AMAZON_EMR);
    cloudMigrationPerHostPage.waitTillLoaderPresent();
    cloudMigrationPerHostPage.verifyCloudMappingHostTableColumn(MigrationCloudMappingModalTable.VM_TYPE);
    LOGGER.pass("Verified VM types", test);
  }
}
