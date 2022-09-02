package com.qa.testcases.cloud.databricks.data.tables;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.scripts.cloud.databricks.DataTablesHelper;
import com.qa.utils.LoggingUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;

/**
 * @author Ankur Jaiswal
 */


@Marker.DbxDataTables
@Marker.GCPDataTables
public class TC_DBX_DT_31 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DBX_DT_31.class);

    @Test(description = "Verify the content of csv file")
    public void TC_DBX_DT_31_verifyDownloadedCSVFile() {
        test = extent.startTest("TC_DBX_DT_31.verifyDownloadedCSVFile", "Verify the content of csv file");
        test.assignCategory("Databricks - Data");
        DataTablesHelper dataTablesHelper = new DataTablesHelper(driver, test);
        dataTablesHelper.clickOnDataTab();
        dataTablesHelper.clickOnDataTablesTab();
        dataTablesHelper.selectWorkspaceForConfiguredMetastore();
        File downloadedFile = dataTablesHelper.downloadDataTable();
        try {
            FileReader fileReader = new FileReader(downloadedFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Read header of the file
            bufferedReader.readLine();
            DataPageObject dataPageObject = new DataPageObject(driver);
            String[] csvLine = bufferedReader.readLine().trim().split(",");
            for (int j=1; j<dataPageObject.tableHeadings.size()-2; j++) {
                String csvContentColumnValue = csvLine[j-1].replaceAll("[=\"]","");
                Assert.assertNotEquals(csvContentColumnValue, "", "Column is empty");
            }
            loggingUtils.pass("CSV file containing the values", test);
            // First line of CSV file is header, at max 1000 records should be there in CSV file
            Assert.assertTrue(bufferedReader.lines().count()<=1001, "CSV file is having" +
                "more than 1000 record");
            loggingUtils.pass("CSV file is having less than 1000 records.", test);
        } catch (FileNotFoundException exception) {
            loggingUtils.error("Downloaded file not found", test);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
