package com.qa.testcases.cluster.jobs;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.JobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class TC_CO_01 extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyGroupByfiltersavailable(String clusterId) {
        test = extent.startTest("TC_CO_01.VerifyGroupByfiltersavailable("+clusterId+")", "Validate GroupBy filters available");
        test.assignCategory("4620 - Cluster / Job");
        Log.info("Start the test cases verifyQueryGraphForUserGroup");
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
        jobsPageObject.groupByQueueList.click();
        for (int i = 0; i < jobsPageObject.filterElements.size(); i++) {
            String queueName = jobsPageObject.filterElements.get(i).getText();
            executer.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + queueName);

        }
        executer.waitUntilElementClickable(jobsPageObject.groupByDropdownButton);
        executer.sleep(3000);
        jobsPageObject.groupByDropdownButton.click();
        jobsPageObject.groupByState.click();
        for (int i = 0; i < jobsPageObject.filterElements.size(); i++) {
            String StateName = jobsPageObject.filterElements.get(i).getText();
            executer.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + StateName);

        }
        executer.waitUntilElementClickable(jobsPageObject.groupByDropdownButton);
        executer.sleep(3000);
        jobsPageObject.groupByDropdownButton.click();
        jobsPageObject.groupByUser.click();
        for (int i = 0; i < jobsPageObject.filterElements.size(); i++) {
            String StateUser = jobsPageObject.filterElements.get(i).getText();
            executer.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + StateUser);

        }
        executer.waitUntilElementClickable(jobsPageObject.groupByDropdownButton);
        executer.sleep(3000);
        jobsPageObject.groupByDropdownButton.click();
        jobsPageObject.groupByAppType.click();
        for (int i = 0; i < jobsPageObject.filterElements.size(); i++) {
            String StateAppType = jobsPageObject.filterElements.get(i).getText();
            executer.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Graph displayed the user based on filter for queue : " + StateAppType);

        }
    }
}

