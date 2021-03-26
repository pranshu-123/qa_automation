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
public class TC_FR_08 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateTinyFileForSelectedCluster(String clusterId) {
    test = extent.startTest("TC_FR_08.validateTinyFileForSelectedCluster " + clusterId,
        "Validate the UI displays Tiny file reports for a selected cluster");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    MouseActions.clickOnElement(driver, fileReportsPageObject.tinyFile);
    filereports.verifyFilesForSelectedCluster(clusterId, "TINY");
    test.log(LogStatus.PASS, "Successfully validate the UI displays Tiny file reports for a selected cluster.");
  }
}
