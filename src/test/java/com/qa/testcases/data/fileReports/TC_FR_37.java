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
public class TC_FR_37 extends BaseClass {
    /**
     Validate user is able to search a particular Avg File size
     */
    @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to search based on Avg File size column for Tiny file reports")
    public void validateAvgFileSizeFileSearchOption(String clusterId) {
        test = extent.startTest("TC_FR_37.validateAvgFileSizeFileSearchOption " + clusterId,
                "P1-Validate that the user is able to search based on Avg File size column for Tiny file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.tinyFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.tinyFile);
        filereports.verifyAllFileSizePathSearchOption("TINY", clusterId,3,3);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate user is able to search Avg File size.");
    }
}

