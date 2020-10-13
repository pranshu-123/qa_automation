package com.qa.testcases.cluster.jobs;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Jobs;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
/**
 * @author Sarbashree Ray
 */
public class TC_CJ_04 extends BaseClass {


    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CJ_04_Verifycombinationsfilters(String clusterId) {
        test = extent.startTest("TC_CJ_04_Verifycombinationsfilters (" + clusterId + ")", "Validate Jobs state filters");
        test.assignCategory("4620 - Cluster / Job");
        Log.startTestCase("TC_CJ_04_Verifycombinationsfilters");
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
        Log.info("Click on GroupByDropDown.");
        jobs.selectApplicationType();
        Log.info("Selected Application Type, from dropdown.");
        test.log(LogStatus.INFO, "Selected Application Type, from dropdown.");
        test.log(LogStatus.PASS, "Verify Application Type, from dropdown.");

        Log.endTestCase("TC_CJ_04_Verifycombinationsfilters");

    }
}
