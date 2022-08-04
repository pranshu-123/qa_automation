package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
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
public class TC_DBX_DT_40 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify whether user is able to sort the table columns of Application Table displayed on \"Table Details\" page.")
    public void validateSortFunctionalityOfApplicationsTable() {
        test = extent.startTest("TC_DBX_DT_40.validateSortFunctionalityOfApplicationsTable",
            "Verify whether user is able to sort the table columns of Application Table displayed on \"Table Details\" page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        UserActions actions = new UserActions(driver);
        DataPageObject dataPageObject = new DataPageObject(driver);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            List<WebElement> tableHeadings = dataPageObject.tableHeadings;
            actions.performActionWithPolling(tableHeadings.get(6), UserAction.CLICK);
            loggingUtils.info("Sort Start time in ascending order", test);
            List<String> unsortedStartTime = dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                .get(6).getText()).collect(Collectors.toList());
            List<String> sortedStartTime = dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                .get(6).getText()).sorted().collect(Collectors.toList());
            Assert.assertEquals(unsortedStartTime, sortedStartTime, "Verified whether data is sorted.");
            loggingUtils.pass("Verified whether data is sorted.", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
