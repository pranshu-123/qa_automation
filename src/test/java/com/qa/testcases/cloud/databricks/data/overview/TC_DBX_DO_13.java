package com.qa.testcases.cloud.databricks.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.cloud.databricks.DataOverviewHelper;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
public class TC_DBX_DO_13 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Validate whether user is able to change the setting of label by making changes in Last App Access")
    public void verifyTableStatesByChangingLastAppAccess() {
        test = extent.startTest("TC_DBX_DO_12.verifyTableStatesByChangingLastAppAccess", "Validate whether user is able" +
                " to change the setting of label by making changes in Last App Access");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataOverviewHelper dataOverviewHelper = new DataOverviewHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        Boolean changeAppSetting = dataOverviewHelper.changeAppliedStateSettings("last app access");
        Assert.assertTrue(changeAppSetting, "User is not able to change table state setting by changing \"Last App Access\"");
        loggingUtils.pass("User is able to change setting of table state by changing \"Last App Access\"", test);
    }
}
