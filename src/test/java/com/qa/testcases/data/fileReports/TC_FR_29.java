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
public class TC_FR_29 extends BaseClass {
    /**
     Validate the user is able sort the UI based on "Path" column for Medium file reports
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validatePathForEmptyFiles(String clusterId) {
        test = extent.startTest("TC_FR_29.validatePathForEmptyFiles " + clusterId,
                "P1-Validate the user is able sort the UI based on \"Path\" column for Medium file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.emptyFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.emptyFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("MEDIUM", clusterId,1,1);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Path' column for Medium file reports.");

    }
}
