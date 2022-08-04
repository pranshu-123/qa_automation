package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Ankur Jaiswal
 */

@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_61 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the Gantt Chart tab of left of application detail page")
    public void verifyGanttChart() {
        test = extent.startTest("TC_DBX_DT_61.verifyGanttChart",
            "Verify the Gantt Chart tab of left of application detail page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            String applicationId = dataPageObject.applicationId.getText();
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            Assert.assertTrue(dataPageObject.ganttChart.findElement(By.xpath("descendant::span")).getText().contains(
                applicationId), "Invalid application is displayed in gantt chart");
            loggingUtils.pass("Correct application Id is displayed in gantt chart", test);
            verifyLeftGraph(dataPageObject);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }

    public void verifyLeftGraph(DataPageObject dataPageObject) {
        ScreenshotHelper screenshotHelper = new ScreenshotHelper();
        WebElement ganttChart = dataPageObject.ganttChart;
        File leftGraphImg = screenshotHelper.takeScreenshotOfElement(driver, ganttChart, 0);
        Boolean ifContainsColor = ImageUtils.isImageContainsColor(leftGraphImg, new int[]{44, 199, 23});
        Assert.assertTrue(ifContainsColor, "Graph not loaded with data");
        loggingUtils.pass(String.format("Graph loaded: %s", ifContainsColor), test);
    }
}
