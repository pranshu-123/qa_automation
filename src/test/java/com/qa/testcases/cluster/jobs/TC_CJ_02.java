package com.qa.testcases.cluster.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Jobs;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

@Marker.ClusterJobs
@Marker.All
public class TC_CJ_02 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CJ_02_VerifyJobsstateinGroupBy(String clusterId) {
        test = extent.startTest("TC_CJ_02.VerifyJobsstateinGroupBy (" + clusterId + ")", "Validate Jobs state in Group By available");
        test.assignCategory(" Cluster / Job");
        Log.startTestCase("TC_CJ_02_VerifyJobsstateinGroupBy");
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
        jobs.selectState();
        test.log(LogStatus.INFO, "Selected Queue, from dropdown.");
        List<String> allDefaultFilterElementForQueue = jobs.getAllDefaultSelectedFilterElements();
        System.out.println("All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);
        Log.info("All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);
        test.log(LogStatus.INFO, "All Queue: filter default selected elements: "+ allDefaultFilterElementForQueue);

        test.log(LogStatus.PASS,"Verified group by element and default filter elements on Jobs Page. ");
        Log.endTestCase("TC_CJ_02_VerifyJobsstateinGroupBy");
        }
        }

