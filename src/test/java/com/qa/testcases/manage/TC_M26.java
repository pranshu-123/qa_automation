package com.qa.testcases.manage;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;

public class TC_M26 extends BaseClass {
    LoggingUtils loggingUtils = new LoggingUtils(TC_M26.class);

    @Test()
    public void verifyDownloadedCSVFile() {
        test = extent.startTest("TC_M26.verifyDownloadedCSVFile", "Verify the content of csv file");
        test.assignCategory("Manage/Audit page");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");
        waitExecuter.sleep(2000);
        File downloadedFile = manage.downloadAuditTable();
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

