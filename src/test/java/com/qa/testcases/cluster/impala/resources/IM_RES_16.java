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
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
/**
 * @author Birender Kumar
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_16 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the tooltip of the graph should display the Total Memory and Allocated Memory of five Nodes")
    public void verifyGroupByFilterForQueueHoverMemoryGraph(String clusterId) {
        test = extent.startTest("IM_RES_16.verifyGroupByFilterForQueueHoverMemoryGraph (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory(" Cluster/Impala Resources");
        test.log(LogStatus.INFO, "Login to the application");

        //Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        DatePicker datePicker = new DatePicker(driver);
        HomePage homePage = new HomePage(driver);
        GraphUtils graphUtils = new GraphUtils();
        Impala impala = new Impala(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource("Impala");

        // Set multi cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select Last 30 days in date picker");
        LOGGER.info("Select Last 30 days in date picker");
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        //Select queue in group by
        test.log(LogStatus.INFO, "Select Queue in Group by option.");
        LOGGER.info("Select Queue in Group by option.");
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByQueueList.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        // Navigating on different points of memory graph and collect tooltips
        test.log(LogStatus.INFO, "Navigate different section in memory graph");
        LOGGER.info("Navigate different section in memory graph");
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.memoryHighChartContainer);
        List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        LOGGER.info("Memory tooltip values : " +memoryTooltipValues);

        // Check if tool-tip contains 5 nodes
        test.log(LogStatus.INFO, "Validate 'Total' and 'Allocated' memory have 5 nodes are present");
        List<String> nodes = new ArrayList<String>();
        System.out.println("MemoryToolTipsSize "+memoryTooltipValues.size());
        Assert.assertTrue(memoryTooltipValues.size()>5,"Failed to validate 'Total' and 'Allocated' memory have 5 nodes are present");
        test.log(LogStatus.PASS,"Validate tool tip of memory graph display Total Memory and Allocated Memory of all Nodes.");

    }
}
