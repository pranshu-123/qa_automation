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
public class TC_FR_41 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void validateMaxFilesizeFileSearchOption(String clusterId) {
        test = extent.startTest("TC_FR_41.validateMaxFilesizeFileSearchOption " + clusterId,
                "Validate user is able search a particular Max File size");
        test.assignCategory(" Data - FileReports ");

        FileReports filereports = new FileReports(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        FileReportsPageObject fileReportsPageObject = new FileReportsPageObject(driver);

        filereports.navidateTofileReportsTab();
        waitExecuter.waitUntilElementPresent(fileReportsPageObject.mediumFile);
        MouseActions.clickOnElement(driver, fileReportsPageObject.mediumFile);
        waitExecuter.waitUntilPageFullyLoaded();
        filereports.verifyAllFileSizePathSearchOption("MEDIUM", clusterId,6,6);
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Successfully Validate user is able to search the Max File size.");
    }
}

