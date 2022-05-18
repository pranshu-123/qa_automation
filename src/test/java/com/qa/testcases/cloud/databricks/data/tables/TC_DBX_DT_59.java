package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import com.qa.utils.actions.UserActions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
@Marker.DbxDataTables
public class TC_DBX_DT_59 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the Navigation tab of left of application detail page")
    public void verifyNavigationTab() {
        test = extent.startTest("TC_DBX_DT_59.verifyNavigationTab",
            "Verify the Navigation tab of left of application detail page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions actions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            actions.performActionWithPolling(dataPageObject.navigationTabApplicationDetailsPage, UserAction.CLICK);
            List<String> headings = dataPageObject.tableHeadings.stream().map(heading -> heading.getText().trim())
                .collect(Collectors.toList()).subList(0, dataPageObject.tableHeadings.size()-1);
            List<String> expectedHeadings = Arrays.asList("Type","Id","Start Time", "Duration", "I/O");
            Assert.assertEquals(headings, expectedHeadings, "Mismatch in table headings");
            loggingUtils.pass("Verified the table in navigation details.", test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}