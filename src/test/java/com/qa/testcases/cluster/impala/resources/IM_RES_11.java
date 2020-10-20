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
    private static final Logger LOGGER = Logger.getLogger(IM_RES_10.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void IM_RES_11_verifyQueryGraphForQueueGroupForTwoDaysDatePickerOptions(String clusterId) {
        test = extent.startTest("IM_RES_10_verifyQueryGraphForQueueGroupForTwoDaysDatePickerOptions : "+clusterId,
                "Verify Query graph one by one for each of the options in date picker range");
        test.assignCategory(" Cluster/Impala Resources");
        impala = new Impala(driver);
        waitExecuter = new WaitExecuter(driver);
        datePicker = new DatePicker(driver);
        impalaPageObject = new ImpalaPageObject(driver);
        test.log(LogStatus.INFO, "Login to the application");

        test.log(LogStatus.INFO, "Go to impala page");
        waitExecuter.waitUntilElementClickable(impalaPageObject.clusterImpalaTab);
        JavaScriptExecuter.clickOnElement(driver, impalaPageObject.clusterImpalaTab);
        waitExecuter.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

        // Select the cluster
        LOGGER.info("Selecting the cluster");
        waitExecuter.sleep(1000);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        // Select last one hour
        test.log(LogStatus.INFO, "Click on date picker");
        datePicker.clickOnDatePicker();
        test.log(LogStatus.INFO, "Select last 1 hour");
        datePicker.selectLastOneHour();
        waitExecuter.sleep(3000);
        impalaPageObject.groupByDropdownButton.click();
        impalaPageObject.groupByQueueList.click();
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");

        LOGGER.info("Verifying memory and query graph for last 2 hour");
        // Select last 2 hour
        test.log(LogStatus.INFO, "Click on date picker");
        datePicker.clickOnDatePicker();
        test.log(LogStatus.INFO, "Select Last 2 hours");
        datePicker.selectLastTwoHour();
        waitExecuter.sleep(3000);

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        // Validate of Query graph is present for selected date range
        Assert.assertTrue(impala.isQueryGraphPresent(), "The query graph is not present with expected conditions");
    }

}



