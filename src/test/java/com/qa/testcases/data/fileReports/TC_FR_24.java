package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_24 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateFileSortForEmptyFiles(String clusterId) {
    test = extent.startTest("validateFileSortForEmptyFiles " + clusterId,
        "Validate the user is able sort based on number of 'Files' column for Empty file reports");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    MouseActions.clickOnElement(driver, fileReportsPageObject.emptyFile);
    filereports.verifyFileSortOption("EMPTY", clusterId);

  }
}
