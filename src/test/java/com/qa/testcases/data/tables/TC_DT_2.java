package com.qa.testcases.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.data.DataTables;
import com.qa.utils.LoggingUtils;
import org.testng.annotations.Test;
@Marker.DataTables
public class TC_DT_2 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DT_2.class);

    @Test(description = "Verify workspace on data page")
    public void TC_DT_2_verifyWorkspace() {
        test = extent.startTest("TC_DT_2.verifyWorkspace", "Verify workspace on data page");
        test.assignCategory("Data/Tables");
        DataTables dataTables = new DataTables(driver, test);
        dataTables.clickOnDataTab("Data");
        loggingUtils.info("Click on Data Tab on top panel", test);
        dataTables.clickOnDataTab("Tables");
        dataTables.selectWorkspaceForConfiguredMetastore();
        loggingUtils.pass("Selected the workspace which has configured metastore", test);
    }
}
