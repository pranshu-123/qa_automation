package com.qa.testcases.cluster.jobs;

import com.qa.base.BaseClass;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.GraphColorConstants;
import com.qa.pagefactory.OverviewGraphPageObject;
import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.ScreenshotHelper;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class TC_CO_02 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyJobsstateinGroupBy(String clusterId) {
        test = extent.startTest("TC_CO_01.VerifyJobsstateinGroupBy (" + clusterId + ")", "Validate Jobs state in Group By available");
        test.assignCategory("4620 - Cluster / Job");
        Log.info("Start the test cases Verify Jobs state in Group By ");
        WaitExecuter executer = new WaitExecuter(driver);

        JobsPageObject jobsPageObject = new JobsPageObject(driver);

        // Click on Chargeback tab
        executer.waitUntilElementClickable(jobsPageObject.jobsTab);
        JavaScriptExecuter.clickOnElement(driver, jobsPageObject.jobsTab);
        executer.waitUntilElementPresent(jobsPageObject.getjobsPageHeader);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast90Days();
        executer.waitUntilPageFullyLoaded();


        executer.waitUntilElementClickable(jobsPageObject.groupByDropdownButton);
        executer.sleep(3000);
        jobsPageObject.groupByDropdownButton.click();
        jobsPageObject.groupByState.click();
        for (int i = 0; i < jobsPageObject.filterElements.size(); i++) {
            String queueName = jobsPageObject.filterElements.get(i).getText();
            executer.waitUntilPageFullyLoaded();
            executer.sleep(1000);
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue " );
            // Take Screenshot and validate the graph
            OverviewGraphPageObject overviewGraph = new OverviewGraphPageObject(driver);
            File screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.JobsGraph,0);
            ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
            test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.NEW_COLOR),
                    "Total NEW allocated graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated total NEW graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.RUNNING_COLOR),
                    "Total RUNNING available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated RUNNING graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.ACCEPTED_COLOR),
                    "Total ACCEPTED available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated ACCEPTED graph is loaded");

            overviewGraph.JobsAcceptedCheckbox.click();
            executer.sleep(2000);
            test.log(LogStatus.INFO, "Uncheck running check box.");
            executer.sleep(2000);
            test.log(LogStatus.INFO, "Uncheck running check box and validate the accepted graph");
            executer.sleep(1000);
            screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.JobsGraph, 0);
            ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
            test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.NEW_COLOR),
                    "Total NEW allocated graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated total NEW graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.RUNNING_COLOR),
                    "Total RUNNING available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated RUNNING graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.ACCEPTED_COLOR),
                    "Total ACCEPTED available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated ACCEPTED graph is loaded");


            overviewGraph.JobsAcceptedCheckbox.click();
            executer.sleep(2000);
            overviewGraph.JobsRunningCheckbox.click();
            test.log(LogStatus.INFO, "Check running check box and unchecked accepted checkbox.");
            executer.sleep(3000);
            test.log(LogStatus.INFO, "Uncheck accepted check box and validate the running graph.");
            executer.sleep(1000);
            screenshot = ScreenshotHelper.takeScreenshotOfElement(driver,overviewGraph.JobsGraph,0);
            ScreenshotHelper.saveFileToLocation(screenshot, DirectoryConstants.getScreenshotDir() + screenshot.getName());
            test.log(LogStatus.INFO, test.addScreenCapture(DirectoryConstants.getScreenshotDir() + screenshot.getName()));
            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.NEW_COLOR),
                    "Total NEW allocated graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated total NEW graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.RUNNING_COLOR),
                    "Total RUNNING available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated RUNNING graph is loaded");

            Assert.assertTrue(ScreenshotHelper.isContainColor(screenshot, GraphColorConstants.JobsGraph.ACCEPTED_COLOR),
                    "Total ACCEPTED available graph is not loaded");
            test.log(LogStatus.PASS, "Successfully validated ACCEPTED graph is loaded");


        }
        }
    }

