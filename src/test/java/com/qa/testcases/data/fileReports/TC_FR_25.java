package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
@Marker.DataFileReports
@Marker.All
public class TC_FR_25 extends BaseClass {
    /**
     Validate the user is able sort based on "Avg File Size" column for Empty file reports
     */
    @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to sort based on Avg File Size column for Empty file reports")
    public void validateAvgFileSizeForEmptyFiles(String clusterId) {
        test = extent.startTest("TC_FR_25.validateAvgFileSizeForEmptyFiles " + clusterId,
                "P1-Validate that the user is able to sort based on Avg File Size column for Empty file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.emptyFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.emptyFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("EMPTY", clusterId,3,3);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Avg File Size' column for empty file reports.");

    }
}

