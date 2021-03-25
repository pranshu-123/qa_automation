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
public class TC_FR_36 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateMediumFileSearchOption(String clusterId) {
    test = extent.startTest("validateLargeFileSearchOption " + clusterId,
        "Validate user is able search a particular file path 'Medium' file report");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyFilePathSearchOption("MEDIUM", clusterId);
    test.log(LogStatus.PASS, "Successfully validate the sort based on number of 'Files' column for Medium file reports.");
  }
}
