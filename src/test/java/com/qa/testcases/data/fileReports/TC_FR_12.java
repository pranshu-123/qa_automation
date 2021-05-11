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
public class TC_FR_12 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateFileSortForLargeFiles(String clusterId) {
    test = extent.startTest("TC_FR_12.validateFileSortForLargeFiles " + clusterId,
        "Validate the user is able sort based on number of 'Files' column for Large file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.largeFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
    waitExecuter.waitUntilPageFullyLoaded();
    filereports.verifyFileSortOption("LARGE", clusterId,2,2);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate the 'Files' column for Large file reports.");
  }
}
