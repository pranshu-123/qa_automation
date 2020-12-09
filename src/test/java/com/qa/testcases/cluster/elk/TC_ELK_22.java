package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.ClusterELK
public class TC_ELK_22 extends BaseClass {

  private static final java.util.logging.Logger LOGGER = Logger.getLogger(com.qa.testcases.cluster.elk.TC_ELK_22.class.getName());

  @Test(dataProvider = "clusterid-data-provider")
  public void verifyESTab(String clusterId) {
    test = extent.startTest("TC_ELK_22_verifyESTab: " + clusterId,
        "Validate cluster drop down , date range and cluster name in UI.");
    test.assignCategory(" ELK ");
    Log.startTestCase("TC_ELK_22_verifyESTab");

    // Initialize all classes objects
    test.log(LogStatus.INFO, "Initialize all class objects");
    LOGGER.info("Initialize all class objects");
    ELKPage elkPage = new ELKPage(driver);
    ELKPageObject elkPageObj = new ELKPageObject(driver);
    DatePicker datePicker = new DatePicker(driver);
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    AllApps allApps = new AllApps(driver);

    // Navigate to ES tab from header
    MouseActions.clickOnElement(driver, elkPageObj.ESTab);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(2000);

    // Click on date picker and get list of calendar ranges
    test.log(LogStatus.INFO, "Click on date picker and list of calendar ranges");
    LOGGER.info("Click on date picker and list of calendar ranges");
    datePicker.clickOnDatePicker();
    waitExecuter.sleep(1000);
    List<String> calendarRanges = allApps.getCalendarRanges();
    waitExecuter.sleep(3000);
    elkPage.verifyDateRange(calendarRanges, test);

    elkPage.verifyClusterDropDown(elkPageObj);
    elkPage.verifyClusterName(elkPageObj);

    test.log(LogStatus.PASS, "Verified cluster drop down , date range and cluster name in UI successfully ");
  }
}