package com.qa.scripts.databricks.jobs;

import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class JobsPage {

        private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
        private final LoggingUtils loggingUtils = new LoggingUtils(com.qa.scripts.databricks.jobs.DbAllApps.class);
        private final WaitExecuter waitExecuter;
        private final WebDriver driver;
        private final DbxApplicationsPageObject applicationsPageObject;
        private final DbxJobsPageObject jobsPageObject;
        private final Actions action;
        private final UserActions userActions;
        private final DbxSubTopPanelModulePageObject dbSubTopPanelModulePageObject;
        private final DatePicker datePicker;
        private final UserActions userAction;

        /**
         * Constructer to initialize wait, driver and necessary objects
         *
         * @param driver - WebDriver instance
         */

        public JobsPage(WebDriver driver) {
            waitExecuter = new WaitExecuter(driver);
            applicationsPageObject = new DbxApplicationsPageObject(driver);
            action = new Actions(driver);
            userActions = new UserActions(driver);
            dbSubTopPanelModulePageObject = new DbxSubTopPanelModulePageObject(driver);
            datePicker = new DatePicker(driver);
            jobsPageObject = new DbxJobsPageObject(driver);
            userAction = new UserActions(driver);
            this.driver = driver;
        }

            /**
             * Method to click the first app in jobs table , navigate to the details page.
             * and verify Status App details Page .
             */
            public String verifyStatus(DbxJobsPageObject jobsPage) {
                String statusTable = jobsPage.status.getText();
                logger.info("Application Id is " + statusTable);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnStatus);
                jobsPage.clickOnStatus.click();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String status = jobsPage.appStatus.getText();
                Assert.assertNotSame(statusTable, status, "Runs Status is not displayed in the Header");
                return status;
            }


    /**
     * Method to click on jobs Id , navigate to the details page.
     * and verify jobs Id details Page .
     */
    public String verifyJobId(DbxJobsPageObject jobsPage) {
        String jobsTable = jobsPage.jobId.getText();
        logger.info("Application Id is " + jobsTable);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnJobId);
        jobsPage.clickOnJobId.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsIdAppPage = jobsPage.appJobId.getText().trim();
        Assert.assertNotSame(jobsTable, jobsIdAppPage, "Runs Id is not displayed in the Header");
        return jobsIdAppPage;
    }

    /**
     * Method to click on jobs Name , navigate to the details page.
     * and verify jobs Id details Page .
     */
    public String verifyJobName(DbxJobsPageObject jobsPage) {
        String jobsName = jobsPage.jobName.getText();
        logger.info("Application Id is " + jobsName);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsNameAppPage = jobsPage.appName.getText().trim();
        Assert.assertNotSame(jobsName, jobsNameAppPage, "Runs Id is not displayed in the Header");
        return jobsNameAppPage;
    }
}
