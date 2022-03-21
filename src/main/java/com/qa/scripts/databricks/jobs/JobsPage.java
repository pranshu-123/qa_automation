package com.qa.scripts.databricks.jobs;

import com.qa.pagefactory.databricks.DbxSubTopPanelModulePageObject;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxJobsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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

    private final List<String> durationTooltipValues;


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
        durationTooltipValues = new ArrayList<String>();
    }

    public List<String> getDurationTooltipValues() {
        return durationTooltipValues;
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
        String status = jobsPage.appStatus.getText().trim();
        Assert.assertEquals(statusTable, status, "Runs Status is not displayed in the Header");
        return status;
    }


    /**
     * Method to click on jobs Id , navigate to the details page.
     * and verify jobs Id details Page .
     */
    public String verifyJobId(DbxJobsPageObject jobsPage) {
        String jobsIdTable = jobsPage.jobId.getText();
        logger.info("Application Id is " + jobsIdTable);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnJobId);
        jobsPage.clickOnJobId.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsIdAppPage = jobsPage.appJobId.getText().trim();
        Assert.assertNotSame(jobsIdTable, jobsIdAppPage, "Runs Id is not displayed in the Header");
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
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String jobsNameAppPage = jobsPage.appJobName.getText().trim();
        Assert.assertNotSame(jobsName, jobsNameAppPage, "Runs Id is not displayed in the Header");
        return jobsNameAppPage;
    }

    /**
     * Method to click on Cluster Name , navigate to the details page.
     * and verify Cluster Name details Page .
     */
    public String verifyClusterName(DbxJobsPageObject jobsPage) {
        String clusterName = jobsPage.clusterName.getText();
        logger.info("Application Cluster Name is " + clusterName);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String clusterNameAppPage = jobsPage.appclusterName.getText().trim();
        Assert.assertNotSame(clusterName, clusterNameAppPage, "Cluster Name is not displayed in the Header");
        return clusterNameAppPage;
    }


    /**
     * Method to click on Work Space , navigate to the details page.
     * and verify Work Space details Page .
     */
    public String verifyWorkSpace(DbxJobsPageObject jobsPage) {
        String workSpace = jobsPage.workSpace.getText();
        logger.info("Application WorkSpace is " + workSpace);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String workSpaceAppPage = jobsPage.appWorkSpace.getText().trim();
        Assert.assertNotSame(workSpace, workSpaceAppPage, "WorkSpace is not displayed in the Header");
        return workSpaceAppPage;
    }

    /**
     * Method to click on Work Space , navigate to the details page.
     * and verify Work Space details Page .
     */
    public String verifyUser(DbxJobsPageObject jobsPage) {
        String user = jobsPage.userName.getText();
        logger.info("Application User Name is " + user);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String userNameAppPage = jobsPage.appUserName.getText().trim();
        Assert.assertNotSame(user, userNameAppPage, "User Name is not displayed in the Header");
        return userNameAppPage;
    }

    /**
     * Method to click on Duration , navigate to the details page.
     * and verify Duration details Page .
     */
    public String verifyDuration(DbxJobsPageObject jobsPage) {
        String duration = jobsPage.duration.getText();
        logger.info("Application Duration Name is " + duration);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String durationAppPage = jobsPage.appDuration.getText().trim();
        Assert.assertNotSame(duration, durationAppPage, "duration is not displayed in the Header");
        return durationAppPage;
    }

    public void VerifyJobsName(DbxJobsPageObject jobsPage, String tableValue, ExtentTest test) {
        try {
            if (tableValue.equalsIgnoreCase("JobsName")) {
                String jobsName = jobsPage.jobName.getText();
                logger.info("Application Id is " + jobsName);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                waitExecuter.waitUntilPageFullyLoaded();
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String jobsNameAppPage = jobsPage.appJobName.getText().trim();
                Assert.assertNotSame(jobsName, jobsNameAppPage, "RunsId is not displayed in the Header");
                test.log(LogStatus.PASS, "JobsName is displayed in the Header: " + jobsNameAppPage);
            } else if (tableValue.equalsIgnoreCase("ClusterName")) {
                String clusterName = jobsPage.clusterName.getText();
                logger.info("Application Cluster Name is " + clusterName);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilPageFullyLoaded();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String clusterNameAppPage = jobsPage.appclusterName.getText().trim();
                Assert.assertNotSame(clusterName, clusterNameAppPage, "Cluster Name is not displayed in the Header");
                test.log(LogStatus.PASS, "JobName is displayed in the Header: " + clusterNameAppPage);
            } else if (tableValue.equalsIgnoreCase("WorkSpaceName")) {
                String workSpace = jobsPage.workSpace.getText();
                logger.info("Application WorkSpace is " + workSpace);
                waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
                jobsPage.clickOnName.click();
                waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
                waitExecuter.waitUntilPageFullyLoaded();
                String workSpaceAppPage = jobsPage.appWorkSpace.getText().trim();
                Assert.assertNotSame(workSpace, workSpaceAppPage, "WorkSpace is not displayed in the Header");
                test.log(LogStatus.PASS, "JobName is displayed in the Header: " + workSpaceAppPage);
            }
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to click on App details , navigate to the details page.
     * and verify Start Time details Page .
     */
    public String verifyStartTime(DbxJobsPageObject jobsPage) {
        String startTime = jobsPage.startTime.getText();
        logger.info("Application Start Name is " + startTime);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String startTimeAppPage = jobsPage.appStartTime.getText().trim();
        Assert.assertNotSame(startTime, startTimeAppPage, "Start Name is not displayed in the Header");
        return startTimeAppPage;
    }

    /**
     * Method to click on App details , navigate to the details page.
     * and verify Start Time details Page .
     */
    public String verifyRunCount(DbxJobsPageObject jobsPage) {
        String user = jobsPage.runCount.getText();
        logger.info("Application Run Count is " + user);
        waitExecuter.waitUntilElementClickable(jobsPage.clickOnName);
        waitExecuter.waitUntilPageFullyLoaded();
        jobsPage.clickOnName.click();
        waitExecuter.waitUntilElementClickable(jobsPage.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String clusterNameAppPage = jobsPage.appRunCount.getText().trim();
        Assert.assertNotSame(user, clusterNameAppPage, "Run Count is not displayed in the Header");
        return clusterNameAppPage;
    }
}

