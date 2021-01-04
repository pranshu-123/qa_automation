package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_35 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateLargeFileSearchOption(String clusterId) {
    test = extent.startTest("validateLargeFileSearchOption " + clusterId,
        "Validate user is able search a particular file path 'Large' file report");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
    filereports.verifyFilePathSearchOption("LARGE", clusterId);
  }
}