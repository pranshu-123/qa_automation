package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_41 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(dataProvider = "clusterid-data-provider",description = "Verify whether user is able to choose the columns to be displayed in table")
    public void validateTableColumnSelection(String clusterId) {
        test = extent.startTest("TC_DBX_DT_41.validateTableColumnSelection",
            "Verify whether user is able to choose the columns to be displayed in table");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        UserActions actions = new UserActions(driver);
        DataPageObject dataPageObject = new DataPageObject(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        AllApps allApps = new AllApps(driver);
        allApps.selectWorkSpaceId(clusterId);
        /*dataTablesHelper.selectWorkspaceForConfiguredMetastore(clusterId);*/
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            actions.performActionWithPolling(dataPageObject.settingsIcon, UserAction.CLICK);
            List<WebElement> columnCheckboxes = dataPageObject.columnCheckboxes;
            actions.performActionWithPolling(columnCheckboxes.get(0).findElement(By.xpath("parent::label")),
                UserAction.CLICK);
            actions.performActionWithPolling(columnCheckboxes.get(1).findElement(By.xpath("parent::label")),
                UserAction.CLICK);
            actions.performActionWithPolling(columnCheckboxes.get(2).findElement(By.xpath("parent::label")),
                UserAction.CLICK);
            loggingUtils.info("Deselected Type, Status and User from column selection", test);
            List<String> columns =
                dataPageObject.tableHeadings.stream().map(heading -> heading.getText().trim()).collect(Collectors.toList());
            Assert.assertFalse(columns.contains("Type"), "Type Column present after deselecting from column selection.");
            loggingUtils.info("Verified type column present after selecting from column selection.", test);
            Assert.assertFalse(columns.contains("Status"), "Status Column present after deselecting from column selection.");
            loggingUtils.info("Verified status column present after selecting from column selection.", test);
            Assert.assertFalse(columns.contains("User"), "User Column present after deselecting from column selection.");
            loggingUtils.info("Verified User column present after selecting from column selection.", test);
            loggingUtils.pass("Verified Column selection", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
