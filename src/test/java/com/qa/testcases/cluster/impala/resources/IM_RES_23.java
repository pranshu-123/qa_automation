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

    @Test(dataProvider = "clusterid-data-provider")
    public void verifythedatadisplayedfortheImpalaqueriesTblToolTip(String clusterId) {
        test = extent.startTest("IM_RES_23.verifythedatadisplayedfortheImpalaqueriesTblToolTip (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory("4620 - Cluster/Impala Resources");
        test.log(LogStatus.INFO, "Login to the application");
        WaitExecuter executer = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        HomePage homePage = new HomePage(driver);
        Impala impala = new Impala(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        // Click on Impala tab
        executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
        JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
        executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

        // Click on Impala tab
        test.log(LogStatus.INFO, "Go to impala page");
        LOGGER.info("Clicking on Impala tab");
        executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
        JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
        executer.sleep(1000);
        executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

        // Select the cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        executer.sleep(3000);
        homePage.selectMultiClusterId(clusterId);
        executer.waitUntilPageFullyLoaded();

        // Select 30 days from date picker
        test.log(LogStatus.INFO, "Select 30 days from date picker");
        LOGGER.info("Select 30 days from date picker");
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast30Days();
        executer.sleep(1000);
        executer.waitUntilPageFullyLoaded();
        executer.sleep(1000);

        // Click on group by dropdown and select queue in filter
        test.log(LogStatus.INFO, "Click on group by dropdown and select queue in filter");
        LOGGER.info("Click on group by dropdown and select queue in filter");
        executer.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
        executer.sleep(3000);
        impalaPageObject.groupByDropdownButton.click();
        executer.sleep(1000);
        impalaPageObject.groupByQueueList.click();
        executer.sleep(1000);
        impala.clearFilter();
        executer.sleep(1000);
        executer.waitUntilPageFullyLoaded();
        executer.sleep(1000);

        executer.sleep(2000);
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
