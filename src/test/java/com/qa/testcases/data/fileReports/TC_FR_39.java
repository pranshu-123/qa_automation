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
public class TC_FR_39 extends BaseClass {
    /**
     Validate user is able to search Min File size
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void validateMinFileSizeSearchOption(String clusterId) {
        test = extent.startTest("TC_FR_39.validateMinFileSizeSearchOption " + clusterId,
                "P1-Validate user is able to search Min File size");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
        filereports.verifyMinFileSizePathSearchOption("MEDIUM", clusterId);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully validate user is able to search Min File size.");
    }
}

