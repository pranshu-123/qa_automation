package com.qa.testcases.cluster.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Jobs;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.testcases.cluster.impala.resources.IM_RES_06;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */

@Marker.ClusterJobs
@Marker.All
public class TC_CJ_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CJ_01.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CJ_01_VerifyGroupByfiltersavailable(String clusterId) {
        test = extent.startTest("TC_CJ_01.VerifyGroupByfiltersavailable(" + clusterId + ")", "Validate GroupBy filters available");
        test.assignCategory("4620 - Cluster / Job");
        Log.startTestCase("TC_CJ_01_VerifyGroupByfiltersavailable");
        WaitExecuter executer = new WaitExecuter(driver);
        HomePage homePage = new HomePage(driver);


        //Initialize Jobs Page Object
        Jobs jobs = new Jobs(driver);
        jobs.verifyjobsHeaderisDisplayed();
        Log.info("Jobs Header is displayed.");
        test.log(LogStatus.INFO, "Jobs Header is displayed.");

        // Set multi cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        Log.info("Select cluster : " + clusterId);
        homePage.selectMultiClusterId(clusterId);
        executer.sleep(1000);
        executer.waitUntilPageFullyLoaded();
        executer.sleep(1000);


        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast90Days();
        executer.waitUntilPageFullyLoaded();




        jobs.clickOnGroupByDropDown();
        executer.sleep(1000);
        if (!jobs.selectState()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by State");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by State");
        }


        jobs.clickOnGroupByDropDown();
        executer.waitUntilPageFullyLoaded();
        if (!jobs.selectApplicationType()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by ApplicationType");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by ApplicationType");
        }


        jobs.clickOnGroupByDropDown();
        executer.waitUntilPageFullyLoaded();
        if (!jobs.selectUser()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by User");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by User");
        }


        jobs.clickOnGroupByDropDown();
        executer.waitUntilPageFullyLoaded();
        if (!jobs.selectQueue()) {
            test.log(LogStatus.PASS, "Verify select dropdown in Group by Queue");
        } else {
            test.log(LogStatus.FAIL, "Test Failed select dropdown in Group by Queue");
        }
        Log.endTestCase("TC_CJ_01_VerifyGroupByfiltersavailable");


    }
}

