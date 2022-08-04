package com.qa.testcases.cluster.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.GraphsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.Graphs;
import com.qa.scripts.HomePage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_14 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CO_14.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the jobs by status KPI graph should be generated for the selected filters")
    public void TC_CO_14_VerifyJobsByStatusKPIGraphFilter(String clusterId) {
        // Select this month
        test = extent.startTest("TC_CO_14_VerifyJobsByStatusKPIGraphFilter" + clusterId, "Verify cluster by status KPI graph filter");
        test.assignCategory(" Cluster Overview");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        Graphs graph = new Graphs(driver);
        GraphsPageObject graphsPageObject = new GraphsPageObject(driver);

        test.log(LogStatus.INFO, "This testcase is running for cluster: " + clusterId);
        //Select the cluster
        LOGGER.info("Selecting cluster from the list " + clusterId);
        HomePage homePage = new HomePage(driver);
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        waitExecuter.waitUntilElementClickable(commonPageObject.addIcon);
        waitExecuter.sleep(3000);
        homePage.selectMultiClusterId(clusterId);
        // Click on datepicker button
        LOGGER.info("Click on date picker");
        test.log(LogStatus.INFO, "Click on date picker");
        waitExecuter.sleep(3000);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);

        // Click on custom range
        LOGGER.info("Click on date picker");
        test.log(LogStatus.INFO, "Click on date picker");
        datePicker.selectCustomRange();
        waitExecuter.sleep(1000);
        /*
         * Set Start date by substracting days from current date and end date as
         * currentdate
         */
        LOGGER.info("Set current and past date");
        test.log(LogStatus.INFO, "Set current and past date");
        datePicker.setCurrentAndPastDate(-30);
        waitExecuter.sleep(1000);
        // Click on apply button of Cluster
        LOGGER.info("Click on apply button");
        test.log(LogStatus.INFO, "Click on apply button");
        datePicker.clickOnCustomDateApplyBtn();
        waitExecuter.sleep(1000);
        try {
            LOGGER.info("Verify By Type graph");
            test.log(LogStatus.INFO, "Verify by type graph");
            Assert.assertTrue(graph.validateGraphIsGenerated(graphsPageObject.byStatusFooterNames, graphsPageObject.statusRGBColor, graphsPageObject.byStatusHexcode), "The color code of graph does not match");
        } catch (NoSuchElementException error) {
            // Skip test case if By Type title is not present
            test.log(LogStatus.SKIP, "Testcase need to be skipped as By Status graph heading is not present");
        }
    }
}
