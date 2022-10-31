package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_68 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_68.class);

    @Test(dataProvider = "clusterid-data-provider", description = "Verify the Partition Detail of Table Details Page")
    public void TC_DBX_DT_68_verifyPartitionDetailSection(String clusterId) throws InterruptedException {
        test = extent.startTest("TC_DBX_DT_68.verifyPartitionDetailSection", "Verify the Partition Detail of Table Details Page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);

            dataTablesHelper.clickOnDataTab();
            dataTablesHelper.clickOnDataTablesTab();
            AllApps allApps = new AllApps(driver);
            WaitExecuter waitExecuter = new WaitExecuter(driver);
            allApps.selectWorkSpaceId(clusterId);
            waitExecuter.waitForSeconds(2);
            dataPageObject.sortByDurationApp.click();
            waitExecuter.waitUntilElementPresent(dataPageObject.sortDown);
            dataPageObject.sortDown.click();
            waitExecuter.waitUntilPageFullyLoaded();
            dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
            dataTablesHelper.clickOnTabOnTableDetails("partition detail");
            if (dataPageObject.tableRows.size() > 0) {
                int expectedPartitionsCount =
                        Integer.parseInt(dataPageObject.totalPartitionsLabel.getText().trim().split(":")[0].trim());
                Assert.assertEquals(dataPageObject.tableRows.size(), expectedPartitionsCount, "Mismatch in partitions " +
                        "count");
                loggingUtils.pass("Correct Partitions count is displayed.", test);

                List<String> headings =
                        dataPageObject.tableHeadings.stream().map(heading -> heading.getText()).collect(Collectors.toList());
                Assert.assertEquals(headings.get(0), "Partition Name", "Incorrect column name is displayed");
                Assert.assertEquals(headings.get(1), "Created", "Incorrect column name is displayed");
                loggingUtils.pass("Correct columns are displayed.", test);

            } else {
                loggingUtils.info("No partitions value is displayed", test);
            }
        } catch (NoSuchElementException e) {
            dataTablesHelper.backToTablesPage();
            loggingUtils.error("Exception occured " + e.getStackTrace(), test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
