package com.qa.testcases.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.DataTables;
import com.qa.testcases.cloud.databricks.data.tables.TC_DBX_DT_1;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;
@Marker.DataTables
public class TC_DT_3 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_1.class);

    @Test(description = "Validate the metastore on data page")
    public void verifyMetaStoreOnDataPage() {
        test = extent.startTest("verifyMetaStoreOnDataPage", "Validate the metastore on data page");
        test.assignCategory("Data/Tables");
        DataTables dataTables = new DataTables(driver, test);
        dataTables.clickOnDataTab("Tables");
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTables.clickOnDataTablesTab();
        dataTables.selectWorkspaceForConfiguredMetastore();
    }
}

