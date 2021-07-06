package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
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
public class IM_RES_08 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_08.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the Group By filter for user should display the Usage only for that particular user")
    public void verifyGroupByFilterForUser(String clusterId) {
        test = extent.startTest("IM_RES_08.verifyGroupByFilterForUser (" + clusterId + ")",
                "Validate the \"Group By\" filter for User.");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Impala impala = new Impala(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);
        //Select cluster id
        HomePage homePage = new HomePage(driver);
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impala.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        impala.selectImpalaType("Impala");
        waitExecuter.sleep(3000);
        //Select date
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impala.clearFilter();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        impalaPageObject.filterInput.click();
        System.out.println("Default filter elements size: "+impalaPageObject.filterElements.size());
        if(impalaPageObject.filterElements.size() > 0){
            String userName = impalaPageObject.filterElements.get(0).getText();
            System.out.println("Default filter 0th index name: "+ userName);
            impalaPageObject.filterElements.get(0).click();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            boolean isTagPresent = false;
            for (String graphTag : impala.getQueriesGraphLabels()) {
                if (graphTag.equals(userName)) {
                    isTagPresent = true;
                } else if (graphTag.contains("...") &&
                        userName.contains(graphTag.split("...")[0])) {
                    isTagPresent = true;
                } else {
                    isTagPresent = false;
                }
            }
            Assert.assertTrue(isTagPresent, "Filter user not displayed for user: " + userName);
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for user : " + userName);
        }else {
            impala.clearFilter();
            impalaPageObject.filterInput.click();
            System.out.println("Default filter elements size: " + impalaPageObject.filterElements.size());
            if (impalaPageObject.filterElements.size() > 0) {
                String userName = impalaPageObject.filterElements.get(0).getText();
                System.out.println("Default filter 0th index name: " + userName);
                impalaPageObject.filterElements.get(0).click();
                waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
                test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
                waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
                boolean isTagPresent = false;
                for (String graphTag : impala.getQueriesGraphLabels()) {
                    if (graphTag.equals(userName)) {
                        isTagPresent = true;
                    } else if (graphTag.contains("...") &&
                            userName.contains(graphTag.split("...")[0])) {
                        isTagPresent = true;
                    } else {
                        isTagPresent = false;
                    }
                }
                Assert.assertTrue(isTagPresent, "Filter user not displayed for user: " + userName);
                test.log(LogStatus.PASS, "Graph displayed the user based on filter for user : " + userName);
            } else {
                test.log(LogStatus.INFO, "No Elements found in filter.");
                test.log(LogStatus.FAIL, "No Element to filter.");
            }
        }

    }
}
