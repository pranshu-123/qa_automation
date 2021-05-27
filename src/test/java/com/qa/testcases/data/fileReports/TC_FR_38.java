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
public class TC_FR_38 extends BaseClass {
    /**
     Validate user is able to search a particular Total File size
     */
    @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to search based on Total File size column for Empty file reports")
    public void validateTotalFileSizeSearchOption(String clusterId) {
        test = extent.startTest("TC_FR_38.validateTotalFileSizeSearchOption " + clusterId,
                "P1-Validate that the user is able to search based on Total File size column for Empty file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.emptyFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.emptyFile);
        filereports.verifyAllFileSizePathSearchOption("EMPTY", clusterId,4,4);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate user is able to search Total File size.");
    }
}
