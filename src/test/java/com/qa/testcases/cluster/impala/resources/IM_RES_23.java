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

import java.util.List;
import java.util.logging.Logger;

@Marker.ImpalaResources
@Marker.All
public class IM_RES_23 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_23.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the data displayed for the Impala queries")
    public void verifythedatadisplayedfortheImpalaqueriesTblToolTip(String clusterId) {
        test = extent.startTest("IM_RES_23.verifythedatadisplayedfortheImpalaqueriesTblToolTip (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory(" Cluster/Impala Resources");
        test.log(LogStatus.INFO, "Login to the application");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        HomePage homePage = new HomePage(driver);
        Impala impala = new Impala(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource("Impala");
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Select the cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Select 30 days from date picker
        test.log(LogStatus.INFO, "Select 30 days from date picker");
        LOGGER.info("Select 30 days from date picker");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Click on group by dropdown and select queue in filter
        test.log(LogStatus.INFO, "Click on group by dropdown and select queue in filter");
        LOGGER.info("Click on group by dropdown and select queue in filter");
        waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
        impalaPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByQueueList.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impala.clearFilter();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        waitExecuter.sleep(1000);

        test.log(LogStatus.INFO, "Navigate different section in memory graph");
        GraphUtils graphUtils = new GraphUtils();
        graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.queryHighChartContainer);
        List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
        List<String> queriesTooltipValues = graphUtils.getQueriesTooltipValues();

        for (int i = 0; i < memoryTooltipValues.size(); i++) {
            Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed null value for memory graph");
            Assert.assertNotEquals(memoryTooltipValues.get(i), "",
                    "Tooltip value displayed blank value for memory graph");
            Assert.assertNotNull(queriesTooltipValues.get(i), "Tooltip value displayed null value for query graph");
            Assert.assertNotEquals(queriesTooltipValues.get(i), "",
                    "Tooltip value not displayed null value for query graph");
        }
        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the Query graph"
                + " it should simultaneously display the tool tip for Memory graph at the same data point");

    }
}
