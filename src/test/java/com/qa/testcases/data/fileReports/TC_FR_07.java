package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_07 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateMediumFileForSelectedCluster(String clusterId) {
    test = extent.startTest("validateMediumFileForSelectedCluster " + clusterId,
        "Validate the UI displays Medium file reports for a selected cluster");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyFilesForSelectedCluster(clusterId, "MEDIUM");
    test.log(LogStatus.PASS, "Successfully validate the UI displays Medium file reports for a selected cluster.");
  }
}
