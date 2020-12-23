package com.qa.scripts.migration;

import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/*
@author - Ojasvi Pandey
This class contains all the action methods related to Cluster Discovery
 */
public class ClusterDiscovery {
    private static final Logger LOGGER = Logger.getLogger(ClusterDiscovery.class.getName());
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final TopPanelPageObject topPanelPageObject;
    private final UserActions userAction;
    private final SubTopPanelModulePageObject topPanelComponentPageObject;
    private final ClusterDiscoveryPageObject cdPageObject;
    private final ApplicationsPageObject applicationsPageObject;
    private final AllApps allApps;
    private final DatePicker datePicker;
    private final SparkAppsDetailsPage sparkApp;
    private final DatePickerPageObject datePickerPageObject;
    private final DatePicker date;
    private CommonPageObject commonPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public ClusterDiscovery(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        userAction = new UserActions(driver);
        topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        cdPageObject = new ClusterDiscoveryPageObject(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        allApps = new AllApps(driver);
        datePicker = new DatePicker(driver);
        sparkApp = new SparkAppsDetailsPage(driver);
        datePickerPageObject = new DatePickerPageObject(driver);
        date = new DatePicker(driver);
        commonPageObject = new CommonPageObject(driver);

    }

    /*
     * This method is to close the confirmation message that appears on landing of
     * page
     */
    public void closeConfirmationMessageNotification() {
        if (cdPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(cdPageObject.confirmationMessageElementClose.get(0));
            userAction.performActionWithPolling(cdPageObject.confirmationMessageElementClose.get(0), UserAction.CLICK);
        }
    }

    /* Method to select Cluster in Modal box */
    public void selectMultiClusterId(String clusterId) {
        waitExecuter.sleep(2000);
        commonPageObject = new CommonPageObject(driver);
        MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
        waitExecuter.sleep(2000);
        LOGGER.info("Size of cluster in dropdown: " + commonPageObject.clustersList.size());
        waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
        userAction.performActionWithPolling(commonPageObject.clusterSearchBox,
                UserAction.SEND_KEYS, clusterId);
        userAction.performActionWithPolling(commonPageObject.clusterSearchFirstField,
                UserAction.CLICK);
        waitExecuter.sleep(4000);
    }

    /* Navigate to Cluster Discovery Tab */
    public void navigateToClusterDiscovery() {
        LOGGER.info("Navigate to Cluster Discovery tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.migrationTab);
        userAction.performActionWithPolling(topPanelPageObject.migrationTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.clusterDiscoveryTab);
        // waitExecuter.sleep(4000);
        // Click on Cluster Discovery tab
        LOGGER.info("Clicked on Cluster Discovery tab");
        userAction.performActionWithPolling(topPanelComponentPageObject.clusterDiscoveryTab, UserAction.CLICK);
        // Validate Cluster Discovery tab loaded successfully
        LOGGER.info("Validate Cluster Discovery tab loaded successfully");
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Navigate to Jobs tab */
    public void navigateToJobs() {
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        userAction.performActionWithPolling(topPanelComponentPageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Click on Run button of modal window */
    public void clickRunModalButton() {
        LOGGER.info("Click on Run button of modal window");
        userAction.performActionWithPolling(cdPageObject.modalRunButton, UserAction.CLICK);
        waitExecuter.waitUntilElementPresent(cdPageObject.runButton);
        waitExecuter.waitUntilElementClickable(cdPageObject.runButton);
    }

    /* Check and remove cluster from searchbox */
    public void removeClusterIfPresent() {
        if (applicationsPageObject.removeCluster != null) {
            applicationsPageObject.clusterSearchBox.sendKeys(Keys.BACK_SPACE);
            waitExecuter.sleep(1000);
            applicationsPageObject.clusterSearchBox.clear();
            waitExecuter.sleep(1000);
        } else
            LOGGER.info("No cluster to remove");
    }

    /* Check of pie charts are present */
    public boolean checkPieIsPresent(List<WebElement> pieType) {

        return pieType.size() > 0;
    }

    /* To create a new report click on Run button */
    public void clickRunForNewReport() {
        LOGGER.info("Click on Run button to open report page");
        userAction.performActionWithPolling(cdPageObject.runButton, UserAction.CLICK);
        waitExecuter.sleep(1000);
    }

    /* Get names of pie chart options */
    public List<String> getPieChartOptions(List<WebElement> pieChartOptions) {
        List<String> nameArray = new ArrayList<>();
        for (WebElement options : pieChartOptions) {
            nameArray.add(options.getText().trim().toLowerCase());
        }
        return nameArray;
    }

    /* Select last 30 days on date picker */
    public void selectLast30Days() {
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.sleep(2000);
    }

    /*Get jobs count from jobs page of spark, MR and tez*/
    public int getJobsCount() {
        waitExecuter.sleep(2000);
        int hiveapp = sparkApp.clickOnlyLink("Tez");
        LOGGER.info("Hive apps -- " + hiveapp);
        waitExecuter.sleep(2000);
        int MRapps = sparkApp.clickOnlyLink("Map Reduce");
        LOGGER.info("Hive apps -- " + MRapps);
        waitExecuter.sleep(2000);
        int sparkApps = sparkApp.clickOnlyLink("Spark");
        LOGGER.info("Hive apps -- " + sparkApps);
        int totalJobs = hiveapp + MRapps + sparkApps;
        return totalJobs;
    }

    /*Set custom date*/
    public void setCustomRange(String startDate, String endDate) {
        LOGGER.info("Click on date range");
        userAction.performActionWithPolling(datePickerPageObject.dateRange, UserAction.CLICK);
        date.selectCustomRange();
        date.setStartDate(startDate);
        waitExecuter.sleep(1000);
        date.setEndDate(endDate);
        waitExecuter.sleep(1000);
        date.clickOnCustomDateApplyBtn();
        waitExecuter.sleep(1000);
    }

}