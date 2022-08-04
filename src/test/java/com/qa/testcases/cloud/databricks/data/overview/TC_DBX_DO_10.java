package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Ankur Jaiswal
 */
@Marker.DbxDataOverview
@Marker.EmrDataOverview
@Marker.GCPDataOverview
public class TC_DBX_DO_10 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify the Table state graph")
    public void verifyTableStates(String clusterId) {
        test = extent.startTest("TC_DBX_DO_10.verifyTableStates", "Verify the Table state graph");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        AllApps allApps = new AllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        dataTablesHelper.clickOnDataTab();
        allApps.selectWorkSpaceId(clusterId);
        waitExecuter.sleep(3000);
        Boolean isLabelPresent = false;
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver,test);
        Map<String, String> tableStatePieChartValues = dataOverviewHelper.getTableStatePieChartValues();
        Set<Map.Entry<String, String>> tableStateMapEntrySet = tableStatePieChartValues.entrySet();
        Iterator itr = tableStateMapEntrySet.iterator();
        while (itr.hasNext()) {
            isLabelPresent = true;
            Map.Entry<String,String> mapEntry = (Map.Entry<String, String>) itr.next();
            loggingUtils.info(String.format("Label %s value: %s", mapEntry.getKey(), mapEntry.getValue()), test);
        }
        Assert.assertTrue(isLabelPresent, "Label is not present for pie chart");
        loggingUtils.pass("Pie charts loaded", test);
    }
}
