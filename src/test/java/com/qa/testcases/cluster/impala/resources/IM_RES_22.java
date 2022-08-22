package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_22  extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_22.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the number of queries in the table should matches the number in the tooltip of the queries chart")
    public void IM_RES_22_verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip(String clusterId) {
        test = extent.startTest("IM_RES_22.verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Impala impala = new Impala(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        //Select clsuter id
        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        impala.selectImpalaType("Impala");
        waitExecuter.sleep(3000);

        //Select date
        try {
            DatePicker datePicker = new DatePicker(driver);
            datePicker.clickOnDatePicker();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            datePicker.selectLast90Days();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            test.log(LogStatus.INFO, "Select Date from DatePicker.");

            waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
            waitExecuter.sleep(3000);
            impalaPageObject.groupByDropdownButton.click();
            impalaPageObject.groupByUserList.click();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            test.log(LogStatus.INFO, "Select User in Group by option.");

            // Validate of Memory graph is present for selected date range
            Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
            test.log(LogStatus.PASS, "Validate the Memory graph is present for selected date range.");

            //TBD
            //1. Navigate to Memory graph and click
            //2. Read the impalaQueries header text if it does not matches "No Impala queries" then matches tooltip text with header impala queries text

            test.log(LogStatus.INFO, "Navigate different section in memory graph");
            GraphUtils graphUtils = new GraphUtils();
            waitExecuter.waitUntilPageFullyLoaded();
            graphUtils.navigateDifferentPointOnGraphGetTextClickCheckImpalaTbl(driver, impalaPageObject.memoryHighChartContainer);
            test.log(LogStatus.PASS, "Successfully read the impalaQueries header text, after click on memory graph.");

        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            test.log(LogStatus.INFO, "Selecting the queue: " + ex + " in filter.");
        }
    }
}
