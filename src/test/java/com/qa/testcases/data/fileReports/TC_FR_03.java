package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.scripts.jobs.applications.InefficientApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.List;

@Marker.DataFileReports
@Marker.All
public class TC_FR_03 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider",description = "P0-Validate the user can list all the clusters listed on the Medium Files is selected")
  public void validateClusterListForMediumFiles(String clusterId) {
    test = extent.startTest("TC_FR_03.validateClusterListForMediumFiles " + clusterId,
        "Validate the user is able to list all the clusters listed for the cluster environment when" +
            " Medium Files is selcted");
    test.assignCategory(" Data - FileReports ");

    WaitExecuter waitExecuter = new WaitExecuter(driver);
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    TopPanelPageObject topPanelObj = new TopPanelPageObject(driver);
    FileReports filereports = new FileReports(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);
    ApplicationsPageObject appPageObj = new ApplicationsPageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.jobs);
    InefficientApps inefficientApps = new InefficientApps(driver);
    waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
    waitExecuter.waitUntilPageFullyLoaded();

    List<String> expectedClusterList = inefficientApps.getAllClusterIds();

    MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
    test.log(LogStatus.INFO, "Clicked on Data Tab");

    waitExecuter.waitUntilElementClickable(topPanelObj.fileReportsTab);
    MouseActions.clickOnElement(driver, topPanelObj.fileReportsTab);
    test.log(LogStatus.INFO, "Clicked on FileReports Tab");
    MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
    filereports.verifyClusterList(expectedClusterList);
    test.log(LogStatus.PASS, "Successfully validate the Medium Files is selected.");
  }
}