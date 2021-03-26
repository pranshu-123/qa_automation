package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_01 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void validateDefaultLargeFileSelected(String clusterId) {
    test = extent.startTest("TC_FR_01.validateDefaultLargeFileSelected " + clusterId,
        "Validate the file reports page displays 'Large' file reports by default");
    test.assignCategory(" Data - FileReports ");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    TopPanelPageObject topPanelObj = new TopPanelPageObject(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);
    ApplicationsPageObject appPageObj = new ApplicationsPageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.jobs);
    waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
    waitExecuter.waitUntilPageFullyLoaded();

    MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
    test.log(LogStatus.INFO, "Clicked on Data Tab");

    waitExecuter.waitUntilElementClickable(topPanelObj.fileReportsTab);
    MouseActions.clickOnElement(driver, topPanelObj.fileReportsTab);
    test.log(LogStatus.INFO, "Clicked on FileReports Tab");
    Assert.assertTrue(fileReportsPageObject.selectedLargeFileOpt.isDisplayed(), "Large file reports are not " +
        "displayed by default");
  }
}
