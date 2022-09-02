package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_36 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_36.class);

    @Test(description = "Verify the table information displayed on \"Table Details\" page.")
    public void verifyInfoDisplayedOnTableDetails() {
        test = extent.startTest("TC_DBX_DT_36.verifyInfoDisplayedOnTableDetails", "Verify the table information displayed on \"Table Details\" page");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        DataPageObject dataPageObject = new DataPageObject(driver);
        try {
            dataTablesHelper.clickOnDataTab();
            dataTablesHelper.clickOnDataTablesTab();
            dataTablesHelper.selectWorkspaceForConfiguredMetastore();
            List<String> getRowValues = dataTablesHelper.getCellValuesOfNthRow(0);
            dataTablesHelper.clickOnMoreInfoOfNthRow(0);
            String actualDatabaseValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(0).getText().split(":")[1].trim();
            String actualTableValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(1).getText().split(":")[1].trim();
            String actualCreatedValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(2).getText().trim();
            String actualLatestAccessValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(3).getText().trim();
            String actualFilePathValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(4).getText().trim();
            String actualUserValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(5).getText().trim();
            String actualFileSystemValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(6).getText().split(":")[1].trim();
            String actualStorageFormatValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(7).getText().split(":")[1].trim();
            String actualTableTypeValue = dataPageObject.generalKPIsStorageKPIsValues
                .get(8).getText().split(":")[1].trim();
            Assert.assertEquals(actualDatabaseValue, getRowValues.get(1));
            Assert.assertEquals(actualTableValue, getRowValues.get(2));
            Assert.assertEquals(actualUserValue, getRowValues.get(3));
            if (getRowValues.get(4).contains("...")) {
                Assert.assertTrue(actualFilePathValue.contains(getRowValues.get(4).substring(0,
                    getRowValues.get(4).indexOf(".")-1)));
            } else {
                Assert.assertEquals(actualFilePathValue, getRowValues.get(4));
            }
            Assert.assertEquals(actualTableTypeValue, getRowValues.get(5));
            Assert.assertEquals(actualFileSystemValue, getRowValues.get(6));
            Assert.assertEquals(actualStorageFormatValue, getRowValues.get(7));
            Assert.assertEquals(actualCreatedValue,
                getRowValues.get(8).split("\n")[1] + " " + getRowValues.get(8).split("\n")[0]);
            Assert.assertEquals(actualLatestAccessValue,
                getRowValues.get(9).split("\n")[1] + " " + getRowValues.get(9).split("\n")[0]);

            List<WebElement> usersAppsSizePartitionsValues = dataPageObject.usersAppsSizePartitionsValues;
            String actualUsersCount = usersAppsSizePartitionsValues.get(0).getText();
            String actualAppsCount = usersAppsSizePartitionsValues.get(1).getText();
            String actualSize = usersAppsSizePartitionsValues.get(2).getText();
            String actualPartitionsCount = usersAppsSizePartitionsValues.get(3).getText();
            Assert.assertEquals(actualSize, getRowValues.get(10));
            Assert.assertEquals(actualAppsCount, getRowValues.get(11));
            Assert.assertEquals(actualPartitionsCount, getRowValues.get(12));
            Assert.assertEquals(actualUsersCount, getRowValues.get(13));

            loggingUtils.pass("Verified table info on table details page", test);
        } finally {
            dataTablesHelper.backToTablesPage();
        }
    }
}
