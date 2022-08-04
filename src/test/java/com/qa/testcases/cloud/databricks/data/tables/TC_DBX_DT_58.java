package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.ActionPerformer;
import com.qa.utils.ImageUtils;
import com.qa.utils.LoggingUtils;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_58 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the chart by different selection of the dropdown values")
    public void verifyLeftChartOnDifferentOptionSelection() {
        test = extent.startTest("TC_DBX_DT_58.verifyLeftChartOnDifferentOptionSelection",
            "Verify the chart by different selection of the dropdown values.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        ActionPerformer actionPerformer = new ActionPerformer(driver);
        UserActions actions = new UserActions(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            // Select duration as option
            verifyleftGraph(dataPageObject);
            loggingUtils.pass("Verified graph with selecting duration as option", test);
            actionPerformer.moveToTheElement(dataPageObject.dropdownIconOnApplicationDetails);
            // Select I/O as option
            actions.performActionWithPolling(dataPageObject.leftGraphOptions.get(1), UserAction.CLICK);
            verifyleftGraph(dataPageObject);
            loggingUtils.pass("Verified graph with selecting I/O as option", test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }

    public void verifyleftGraph(DataPageObject dataPageObject) {
        ScreenshotHelper screenshotHelper = new ScreenshotHelper();
        WebElement leftChartElement = dataPageObject.applicationDetailsPageCharts.get(0);
        File leftGraphImg = screenshotHelper.takeScreenshotOfElement(driver, leftChartElement, 0);
        Boolean ifContainsColor = ImageUtils.isImageContainsColor(leftGraphImg, new int[]{44, 199, 23});
        Assert.assertTrue(ifContainsColor, "Graph not loaded with data");
        loggingUtils.pass(String.format("Graph loaded: %s", ifContainsColor), test);
    }
}
