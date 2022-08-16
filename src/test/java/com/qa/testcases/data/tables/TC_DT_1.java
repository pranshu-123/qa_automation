package com.qa.testcases.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.DataTablesPageObject;
import com.qa.scripts.data.DataTables;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DataTables
public class TC_DT_1 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DT_1.class);

    @Test(description = "Verify whether table tab is present")
    public void TC_DT_1_verifyTablesTab() {
        test = extent.startTest("TC_DT_1.verifyTablesTab", "Verify whether table tab is present");
        test.assignCategory("Data/Tables");
        DataTablesPageObject dataPageObject = new DataTablesPageObject(driver);
        DataTables dataTables = new DataTables(driver, test);
        dataTables.clickOnDataTab("Tables");
        loggingUtils.info("Click on Data Tab on top panel", test);
        Assert.assertTrue(dataTables.checkIfElementPresent(dataPageObject.dataTablesTab));
        loggingUtils.pass("Data Tables tab is present", test);
    }
}
