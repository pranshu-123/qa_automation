package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ImpalaResources
@Marker.All
public class IM_RES_12 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_12.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user chooses the same date range to display the memory and Queries graphs")
    public void verifyGroupByFilterForQueue(String clusterId) {
        test = extent.startTest("IM_RES_12.verifyGroupByFilterForQueue (" + clusterId + ")", "Validate the \"Group By\" filter for Queue.");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Impala impala = new Impala(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        HomePage homePage = new HomePage(driver);
        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource("Impala");
        waitExecuter.sleep(2000);

        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Date from DatePicker.");
        impala.selectQueueInGroupBy();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        test.log(LogStatus.PASS, "Validate the Memory graph is present for selected date range.");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
        test.log(LogStatus.PASS, "Validate the Query graph is present for selected date range.");

    }
}
