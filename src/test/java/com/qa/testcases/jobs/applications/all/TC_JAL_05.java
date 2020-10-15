package com.qa.testcases.jobs.applications.all;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.AllApps
@Marker.All
public class TC_JAL_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_JAL_05.class.getName());

    @Test
    public void validateDatePickerFilters() {
        test = extent.startTest("TC_JAL_05.validateDatePickerFilters",
                "Verify the list of cluster options available");
        test.assignCategory("4620 Jobs - Applications");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        // Get cluster list
        test.log(LogStatus.INFO, "Get cluster list");
        LOGGER.info("Get cluster list");
        waitExecuter.sleep(1000);
        applicationsPageObject.clusterSearchBox.click();
        List<WebElement> listOfClusters = applicationsPageObject.getclusterListDropdown;
        waitExecuter.sleep(1000);
        //Verification
        Assert.assertTrue(listOfClusters.size() > 0, "Cluster list is empty");
        test.log(LogStatus.PASS, "Verified that the cluster list is not empty. The size is: "
                + listOfClusters.size());
    }
}
