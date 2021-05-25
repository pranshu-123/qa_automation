package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_07 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider",description = "P0-Validate the user can list all the clusters listed for the cluster environment while selecting the 'Medium' Files")
  public void validateMediumFileForSelectedCluster(String clusterId) {
    test = extent.startTest("TC_FR_07.validateMediumFileForSelectedCluster " + clusterId,
        "Validate the UI displays Medium file reports for a selected cluster");
    test.assignCategory(" Data - FileReports ");

    FileReports filereports = new FileReports(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    UserActions userActions = new UserActions(driver);
    FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

    filereports.navidateTofileReportsTab();
    test.log(LogStatus.INFO, "Clicked on FileReports Tab");
    waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
    userActions.performActionWithPolling(fileReportsPageObject.mediumFile, UserAction.CLICK);
    filereports.verifyFilesForSelectedCluster(clusterId, "MEDIUM");
    waitExecuter.waitUntilPageFullyLoaded();
    test.log(LogStatus.PASS, "Successfully validate Medium file reports for a selected cluster.");
  }
}
