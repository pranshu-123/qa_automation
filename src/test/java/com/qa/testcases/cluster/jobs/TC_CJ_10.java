package com.qa.testcases.cluster.jobs;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Jobs;
import com.qa.utils.GraphUtils;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;

import java.util.List;
/**
 * @author Sarbashree Ray
 */

@Marker.ClusterJobs
@Marker.All
public class TC_CJ_10 extends BaseClass {


    public void TC_CJ_10_VerifyJobsuserfilterscombinations (String clusterId) {
        test = extent.startTest("TC_CJ_10_VerifyJobsuserfilterscombinations (" + clusterId + ")", "Validate Jobs Application type in Group By ");
        test.assignCategory("4620 - Cluster / Job");
        Log.startTestCase("TC_CJ_10_VerifyJobsuserfilterscombinations");
        WaitExecuter executer = new WaitExecuter(driver);
        JobsPageObject jobsPageObject=new JobsPageObject(driver);
        GraphUtils graphUtils = new GraphUtils();
        HomePage homePage = new HomePage(driver);

        //Initialize Jobs Page Object
        Jobs jobs = new Jobs(driver);
        jobs.verifyjobsHeaderisDisplayed();
        Log.info("Jobs Header is displayed.");
        test.log(LogStatus.INFO, "Jobs Header is displayed.");

        // Set multi cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        Log.info("Jobs Header is displayed.");
        homePage.selectMultiClusterId(clusterId);
        executer.sleep(1000);
        executer.waitUntilPageFullyLoaded();
        executer.sleep(1000);


        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast90Days();
        executer.sleep(4000);

        jobs.clickOnGroupByDropDown();
        Log.info("Click on GroupByDropDown.");
        jobs.selectApplicationType();
        executer.sleep(1000);
        Log.info("Selected Application Type, from dropdown.");
        test.log(LogStatus.INFO, "Selected Application Type, from dropdown.");

        // Navigating on different points of memory graph and collect tooltips
        test.log(LogStatus.INFO, "Navigate different section in jobs graph");

        executer.sleep(8000);

        graphUtils.navigateDifferentPointOnGraph(driver, jobsPageObject.JobHighChartContainer);
        List<String> memoryTooltipValues = graphUtils.getMemoryTooltipValues();
        List<String> queriesTooltipValues = graphUtils.getQueriesTooltipValues();

        for (int i = 0; i < memoryTooltipValues.size(); i++) {
            Assert.assertNotNull(memoryTooltipValues.get(i), "Tooltip value displayed null value for jobs graph");
            Assert.assertNotEquals(memoryTooltipValues.get(i), "",
                    "Tooltip value displayed blank value for jobs graph");
        }


        test.log(LogStatus.PASS, "Validate When the user hovers the mouse over the jobs graph"
                + " it should simultaneously display the tool tip for jobs graph at the same data point");

        Log.endTestCase("TC_CJ_10_VerifyJobsuserfilterscombinations");
    }
}