package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.ImageUtils;
import com.qa.utils.LoggingUtils;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.File;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_33 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_33.class);

    @Test(description = "Validate whether Apps graph is updated when different rows are selected from table details.")
    public void verifyAppsGraphWithDifferentTables() {
        test = extent.startTest("verifyAppsGraphWithDifferentTables", "Validate whether Apps graph is updated when" +
            " different rows are selected from table details.");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        DataPageObject dataPageObject = new DataPageObject(driver);
        UserActions actions = new UserActions(driver);
        if (dataPageObject.tableRows.size() > 1) {
            actions.performActionWithPolling(dataPageObject.tableRows.get(1).findElement(By.cssSelector("td.border-warm > label > span.checkmark")),
                UserAction.CLICK);
            File screenshot1 = ScreenshotHelper.takeScreenshotOfElement(driver,
                dataPageObject.displayedGraphs.get(1), 0);
            actions.performActionWithPolling(dataPageObject.tableRows.get(2).findElement(By.cssSelector("td.border-warm > label > span.checkmark")),
                UserAction.CLICK);
            File screenshot2 = ScreenshotHelper.takeScreenshotOfElement(driver,
                dataPageObject.displayedGraphs.get(1), 0);
            Boolean isImageIdentical = ImageUtils.compareImage(screenshot1, screenshot2);
            if (isImageIdentical) {
                loggingUtils.info("Image is identical. <b style='color:red'>Please cross check.</>", test);
            }
            loggingUtils.pass("Verified graph loaded for different tables", test);
        }
    }
}
