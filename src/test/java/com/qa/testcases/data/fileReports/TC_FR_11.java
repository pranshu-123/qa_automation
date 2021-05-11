package com.qa.testcases.data.fileReports;

import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class TC_FR_11 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void validatePathSortForLargeFiles(String clusterId) {
        test = extent.startTest("TC_FR_11.validatePathSortForLargeFiles " + clusterId,
                "Validate the user is able sort based on number of 'Path' column for Large file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.largeFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
        waitExecuter.sleep(2000);
        filereports.verifyAllSortOption("LARGE", clusterId,1,1);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Path' column for Large file reports.");
    }
}
