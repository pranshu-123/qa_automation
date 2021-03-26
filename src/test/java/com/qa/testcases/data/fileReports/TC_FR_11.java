package com.qa.testcases.data.fileReports;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.scripts.data.FileReports;
import com.qa.utils.MouseActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

@Marker.DataFileReports
@Marker.All
public class TC_FR_11 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void validateFileSortForLargeFiles(String clusterId) {
        test = extent.startTest("validateFileSortForLargeFiles " + clusterId,
                "Validate the user is able sort based on number of 'Files' column for Large file reports");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        MouseActions.clickOnElement(driver, fileReportsPageObject.largeFile);
        filereports.verifyFileSortOption("LARGE", clusterId);
        test.log(LogStatus.PASS, "Successfully validate the sort based on number of 'Files' column for Large file reports.");
    }
}
