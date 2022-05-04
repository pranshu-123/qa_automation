package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
@Marker.DbxDataTables
public class TC_DBX_DT_63 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify \"Resources\" tab on application details page")
    public void verifyResourcesTab() {
        test = extent.startTest("TC_DBX_DT_63.verifyResourcesTab",
            "Verify \"Resources\" tab on application details page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions userActions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            userActions.performActionWithPolling(dataPageObject.resourcesTabApplicationDetails, UserAction.CLICK);
            Assert.assertTrue(dataPageObject.applicationDetailsPageCharts.size() == 5, "Some graphs" +
                " are not loaded.");
            loggingUtils.info("Verified all graphs are loaded.", test);

            Assert.assertEquals(dataPageObject.applicationDetailsPageCharts.get(1).findElement(
                By.xpath("parent::div/../preceding-sibling::div/h4")).getText().trim(), "Task Attempt (MAP)",
                "Task Attempt (MAP) graph is not loaded");
            loggingUtils.pass("Task Attempt (MAP) graph is displayed.", test);
            Assert.assertEquals(dataPageObject.applicationDetailsPageCharts.get(2).findElement(
                By.xpath("parent::div/../preceding-sibling::div/h4")).getText().trim(), "Task Attempt (REDUCE)",
                "Task Attempt (REDUCE) graph is not loaded");
            loggingUtils.pass("Task Attempt (REDUCE) graph is displayed.", test);
            Assert.assertEquals(dataPageObject.applicationDetailsPageCharts.get(3).findElement(
                By.xpath("parent::div/../preceding-sibling::div/h4")).getText().trim(), "Attempts",
                "Attempts graph is not loaded");
            loggingUtils.pass("Attempts graph is displayed.", test);
            Assert.assertEquals(dataPageObject.applicationDetailsPageCharts.get(4).findElement(
                By.xpath("parent::div/../preceding-sibling::div/h4")).getText().trim(), "Task Attempts",
                "Task Attempts graph is not loaded");
            loggingUtils.pass("Task Attempts graph is displayed.", test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}
