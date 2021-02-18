package com.qa.testcases.migration.cloud_mapping_per_host;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.scripts.migration.CloudMigrationPerHostPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.CloudMappingPerHost
@Marker.All
public class TC_CMP_82 extends BaseClass {
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_CMP_82.class);
    /**
     * Verify for all the fields (CPU, Memory, Disk Space, Price)
     * is populated for all VM instances of each Cloud Provider/Regions
     */
    @Test
    public void verifyDetailsDisplayedForInstance() {
        test = extent.startTest("TC_CMP_82.verifyDetailsDisplayedForInstance", "Verify for all the fields " +
            "(CPU, Memory, Disk Space, Price) is populated for all VM instances of each Cloud Provider/Regions ");
        test.assignCategory("Migration/Cloud Mapping Per Host");

        CloudMigrationPerHostPage cloudMigrationPerHostPage = new CloudMigrationPerHostPage(driver);
        UserActions actions = new UserActions(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        LOGGER.info("Navigate to migration host page", test);
        cloudMigrationPerHostPage.navigateToCloudMappingPerHost();

        LOGGER.info("Click on Run button", test);
            cloudMigrationPerHostPage.clickOnRunButton();
        cloudMigrationPerHostPage.waitTillLoaderPresent();
        Map<String, List> instanceDetails = cloudMigrationPerHostPage.getInstanceValuesFromModalTable(false);
        Iterator iterator= instanceDetails.values().iterator();
        while (iterator.hasNext()) {
            List dataWithoutValues = (List) ((List) iterator.next()).stream().filter(data -> data.toString().trim().equals(
                "")).collect(Collectors.toList());
            Assert.assertTrue(dataWithoutValues.size() == 0, "Some instance does not display the data");
            LOGGER.pass("Instances displayed the all the details", test);
        }
    }
}
