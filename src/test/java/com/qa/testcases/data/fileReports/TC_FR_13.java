package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_13 extends BaseClass {
    /**
    Validate the user is able sort based on "Avg File Size" column for Large file reports
     */
    private final static LoggingUtils LOGGER = new LoggingUtils(TC_FR_13.class);
    @Test(dataProvider = "clusterid-data-provider")
    public void validateSortByAvgFileSize(String clusterId) {
        test = extent.startTest("TC_FR_13.validateSortByAvgFileSize " + clusterId,
                "P1-Validate the user is able sort based on \"Avg File Size\" column for Large file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.tinyFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.tinyFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAvgFileSizeSortOption("LARGE", clusterId);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Avg File Size' column for Large file reports.");

    }
}
