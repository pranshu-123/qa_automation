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
public class TC_FR_09 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider",description ="P0-Validate the UI displays Empty file reports for a selected cluster")
  public void validateEmptyFileForSelectedCluster(String clusterId) {
    test = extent.startTest("TC_FR_09.validateEmptyFileForSelectedCluster " + clusterId,
        "Validate the UI displays Empty file reports for a selected cluster");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.emptyFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.emptyFile);
    filereports.verifyFilesForSelectedCluster(clusterId, "EMPTY");
    test.log(LogStatus.PASS, "Successfully validate the Empty file reports for a selected cluster.");
  }
}
