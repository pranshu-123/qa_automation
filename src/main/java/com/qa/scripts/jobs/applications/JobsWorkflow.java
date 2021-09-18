package com.qa.scripts.jobs.applications;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.JobsWorkflowPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;

/**
 * @author Ojasvi Pandey This class contains all actions or activity perform on
 *         Jobs Page.
 */
public class JobsWorkflow {
    private static final Logger LOGGER = Logger.getLogger(JobsWorkflow.class.getName());
    private TopPanelPageObject topPanelPageObject;
    private SubTopPanelModulePageObject subTopPanelModulePageObject;
    private WebDriver driver;
    private UserActions userActions;
    private WaitExecuter waitExecutor;
    private JobsWorkflowPageObject workflow;
    private Actions action;
    private JavascriptExecutor executor;
    private DatePicker datePicker;

    /**
     * @param driver WebDriver instance
     */
    public JobsWorkflow(WebDriver driver) {
        this.driver = driver;
        topPanelPageObject = new TopPanelPageObject(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        userActions = new UserActions(driver);
        waitExecutor = new WaitExecuter(driver);
        workflow = new JobsWorkflowPageObject(driver);
        executor = (JavascriptExecutor) driver;
        action = new Actions(driver);
        datePicker = new DatePicker(driver);
    }

    /**
     * Click on Jobs Pipeline page Actions Click On Cluster > Jobs > Pipelines
     */
    public void clickOnJobsPipelineTab() {
        waitExecutor.waitUntilElementClickable(topPanelPageObject.jobsTab);
        userActions.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        waitExecutor.waitUntilElementClickable(workflow.resetButton);
        userActions.performActionWithPolling(subTopPanelModulePageObject.jobsPipelinesTab, UserAction.CLICK);
        waitExecutor.waitUntilElementClickable(workflow.globalSearch);
    }

    /* Navigate to pipeline tab */
    public void navigateToPipelinesTab() {
        LOGGER.info("Navigating to Pipelines tab of Jobs Page.");
        userActions.performActionWithPolling(subTopPanelModulePageObject.jobsPipelinesTab, UserAction.CLICK);
        waitExecutor.waitUntilElementPresent(workflow.searchBoxWorkflow);
    }

    /* Search workflow name to select */
    public void searchWorkflowName(String pipelineName) {
        LOGGER.info("Search for the workflow");
        userActions.performActionWithPolling(workflow.searchBoxWorkflow, UserAction.CLICK);
        userActions.performActionWithPolling(workflow.searchBoxWorkflow, UserAction.SEND_KEYS, pipelineName);
        action.sendKeys(Keys.ENTER);
        if (workflow.pipelineTablePresent.size() != 0) {
            waitExecutor.sleep(2000);
            MouseActions.clickOnElement(driver, workflow.getUserFromTable);
            waitExecutor.waitUntilPageFullyLoaded();
            LOGGER.info("Click on first workflow of the searched pipelines.");
            action.moveToElement(workflow.getUserFromTable).perform();
            waitExecutor.sleep(4000);
        } else {
            workflow.noDataForWorkflows.isDisplayed();
            LOGGER.info("There is no data for workflow searched.");
        }

    }

    /* Scroll to top of the page */
    public void scrollToTopOfThePage() {
        LOGGER.info("Navigate to top of the page.");
        executor.executeScript("arguments[0].scrollIntoView();", workflow.jobsTab);
    }

    /* Search JobId and get WFLId through that */
    public Set<String> searchByJobIDGetWFIId(List<String> jobIds) {
        LOGGER.info("Navigate to application tab of Jobs page.");
        userActions.performActionWithPolling(workflow.applicationTab, UserAction.CLICK);
        Set<String> WFLIds = new HashSet<String>();
        for (String jobId : jobIds) {
            LOGGER.info("Click on global search and search jobid - " + jobId);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            workflow.globalSearch.clear();
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.SEND_KEYS, jobId);
            workflow.globalSearch.sendKeys(Keys.ENTER);
            LOGGER.info("Click on WFL link of job id.");
            selectWorkflowLink();
            waitExecutor.waitUntilElementClickable(workflow.flippedButton);
            LOGGER.info("Get the workflow id from the header of the page.");
            userActions.performActionWithPolling(workflow.ganttChartTab, UserAction.CLICK);
            WFLIds.add(workflow.getWorkflowHeaderID.getText().trim());
            LOGGER.info("Close the workflow details page.");
            userActions.performActionWithPolling(workflow.close, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            break;
        }
        return WFLIds;
    }

    /* Search JobId and get WFLId through that */
    public void searchByJobIDGetWFIIdHive(List<String> jobIds) {
        LOGGER.info("Navigate to application tab of Jobs page.");
        userActions.performActionWithPolling(workflow.applicationTab, UserAction.CLICK);
        for (String jobId : jobIds) {
            LOGGER.info("Click on global search and search jobid - " + jobId);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            waitExecutor.sleep(1000);
            workflow.globalSearch.clear();
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.SEND_KEYS, jobId);
            workflow.globalSearch.sendKeys(Keys.ENTER);
            LOGGER.info("Click on WFL link of job id.");
            selectWorkflowLink();
            LOGGER.info("Close the workflow details page.");
            waitExecutor.waitUntilElementClickable(workflow.close);
            userActions.performActionWithPolling(workflow.close, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            break;
        }
    }

    /* Get oozie node Job Ids from Ganntt Chart */
    public List<String> getJobIds() {
        List<String> jobIdNames = new ArrayList<>();
        userActions.performActionWithPolling(workflow.ganttChartTab, UserAction.CLICK);
        userActions.performActionWithPolling(workflow.oozienodeGanntChartDropdown, UserAction.CLICK);
        List<WebElement> getIds = workflow.getJobId;
        LOGGER.info("Get all the job ids under MR tag of Gantt chart.");
        for (WebElement ID : getIds) {
            jobIdNames.add(ID.getAttribute("content").toString().trim());
        }
        return jobIdNames;
    }

    /* Get tagged node Job Ids from Ganntt Chart */
    public List<String> getJobIdsFromParentTag() {
        userActions.performActionWithPolling(workflow.ganttChartTab, UserAction.CLICK);
        String getId = workflow.getParentJobId.getAttribute("content").trim();
        List<String> jobIds = new ArrayList<>();
        jobIds.add(getId);
        LOGGER.info("Get all the job ids under tag of Gantt chart- " + jobIds);
        return jobIds;
    }

    /* Get workflow id from workflow details page */
    public String getWorkflowId() {
        LOGGER.info("Get the workflow id from the header of the page.");
        String WFLId = workflow.getWorkflowHeaderID.getText().trim();
        return WFLId;
    }

    /* Select last 7 days data to display for workflow */
    public void selectLast7Days() {
        datePicker.clickOnDatePicker();
        waitExecutor.waitUntilElementClickable(workflow.globalSearch);
        try {
            waitExecutor.sleep(3000);
            datePicker.selectLast90Days();
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
        } catch (NoSuchElementException ex) {
            workflow.noDataForWorkflows.isDisplayed();
        }
    }

    /* Close modal window */
    public void close() {
        LOGGER.info("Close the app details page and navigate to workflow home page.");
        MouseActions.clickOnElement(driver, workflow.close);
        waitExecutor.sleep(1000);
    }

    /* Validate KPI present */
    public List<String> isKPIsPresent() {
        List<WebElement> jobKPIsE = workflow.getJobKPI;
        List<String> jobKPIString = new ArrayList<>();
        for (WebElement kpi : jobKPIsE) {
            jobKPIString.add(kpi.getText().trim());
        }
        LOGGER.info("KPIs for workflow - " + jobKPIString);
        return jobKPIString;

    }

    /* Validate Job duration present */
    public List<String> isJobTimePresent() {
        List<WebElement> jobDurationE = workflow.getJobDuration;
        List<String> jobDurationString = new ArrayList<>();
        for (WebElement job : jobDurationE) {
            jobDurationString.add(job.getText().trim());
        }
        LOGGER.info("Job duration for workflow - " + jobDurationString);
        return jobDurationString;
    }

    /* Click on workflow link */
    public void selectWorkflowLink() {
        try {
            if (workflow.genericWFILink.size() > 0) {
                LOGGER.info("Clicked on WFL link");
                waitExecutor.waitUntilElementClickable(workflow.genericWFILink.get(0));
                userActions.performActionWithPolling(workflow.genericWFILink.get(0), UserAction.CLICK);
                LOGGER.info("Clicked on WFI link");
            } else if (workflow.genericHiveLink.size() > 0) {
                waitExecutor.waitUntilElementClickable(workflow.genericHiveLink.get(0));
                userActions.performActionWithPolling(workflow.genericHiveLink.get(0), UserAction.CLICK);
                LOGGER.info("Clicked on Hive link");
            }
        } catch (StaleElementReferenceException ex) {
            waitExecutor.waitUntilElementClickable(workflow.genericHiveLink.get(0));
            userActions.performActionWithPolling(workflow.genericHiveLink.get(0), UserAction.CLICK);
            LOGGER.info("Clicked on Hive link");
        } catch (AssertionError ex) {
            waitExecutor.waitUntilElementClickable(workflow.genericHiveLink.get(0));
            userActions.performActionWithPolling(workflow.genericHiveLink.get(0), UserAction.CLICK);
            LOGGER.info("Clicked on Hive link");
        } catch (NoSuchElementException ex) {
            waitExecutor.waitUntilElementClickable(workflow.genericHiveLink.get(0));
            userActions.performActionWithPolling(workflow.genericHiveLink.get(0), UserAction.CLICK);
            LOGGER.info("Clicked on Hive link");
        } catch (TimeoutException ex) {
            waitExecutor.waitUntilElementClickable(workflow.genericHiveLink.get(0));
            userActions.performActionWithPolling(workflow.genericHiveLink.get(0), UserAction.CLICK);
            LOGGER.info("Clicked on Hive link");
        }

    }

    /* Search by JobId without getting WorkflowId */
    public void searchByJobID(List<String> jobIds) {
        LOGGER.info("Navigate to application tab of Jobs page.");
        userActions.performActionWithPolling(workflow.applicationTab, UserAction.CLICK);
        for (String jobId : jobIds) {
            LOGGER.info("Click on global search and search jobid - " + jobId);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
            workflow.globalSearch.clear();
            userActions.performActionWithPolling(workflow.globalSearch, UserAction.SEND_KEYS, jobId);
            workflow.globalSearch.sendKeys(Keys.ENTER);
            LOGGER.info("Click on WFL link of job id.");
            selectWorkflowLink();
            waitExecutor.waitUntilElementClickable(workflow.flippedButton);
            LOGGER.info("Get the workflow id from the header of the page.");
            userActions.performActionWithPolling(workflow.ganttChartTab, UserAction.CLICK);
            LOGGER.info("Close the workflow details page.");
            userActions.performActionWithPolling(workflow.close, UserAction.CLICK);
            waitExecutor.waitUntilElementClickable(workflow.globalSearch);
        }
    }
}
