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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.ImpalaResources
@Marker.All
public class IM_RES_15 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_15.class.getName());
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the Memory Chart should display the memory limit and allocation for each node")
    public void IM_RES_15_verifymemorylimiandallocationeachnodeMemoryChart(String clusterId) {
        test = extent.startTest("IM_RES_15.verifymemorylimiandallocationeachnodeMemoryChart : " + clusterId,
                "Verify that the memory limit and allocation for each node are displayed by the Memory Chart.)");
        test.assignCategory(" Cluster/Impala Resources");

        test.log(LogStatus.INFO, "Login to the application");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Impala impala = new Impala(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource("Impala");

        // Select the cluster
        LOGGER.info("Selecting the cluster");
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        impalaPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByQueueList.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select this month in date picker");

        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Navigate different section in memory graph");
        GraphUtils graphUtils = new GraphUtils();
        graphUtils.navigateDifferentPointOnGraph(driver, impalaPageObject.memoryHighChartContainer);
        List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();

        List<String> nodeValues = new ArrayList<String>();
        for (int i = 0; i < memoryTooltipValues.size(); i++) {
            if (memoryTooltipValues.get(i).contains("-")) {
                test.log(LogStatus.INFO,"Before split " + memoryTooltipValues.get(i));
                String[] splitByHyphen = memoryTooltipValues.get(i).split("-");
                for (int j = 0; j < splitByHyphen.length; j++) {
                    if (splitByHyphen[j].contains("Total") || splitByHyphen[j].contains("Allocated")) {
                        String[] splitByColon = splitByHyphen[j].split(":");
                        nodeValues.add(splitByColon[0]);
                        LOGGER.info("split by colon " + splitByColon[0]);
                        test.log(LogStatus.INFO,"split by colon " + splitByColon[0]);
                    }
                }
            }
        }
        //Assertion checks
        test.log(LogStatus.INFO, "Check with Assertions");
        LOGGER.info("Check with Assertions");
        LOGGER.info("NODE VALUE - " +nodeValues);
        Assert.assertTrue(nodeValues.contains("Total"), "The tootip in memory graph does not contain 'Total' keyword");
        Assert.assertTrue(nodeValues.contains("Allocated"),
                "The tootip in memory graph does not contain 'Allocated' keyword");

        test.log(LogStatus.PASS, "verify the  the Total Memory and Allocated Memory of all the Nodes" + memoryTooltipValues);


    }
    }


