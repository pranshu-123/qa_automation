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
public class TC_FR_35 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to search based on Max File Size column for Large file reports")
  public void validateLargeFileSearchOption(String clusterId) {
    test = extent.startTest("TC_FR_35.validateLargeFileSearchOption " + clusterId,
        "P1-Validate that the user is able to search based on Max File Size column for Large file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent( fileReportsPageObject.mediumFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyAllFileSizePathSearchOption("MEDIUM", clusterId,6,6);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate search a particular file path 'Large' file report.");
  }
}
