package com.qa.testcases.cloud.dbx.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.dbx.DataPageObject;
import com.qa.scripts.cloud.dbx.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */

@Marker.DBX.Data
public class TC_DBX_DT_37 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_37.class);

    @Test(description = "Verify the Analysis section of Table Details page.")
    public void verifyAnalysisSection() {
        test = extent.startTest("verifyAnalysisSection", "Verify the Analysis section of Table Details page.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnDataTab();
            dataTablesHelper.clickOnDataTablesTab();
            dataTablesHelper.selectWorkspaceForConfiguredMetastore();
            dataTablesHelper.clickOnMoreInfoOfNthRow(0);
            dataTablesHelper.clickOnTabOnTableDetails("analysis");
            List<WebElement> recommendationAndInsights = dataPageObject.analysisRecommendationAndInsightsTitle;
            if (recommendationAndInsights.size() > 0) {
                recommendationAndInsights.stream().map(item -> item.getText()).forEach(item ->
                    loggingUtils.info("Analysis tab displays insights: " + item, test));
            } else {
                loggingUtils.info("No insights and recommendation displayed", test);
            }
            loggingUtils.pass("Verified Analysis tab", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
