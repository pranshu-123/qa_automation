package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.ActionPerformer;
import com.qa.utils.ImageUtils;
import com.qa.utils.LoggingUtils;
import com.qa.utils.ScreenshotHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Ankur Jaiswal
 */
@Marker.DBX.Data
public class TC_DBX_DT_56 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    @Test(description = "Verify the chart displayed on left side of application details page.")
    public void verifyLeftChartApplicationDetailPage() {
        test = extent.startTest("TC_DBX_DT_56.verifyLeftChartApplicationDetailPage",
            "Verify the chart displayed on left side of application details page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        dataTablesHelper.clickOnMoreInfoOfNthRow(0);
        DataPageObject dataPageObject = new DataPageObject(driver);
        ScreenshotHelper screenshotHelper = new ScreenshotHelper();
        ActionPerformer actionPerformer = new ActionPerformer(driver);
        try {
            dataTablesHelper.clickOnTabOnTableDetails("Applications");
            dataTablesHelper.selectAllApplicationsColumn();
            dataTablesHelper.clickOnParentAppOfNthRow(0);
            WebElement leftChartElement = dataPageObject.applicationDetailsPageCharts.get(0);
            File leftGraphImg = screenshotHelper.takeScreenshotOfElement(driver, leftChartElement, 0);
            Boolean ifContainsColor = ImageUtils.isImageContainsColor(leftGraphImg, new int[]{44, 199, 23});
            Assert.assertTrue(ifContainsColor, "Graph not loaded with data");
            loggingUtils.pass(String.format("Graph loaded: %s", ifContainsColor), test);
        } finally {
            dataTablesHelper.closeApplicationDetailsPage();
            dataTablesHelper.backToTablesPage();
        }
    }
}
