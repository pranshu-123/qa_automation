package com.qa.testcases.cluster.hbase;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.HBasePage;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ClusterHBase
@Marker.All
public class TC_HB_23 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_HB_23.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyAllDatesFromHBaseTab(String clusterId) {
        test = extent.startTest("TC_HB_23.verifyAllDatesFromHBaseTab",
                "Verify all the date pickers.");
        test.assignCategory(" Cluster - HBasePage ");
        Log.startTestCase("TC_HB_23.verifyAllDatesFromHBaseTab");

        // Initialize all classes objects
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HBasePage hbase = new HBasePage(driver);
        HBasePageObject hBasePageObject = new HBasePageObject(driver);

        //Navigate to HBase tab
        waitExecuter.waitUntilElementClickable(hBasePageObject.hbaseTab);
        MouseActions.clickOnElement(driver, hBasePageObject.hbaseTab);
        LOGGER.info("Clicked on HBase Tab");
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        LOGGER.info("HBase headers found: " + hbase.getHBaseHeader());

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();

        String[] expectedDateOptions = {"Last 1 Hour","Last 2 Hours", "Last 6 Hours","Last 12 Hours","Today",
                "Yesterday", "Last 7 Days", "Last 30 Days", "This Month", "Last Month", "Custom Range"};

        for (String expectedDateOption : expectedDateOptions) {
            Assert.assertTrue(datePicker.getDatePickerOptions().contains(expectedDateOption),
                    "Date list does not contain: " + expectedDateOption);
            test.log(LogStatus.PASS, "Date list contains option: " + expectedDateOption);
        }
        datePicker.clickOnDatePicker();
        LOGGER.info("HBase clusters Verify all the date pickers.");
        test.log(LogStatus.PASS, "Verified all the date pickers on HBase.");


    }
}
