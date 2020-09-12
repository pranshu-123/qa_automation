package com.qa.testcases.cluster.impala.resources;

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

public class IM_RES_16 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyGroupByFilterForQueueHoverMemoryGraph(String clusterId) {
        test = extent.startTest("IM_RES_16.verifyGroupByFilterForQueueHoverMemoryGraph (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory("4620 - Cluster/Impala Resources");
        WaitExecuter executer = new WaitExecuter(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        // Click on Impala tab
        executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
        JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
        executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        executer.waitUntilPageFullyLoaded();

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast30Days();
        executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Select Date from DatePicker.");

        Impala impala = new Impala(driver);
        impala.selectQueueInGroupBy();
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        // Validate of Memory graph is present for selected date range
        Assert.assertTrue(impala.isMemoryGraphPresent(), "The memory graph is not present with expected conditions");
        test.log(LogStatus.PASS, "Validate the Memory graph is present for selected date range.");

        test.log(LogStatus.INFO, "Navigate different section in memory graph");
        GraphUtils graphUtils = new GraphUtils();
        graphUtils.navigateDifferentPointOnGraph(driver,
                impalaPageObject.memoryHighChartContainer);
        List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
        System.out.println("All Tool Tips"+memoryTooltipValues);

        for (int i=0; i<memoryTooltipValues.size(); i++) {
            Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed null value for memory graph");
            Assert.assertNotEquals(memoryTooltipValues.get(i), "", "Tooltip value displayed blank value for memory graph");
        }

        test.log(LogStatus.PASS,"Validate tool tip of memory graph display Total Memory and Allocated Memory of all Nodes.");

    }
}
