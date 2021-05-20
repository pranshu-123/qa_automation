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
public class TC_FR_19 extends BaseClass {
    /**
     Validate the user is able sort based on "Avg File Size" column for Tiny file reports
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateAvgFileSizeSortForTinyFiles(String clusterId) {
        test = extent.startTest("TC_FR_19.validateAvgFileSizeSortForTinyFiles " + clusterId,
                "P1-Validate the user is able sort based on number of 'Avg File Size' column for Tiny file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        UserActions userActions = new UserActions(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.tinyFile);
        userActions.performActionWithPolling(fileReportsPageObject.tinyFile, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("TINY", clusterId,2,2);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Avg File Size' column for Tiny file reports.");
    }
}