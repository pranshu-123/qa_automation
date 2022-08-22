package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
 * @author - Ojasvi Pandey
 */

@Marker.ImpalaResources
@Marker.All
public class IM_RES_18 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_18.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the UI should display the usage only the selected group of Queues")
    public void IM_RES_18_verifyDataDisplayedAsFilteredQueue(String clusterId) {
        test = extent.startTest("IM_RES_18.verifyDataDisplayedAsFilteredQueue (" + clusterId + ")",
                "Validate the \"Group By\" filter for Queue.");
        test.assignCategory(" Cluster/Impala Resources");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        HomePage homePage = new HomePage(driver);
        Impala impala = new Impala(driver);
        DatePicker datePicker = new DatePicker(driver);
        UserActions userActions = new UserActions(driver);
        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);

        // Select the cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        impala.selectImpalaType("Impala");
        waitExecuter.sleep(3000);


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
        userActions.performActionWithPolling(impalaPageObject.groupByDropdownButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(impalaPageObject.groupByQueueList);
        userActions.performActionWithPolling(impalaPageObject.groupByQueueList, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impala.clearFilter();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        List<String> queueNameList = new ArrayList<String>();
        for (int i = 0; i < impalaPageObject.filterElements.size(); i++) {
            String queueName = impalaPageObject.filterElements.get(i).getText();
            if (queueName != null) {
            queueNameList.add(queueName);
            impalaPageObject.filterElements.get(i).click();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            waitExecuter.sleep(2000);
            test.log(LogStatus.INFO, "Selecting the queue: " + queueName + " in filter.");
            LOGGER.info("Selecting the queue: " + queueName + " in filter.");
            boolean isTagPresent = false;
            List<String> graphTags = impala.getQueriesGraphLabels();
            LOGGER.info("GRAPH TAG- " + graphTags);
            LOGGER.info("QUEUE NAME- " + queueName);
            isTagPresent = graphTags.contains(queueName);
            Assert.assertTrue(isTagPresent, "Filter user not displayed for queue: " + queueName);
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + queueName);
            impalaPageObject.filterInput.click();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            waitExecuter.sleep(3000);
        }
        impalaPageObject.filterInput.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        waitExecuter.sleep(3000);
    }}
}
