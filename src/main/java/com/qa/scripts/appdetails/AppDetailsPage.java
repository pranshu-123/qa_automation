package com.qa.scripts.appdetails;

import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.ApplicationEnum;
import com.qa.enums.ImpalaEventTypes;
import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.appsDetailsPage.AppDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ankur Jaiswal
 */
public class AppDetailsPage {
    private final JavascriptExecutor jse;
    private final Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private final WebDriver driver;
    private final AppDetailsPageObject appDetailsPageObject;
    private final UserActions userActions;
    private final WaitExecuter wait;
    private final TopPanelPageObject topPanelPageObject;
    private final ApplicationsPageObject applicationsPageObject;
    private final SparkAppsDetailsPage application;
    private final SubTopPanelModulePageObject subTopPanel;

    /**
     * Constructor method to initialize object fields
     *
     * @param driver - WebDriver instance
     */
    public AppDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitExecuter(driver);
        appDetailsPageObject = new AppDetailsPageObject(driver);
        userActions = new UserActions(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        application = new SparkAppsDetailsPage(driver);
        subTopPanel = new SubTopPanelModulePageObject(driver);
        jse = (JavascriptExecutor) driver;
    }

    /**
     * Select only application in left panel of application page. Click on only
     * button of the application passed as parameter
     *
     * @param applicationType - Application Type which we want to select
     */
    public int selectOnlyApplication(AppDetailsApplicationType applicationType) {
        wait.waitUntilElementClickable(appDetailsPageObject.applicationTypeLabels.get(0));
        int appCount = 0;
        for (int i = 0; i < appDetailsPageObject.applicationTypeLabels.size(); i++) {
            if (appDetailsPageObject.applicationTypeLabels.get(i).getText().contains(applicationType.getValue())) {
                wait.sleep(3000);
                String applicationAppText = appDetailsPageObject.applicationTypeLabels.get(i).getText();
                logger.info(applicationAppText);
                String[] appDetails = applicationAppText.split("\\(");
                logger.info("App details count- " + appDetails[1]);
                appCount = Integer.parseInt(appDetails[1].replaceAll("[^0-9]", "").trim());
                ActionPerformer actionPerformer = new ActionPerformer(driver);
                actionPerformer.moveToTheElement(appDetailsPageObject.applicationTypeLabels.get(i));
                String appId = appDetailsPageObject.applicationTypeShowOnly.getText().trim();
                logger.info("Impala Insight application Id is " + appId);
                wait.waitUntilElementClickable(applicationsPageObject.resetButton);
                break;
            }
        }
        return appCount;
    }

    /**
     * Check whether other application type is displayed on app Returns true if
     * other application type is displayed on table
     *
     * @param appDetailsApplicationType
     * @return
     */
    public Boolean IsOtherApplicationTypesInTable(AppDetailsApplicationType appDetailsApplicationType) {
        List<WebElement> tableRows = getAppsDetailsAsPage();
        Boolean isOtherApplicationPresent = false;
        for (WebElement row : tableRows) {
            if (!row.findElements(By.xpath("//td")).get(ApplicationEnum.TYPE.getIndex()).getText()
                    .equalsIgnoreCase(appDetailsApplicationType.getValue())) {
                isOtherApplicationPresent = true;
            }
        }
        return isOtherApplicationPresent;
    }

    /**
     * Get table rows displayed on app details page
     */
    public List<WebElement> getAppsDetailsAsPage() {
        return appDetailsPageObject.allAppsTable.findElements(By.xpath("//tr"));
    }

    /**
     * Sort the table column of jobs application page
     *
     * @param applicationEnum - Column to sort
     */
    public void sortColumnAllAppsTable(ApplicationEnum applicationEnum) {
        List<WebElement> tableHeadings = appDetailsPageObject.allAppsTable.findElements(By.xpath("//thead"));
        for (WebElement tableHeading : tableHeadings) {
            userActions.performActionWithPolling(
                    tableHeading.findElements(By.xpath("//th")).get(applicationEnum.getIndex()), UserAction.CLICK);
        }
    }

    /**
     * Get status of application from job details
     */
    public String getJobStatus() {
        return appDetailsPageObject.jobStatus.getText();
    }

    /**
     * Get start date and time of application from job details
     */
    public String getJobStartTime() {
        return appDetailsPageObject.jobStartTime.getText();
    }

    /**
     * Get end date and time of application from job details
     */
    public String getJobEndTime() {
        return appDetailsPageObject.jobEndTime.getText();
    }

    /**
     * Get job duration of application from job details
     */
    public String getJobDuration() {
        return appDetailsPageObject.jobDuration.getText();
    }

    /**
     * Get uuid of application from job details
     */
    public String getApplicationUUID() {
        return appDetailsPageObject.jobUUID.getText();
    }

    /**
     * Get type of application from job details
     */
    public String getApplicationType() {
        return appDetailsPageObject.appTypeAppDetails.getText();
    }

    /**
     * Get disk io of application from job details
     */
    public String getApplicationDataIO() {
        return appDetailsPageObject.jobReadWriteData.getText();
    }

    /**
     * Get fragment rows displayed on impala application from job details
     */
    public List<WebElement> getImpalaFragmentRows() {
        return appDetailsPageObject.impalaFragmentRows;
    }

    /**
     * Get query plan components displayed on page of impala app details from job
     * details
     */
    public List<WebElement> getImpalaQueryPlanDetails() {
        return appDetailsPageObject.impalaQueryPlanComponents;
    }

    /**
     * Get instance view displayed on page of impala app details from job details
     */
    public WebElement getImpalaInstanceView() {
        return appDetailsPageObject.impalaIFragmentnstanceView;
    }

    /**
     * Get instance view fragment rows displayed on impala application from job
     * details
     */
    public List<WebElement> getImpalaInstanceViewFragmentRows() {
        return appDetailsPageObject.impalaFragmentInstanceViewsRows;
    }

    /**
     * Click on operators tab on impala app details page
     */
    public void clickOnImpalaOperatorsTab() {
        userActions.performActionWithPolling(appDetailsPageObject.operatorsTabImpala, UserAction.CLICK);
    }

    /**
     * Click on gantt tab on impala app details page
     */
    public void clickOnImpalaGanttChartTab() {
        userActions.performActionWithPolling(appDetailsPageObject.ganttChartTabImpala, UserAction.CLICK);
    }

    /**
     * Get gantt chart ids in numeric
     */
    public List<Integer> getGanttChartIdsInNumeric() {
        List<Integer> fragmentIds = new ArrayList<>();
        for (WebElement fragmentId : appDetailsPageObject.ganttChartIds) {
            Integer fragId = Integer.valueOf(fragmentId.getText().replaceAll("[^0-9]+", ""));
            fragmentIds.add(fragId);
        }
        return fragmentIds;
    }

    /**
     * Close the modal
     */
    public void closeModalbutton() {
        userActions.performActionWithPolling(appDetailsPageObject.closeModalbutton, UserAction.CLICK);
    }

    /* Click on the first impala success job */
    public void clickOnFirstImpalaJob() {
        if (appDetailsPageObject.getImpalaJobs.size() > 0) {
            wait.waitUntilElementClickable(appDetailsPageObject.clickOnAppId);
            userActions.performActionWithPolling(appDetailsPageObject.clickOnAppId, UserAction.CLICK);
            wait.waitUntilElementClickable(appDetailsPageObject.impalaFragmentTab);
        }
    }

    /* Get number of fragments of Impala application */
    public int getFragmentCount() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaFragmentTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaFragmentTab, UserAction.CLICK);
        int numFragments = 0;
        try {
            numFragments = appDetailsPageObject.getFragmentIdRows.size();
        } catch (NoSuchElementException ex) {
            logger.info("There are no fragment ids in Fragment tab");
        }
        logger.info("Number of operators from Fragment View- " + numFragments);
        return numFragments;
    }

    /* Get number of Operators of Impala application */
    public int getOperatorCount() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaOperatorsTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaOperatorsTab, UserAction.CLICK);
        int numOperators = 0;
        try {
            numOperators = appDetailsPageObject.getOperatorTypesRow.size();
        } catch (NoSuchElementException ex) {
            logger.info("There are no Operators in Operator View");
        }
        logger.info("Number of operators from Operator View- " + numOperators);
        return numOperators;
    }

    /* Get number of fragments of Impala from Query Plan tab for application */
    public int getQueryPlanFragmentCount() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaQueryPlanTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaQueryPlanTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.queryPlanFragmentTab);
        userActions.performActionWithPolling(appDetailsPageObject.queryPlanFragmentTab, UserAction.CLICK);
        int numFragments = 0;
        try {
            numFragments = appDetailsPageObject.getFragmentsFromQueryPlan.size();

        } catch (NoSuchElementException ex) {
            logger.info("There are no fragment rows in Query Plan / Fragment View");
        }
        logger.info("Number of fragments from Query Plan- " + numFragments);
        return numFragments;
    }

    /* Get number of Operators of Impala from Query Plan tab for application */
    public int getQueryPlanOperatorCount() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaQueryPlanTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaQueryPlanTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.queryPlanFragmentTab);
        userActions.performActionWithPolling(appDetailsPageObject.queryPlanFragmentTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.queryPlanFragmentTab);
        wait.sleep(1000);
        int numOperators = 0;
        try {
            numOperators = appDetailsPageObject.getOperatorsFromQueryPlan.size();
        } catch (NoSuchElementException ex) {
            logger.info("There are no Operators in Query Plan / Fragment View");
        }
        logger.info("Number of operators from Query Plan- " + numOperators);
        return numOperators;
    }

    /* Get number of Operators of Impala from Query Plan tab for application */
    public int getQueryPlanOperatorViewCount() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaQueryPlanTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaQueryPlanTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.queryPlanOperatorTab);
        userActions.performActionWithPolling(appDetailsPageObject.queryPlanOperatorTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.queryPlanOperatorTab);
        wait.sleep(1000);
        int numOperators = 0;
        try {
            numOperators = appDetailsPageObject.operatorViewOpCount.size();
        } catch (NoSuchElementException ex) {
            logger.info("There are no Operators in Query Plan / Fragment View");
        }
        logger.info("Number of operators from Query Plan- " + numOperators);
        return numOperators;
    }

    /* Click on Jobs tab after login */
    public void navigateToJobsTab() {
        wait.waitUntilElementClickable(topPanelPageObject.jobsTab);
        userActions.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        logger.info("Click on Job Tabs");
        wait.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /* Select only success application */
    public void selectSuccessfulApplication() {
        wait.waitUntilElementClickable(appDetailsPageObject.statusToggleLeftpane);
        userActions.performActionWithPolling(appDetailsPageObject.statusToggleLeftpane, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.statusList.get(0));
        application.clickOnlyLink("Success");
        wait.waitUntilElementClickable(appDetailsPageObject.resetButton);
    }

    /* Click on reset button */
    public void reset() {
        wait.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        wait.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /* Close App details page */
    public void close() {
        if (appDetailsPageObject.closeIcon.size() > 0) {
            wait.waitUntilElementClickable(appDetailsPageObject.closeIcon.get(0));
            userActions.performActionWithPolling(appDetailsPageObject.closeIcon.get(0), UserAction.CLICK);
            wait.waitUntilElementClickable(applicationsPageObject.globalSearchBox);
        }
    }

    /* Get tags table data */
    public Map<String, String> getImpalaTags() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaTagsTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaTagsTab, UserAction.CLICK);
        List<WebElement> key = appDetailsPageObject.getTagNames;
        List<WebElement> values = appDetailsPageObject.getTagDescription;
        HashMap<String, String> tagsMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            tagsMap.put(key.get(i).getText().trim(), values.get(i).getText().trim());
        }
        wait.sleep(1000);
        return tagsMap;
    }

    /* Get all application details from allApps table for the first app */
    public List<String> getApplicationDetails() {
        List<WebElement> appDetailWebElements = appDetailsPageObject.getTableValuesOfImpalaApp;
        List<String> appDetailsList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            appDetailsList.add(appDetailWebElements.get(i).getText().trim());
        }
        return appDetailsList;
    }

    /* Get cluster name of first app */
    public String getClusterName() {
        String clusterName = appDetailsPageObject.getTableValuesOfImpalaApp.get(4).getAttribute("title").trim();
        String[] nameOfCluster = clusterName.split("\\[");
        return nameOfCluster[0];
    }

    /* Get app count from impala chargeback */
    public int getAppCountImpalaChargeback(String clusterId) {
        wait.waitUntilElementClickable(topPanelPageObject.clusterTab);
        userActions.performActionWithPolling(topPanelPageObject.clusterTab, UserAction.CLICK);
        logger.info("Click on Clusters Tabs");
        wait.waitUntilElementClickable(subTopPanel.chargeback);
        userActions.performActionWithPolling(subTopPanel.chargeback, UserAction.CLICK);
        logger.info("Click on chargeback drop button");
        wait.waitUntilElementClickable(appDetailsPageObject.chargebackDropdown);
        userActions.performActionWithPolling(appDetailsPageObject.chargebackDropdown, UserAction.CLICK);
        logger.info("Select impala chargeback");
        wait.waitUntilElementClickable(appDetailsPageObject.impalaChargeback);
        userActions.performActionWithPolling(appDetailsPageObject.impalaChargeback, UserAction.CLICK);
        selectClusterInChargeback(clusterId);
        int impalaCBJobCount = Integer
                .parseInt(appDetailsPageObject.impalaChargebackJobCount.getText().trim().replaceAll("[^0-9]", ""));
        return impalaCBJobCount;
    }

    /* Select cluster in imapla chargeback page */
    public void selectClusterInChargeback(String clusterId) {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaClusterDrodown);
        userActions.performActionWithPolling(appDetailsPageObject.impalaClusterDrodown, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.clusterSearchBox);
        userActions.performActionWithPolling(appDetailsPageObject.clusterSearchBox, UserAction.CLICK);
        userActions.performActionWithPolling(appDetailsPageObject.clusterSearchBox, UserAction.SEND_KEYS, clusterId);
        wait.waitUntilElementClickable(appDetailsPageObject.select1stOption);
        userActions.performActionWithPolling(appDetailsPageObject.select1stOption, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.impalaClusterDrodown);
    }

    /* Refresh page for 6 mins to get running impala job */
    public void runningApps() {
        wait.waitUntilElementClickable(appDetailsPageObject.runningAppsTab);
        userActions.performActionWithPolling(appDetailsPageObject.runningAppsTab, UserAction.CLICK);
        selectOnlyApplication(AppDetailsApplicationType.IMPALA);
        Boolean runningAppFound = false;
        while (runningAppFound == false) {
            int count = selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            if (count > 0) {
                runningAppFound = true;
                logger.info("Running app found for impala");
            } else
                logger.info("Running app not found for impala");
        }
    }

    /* To zoom out of the page by defining percentage of zoom out */
    public void zoomOut(String percentage) {
        jse.executeScript("document.body.style.zoom = " + "'" + percentage + "%';");

    }

    /* Move to Inefficient apps */
    public void navigateToInefficientApps() {
        wait.waitUntilElementClickable(appDetailsPageObject.inefficientAppsTab);
        userActions.performActionWithPolling(appDetailsPageObject.inefficientAppsTab, UserAction.CLICK);
        wait.waitUntilElementClickable(appDetailsPageObject.eventToggleLeftPane);
    }

    /* Select filter in UI */
    public void selectEventFilter(ImpalaEventTypes eventType) {
        wait.waitUntilElementClickable(appDetailsPageObject.eventToggleLeftPane);
        userActions.performActionWithPolling(appDetailsPageObject.eventToggleLeftPane, UserAction.CLICK);
        for (int i = 0; i < appDetailsPageObject.eventTypeLabels.size(); i++) {
            if (appDetailsPageObject.eventTypeLabels.get(i).getAttribute("content").contains(eventType.getValue())) {
                ActionPerformer actionPerformer = new ActionPerformer(driver);
                actionPerformer.moveToTheElement(appDetailsPageObject.eventTypeLabels.get(i));
                userActions.performActionWithPolling(appDetailsPageObject.eventTypeShowOnly.get(i),
                        UserAction.CLICK);
            }
        }
    }

    /* Get all efficiency tags from app details */
    public List<String> getEfficiencyTags() {
        wait.waitUntilElementClickable(appDetailsPageObject.impalaAnalysisTab);
        userActions.performActionWithPolling(appDetailsPageObject.impalaAnalysisTab, UserAction.CLICK);
        List<WebElement> efficiencyTags = appDetailsPageObject.getEfficiencyTitleString;
        List<String> efficiencyTitleString = new ArrayList<>();
        for (WebElement tags : efficiencyTags) {
            efficiencyTitleString.add(tags.getText().trim());
        }
        wait.sleep(1000);
        return efficiencyTitleString;
    }

    /* Click on the first impala job in in-efficient tab */
    public void clickOnFirstInefficientJob() {
        if (appDetailsPageObject.getImpalaInefficientJobs.size() > 0) {
            wait.waitUntilElementClickable(appDetailsPageObject.firstInefficientRow);
            userActions.performActionWithPolling(appDetailsPageObject.firstInefficientRow, UserAction.CLICK);
            wait.waitUntilElementClickable(appDetailsPageObject.impalaAnalysisTab);
        }
    }

    /*Search for the app Id*/
    public void searchByAppID(String app) {
        wait.waitUntilElementClickable(appDetailsPageObject.globalSearchBox);
        userActions.performActionWithPolling(appDetailsPageObject.globalSearchBox, UserAction.CLICK);
        userActions.performActionWithPolling(appDetailsPageObject.globalSearchBox, UserAction.SEND_KEYS, app);
        appDetailsPageObject.globalSearchBox.sendKeys(Keys.RETURN);
        wait.waitUntilElementClickable(appDetailsPageObject.globalSearchBox);
        if (appDetailsPageObject.clickFirstApp.size() > 0) {
            wait.waitUntilElementClickable(appDetailsPageObject.clickFirstApp.get(0));
            userActions.performActionWithPolling(appDetailsPageObject.clickFirstApp.get(0), UserAction.CLICK);
            wait.waitUntilPageFullyLoaded();
            wait.waitUntilElementClickable(appDetailsPageObject.flippedButton);
        } else
            logger.info("There are no application by name- " + app);
    }
}
