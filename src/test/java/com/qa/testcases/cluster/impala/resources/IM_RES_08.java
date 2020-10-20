package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
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

/**
 * @author Birender Kumar
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_08 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_08.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyGroupByFilterForUser(String clusterId) {
        test = extent.startTest("IM_RES_08.verifyGroupByFilterForUser (" + clusterId + ")",
                "Validate the \"Group By\" filter for User.");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter executer = new WaitExecuter(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        // Click on Impala tab
        executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
        JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
        executer.sleep(2000);
        executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);
        //Select cluster id
        HomePage homePage = new HomePage(driver);
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        executer.sleep(3000);
        homePage.selectMultiClusterId(clusterId);
        executer.sleep(2000);
        executer.waitUntilPageFullyLoaded();
        //Select date
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast30Days();
        executer.waitUntilPageFullyLoaded();

        Impala impala = new Impala(driver);
        impala.clearFilter();
        executer.waitUntilPageFullyLoaded();

        impalaPageObject.filterInput.click();
        System.out.println("Default filter elements size: "+impalaPageObject.filterElements.size());
        if(impalaPageObject.filterElements.size() > 0){
            String userName = impalaPageObject.filterElements.get(0).getText();
            System.out.println("Default filter 0th index name: "+ userName);
            impalaPageObject.filterElements.get(0).click();
            executer.waitUntilPageFullyLoaded();
            test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
            executer.sleep(2000);
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
                executer.waitUntilPageFullyLoaded();
                test.log(LogStatus.INFO, "Selecting the user: " + userName + " in filter.");
                executer.sleep(2000);
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
