package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
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
public class IM_RES_11 extends BaseClass {
    private Impala impala;
    private WaitExecuter waitExecuter;
    private DatePicker datePicker;
    private ImpalaPageObject impalaPageObject;
    private static final Logger LOGGER = Logger.getLogger(IM_RES_11.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the user chooses each date picker to display the memory and Queries graphs.")
    public void IM_RES_11_verifyQueryGraphForQueueGroupForTwoDaysDatePickerOptions(String clusterId) {
        test = extent.startTest("IM_RES_11_verifyQueryGraphForQueueGroupForTwoDaysDatePickerOptions : "+clusterId,
                "Verify Query graph one by one for each of the options in date picker range");
        test.assignCategory(" Cluster/Impala Resources");
        impala = new Impala(driver);
        waitExecuter = new WaitExecuter(driver);
        datePicker = new DatePicker(driver);
        impalaPageObject = new ImpalaPageObject(driver);
        test.log(LogStatus.INFO, "Login to the application");

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);

        // Select the cluster
        LOGGER.info("Selecting the cluster");
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Select last one hour
        test.log(LogStatus.INFO, "Click on date picker");
        datePicker.clickOnDatePicker();
        test.log(LogStatus.INFO, "Select last 1 hour");
        datePicker.selectLastOneHour();
        waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
         waitExecuter.sleep(2000);
        impalaPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByQueueList.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

        LOGGER.info("Verifying memory and query graph for last 2 hour");
        // Select last 2 hour
        test.log(LogStatus.INFO, "Click on date picker");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Last 2 hours");
        datePicker.selectLastTwoHour();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
    }

}



