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
public class TC_FR_23 extends BaseClass {
    /**
     Validate the user is able sort the UI based on "Path" column for Empty file reports
     */
    @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to sort based on Path column for Empty file reports")
    public void validatePathSortForTinyFiles(String clusterId) {
        test = extent.startTest("TC_FR_23.validatePathSortForTinyFiles " + clusterId,
                "P1-Validate that the user is able to sort based on Path column for Empty file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.emptyFile);
        userActions.performActionWithPolling(fileReportsPageObject.emptyFile, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("EMPTY", clusterId,1,1);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Path' column for EMPTY file reports.");
    }
}

