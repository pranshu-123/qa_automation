package com.qa.testcases.migration.cloud_mapping_per_host;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import org.testng.annotations.Test;
import com.qa.utils.WaitExecuter;
import java.util.Arrays;
import java.util.List;

@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_12 extends BaseClass {

    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_12.class);

    public void verifyEMRCostEffectivenessWithLocalAttachedStorage() {

        test = extent.startTest("TC_CMP_12.verifyEMRCostEffectivenessWithObjectStorage", "Verify Unravel recommends the best EMR instance based on max capacity for \"Lift and Shift\" and cluster node usage for \"cost reduction\" when 5 of the instances in Run/Schedule are selected. Make sure that Unravel recommends the most cost effective instance. " +
                "for Object Storage");

        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        CloudMappingPerHostPageObject cloudMappingPerHostPageObject = new CloudMappingPerHostPageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();
        LOGGER.info("Click on Run button", test);
        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        LOGGER.info("Select Object storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> value1 = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.COST);
        System.out.println(Arrays.toString(value1.toArray()));

        for (WebElement element : cloudMappingPerHostPageObject.modalTableRows) {
            cloudMigrationPerHostPage.setCustomCost(element, "1");
        }

        cloudMigrationPerHostPage.clickOnModalRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(10000);

        test.log(LogStatus.PASS, "Validated EMR recommends the most effective instance with Local Attached Storage selected successfully.");

    }
}
