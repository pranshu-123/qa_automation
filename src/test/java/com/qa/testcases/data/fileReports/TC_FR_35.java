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
    waitExecuter.waitUntilElementPresent( fileReportsPageObject.largeFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
    filereports.verifyAllFileSizePathSearchOption("MEDIUM", clusterId,2,2);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate search a particular file path 'Large' file report.");
  }
  @Test(dataProvider = "clusterid-data-provider", description = "P1-Validate that the user is able to search based on files column for Medium file reports")
  public void validateMediumFileSearchOption(String clusterId) {
    test = extent.startTest("TC_FR_36.validateLargeFileSearchOption " + clusterId,
            "P1-Validate that the user is able to search based on files column for Medium file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyAllFileSizePathSearchOption("MEDIUM", clusterId, 2, 2);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate the search a particular file path 'Medium' file report.");
  }

  @Test(dataProvider = "clusterid-data-provider", description = "P1-Validate that the user is able to search based on Avg File size column for Tiny file reports")
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
    filereports.verifyAllFileSizePathSearchOption("TINY", clusterId, 3, 3);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate user is able to search Avg File size.");
  }

  @Test(dataProvider = "clusterid-data-provider", description = "P1-Validate that the user is able to search based on Total File size column for Empty file reports")
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
    filereports.verifyAllFileSizePathSearchOption("EMPTY", clusterId, 4, 4);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate user is able to search Total File size.");
  }

  @Test(dataProvider = "clusterid-data-provider", description = "P1-Validate that the user is able to search based on Min File size column for Large file reports")
  public void validateMinFileSizeSearchOption(String clusterId) {
    test = extent.startTest("TC_FR_39.validateMinFileSizeSearchOption " + clusterId,
            "P1-Validate that the user is able to search based on Min File size column for Large file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.largeFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
    filereports.verifyAllFileSizePathSearchOption("LARGE", clusterId, 5, 5);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate user is able to search Min File size.");
  }

  @Test(dataProvider = "clusterid-data-provider", description = "P1-Validate that the user is able to search based on Max File size column for Medium file reports")
  public void validateMaxFileSizeSearchOption(String clusterId) {
    test = extent.startTest("TC_FR_40.validateMaxFileSizeSearchOption " + clusterId,
            "P1-Validate that the user is able to search based on Max File size column for Medium file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyAllFileSizePathSearchOption("MEDIUM", clusterId, 6, 6);
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate user is able to search Max File size.");
  }
}
