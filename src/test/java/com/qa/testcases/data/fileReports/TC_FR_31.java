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
public class TC_FR_31 extends BaseClass {
    /**
     Validate the user is able sort based on "Avg File Size" column for Medium file reports
     */
    @Test(dataProvider = "clusterid-data-provider",description = "P1-Validate that the user is able to sort based on Avg File Size column for Medium file reports")
    public void validateAvgFileSizeSortForMediumFiles(String clusterId) {
        test = extent.startTest("TC_FR_31.validateAvgFileSizeSortForMediumFiles " + clusterId,
                "P1-Validate that the user is able to sort based on Avg File Size column for Medium file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("MEDIUM", clusterId,3,3);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Avg File Size' column for Medium file reports.");
    }
}
