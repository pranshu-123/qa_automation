package com.qa.testcases.migration.cloud_mapping_per_host;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import org.testng.annotations.Test;
import com.qa.utils.WaitExecuter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_15 extends BaseClass{

    private static final LoggingUtils LOGGER = new LoggingUtils(TC_CMP_15.class);

    @Test
    public void verifyAzureCostEffectivenessWithObjectStorage() {

        test = extent.startTest("TC_CMP_15.verifyAzureCostEffectivenessWithObjectStorage", "Verify Unravel recommends the best AZURE instance based on max capacity for \"Lift and Shift\" and cluster node usage for \"cost reduction\" when 5 of the instances in Run/Schedule are selected. Make sure that Unravel recommends the most cost effective instance. " +
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

        LOGGER.info("Select AZURE as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select Object storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> costList = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.COST);

        List<String> costListWithoutDollar = new ArrayList<>();
        for(String s :costList){
            costListWithoutDollar.add(s.replace("$",""));
        }

        int  index =costListWithoutDollar.size() - 1;
        for (WebElement element : cloudMigrationPerHostPage.getCustomCosts(cloudMappingPerHostPageObject.modalTableRows)) {
            cloudMigrationPerHostPage.setCustomCost(element, costListWithoutDollar.get(index));
            index--;
        }
        index =0;

        cloudMigrationPerHostPage.clickOnModalRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        waitExecuter.sleep(10000);
        LOGGER.info("Click on Run button", test);

        cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select AZURE as cloud product.", test);
        cloudMigrationPerHostPage.selectCloudProduct(CloudProduct.AZURE);
        cloudMigrationPerHostPage.waitTillLoaderPresent();

        LOGGER.info("Select Object storage.", test);
        cloudMigrationPerHostPage.selectStorage("Object storage");
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        List<String> costList2 = cloudMigrationPerHostPage.getColumnValuesFromModalTable(MigrationCloudMappingModalTable.COST);

        List<String> costListWithoutDollar2 = new ArrayList<>();
        for(String s :costList2){
            costListWithoutDollar2.add(s.replace("$",""));
        }

        Collections.reverse(costListWithoutDollar2);
        Assert.assertEquals(costListWithoutDollar,costListWithoutDollar);
        test.log(LogStatus.PASS, "Validated Azure recommends the most effective instance with Object Storage selected successfully.");

    }
}
