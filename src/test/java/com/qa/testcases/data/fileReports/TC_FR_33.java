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
public class TC_FR_33 extends BaseClass {
    /**
     Validate the user is able sort based on "Min File Size" column for Medium file reports
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMinFileSizeSortForMediumFiles(String clusterId) {
        test = extent.startTest("TC_FR_30.validateMinFileSizeSortForMediumFiles " + clusterId,
                "P1-Validate the user is able sort based on number of 'Min File Size' column for Medium file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllSortOption("MEDIUM", clusterId,5,5);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate the 'Min File Size' column for Medium file reports.");
    }
}
