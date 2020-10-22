package com.qa.scripts.jobs.applications;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AllApps {
    private static final Logger LOGGER = Logger.getLogger(AllApps.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ApplicationsPageObject applicationsPageObject;
    private final Actions action;
    private final TopPanelComponentPageObject topPanelComponentPageObject;
    private final DatePicker datePicker;
    private final AllApps allApps;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public AllApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        action = new Actions(driver);
        topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        datePicker = new DatePicker(driver);
        allApps = new AllApps(driver);
        this.driver = driver;
    }

    /*Get all range from calendar */
    public List<String> getCalendarRanges() {
        List<WebElement> getCalendarRangeElements = applicationsPageObject.dateRanges;
        waitExecuter.sleep(1000);
        List<String> listOfCaledarRanges = new ArrayList<>();
        LOGGER.info("Total number of ranges in datepicker: " + getCalendarRangeElements.size());
        for (int i = 0; i < getCalendarRangeElements.size(); i++) {
            LOGGER.info("The range in the calendar " + getCalendarRangeElements.get(i).getText());
            listOfCaledarRanges.add(getCalendarRangeElements.get(i).getText());
        }
        return listOfCaledarRanges;
    }

    /* Select cluster from list */
    public void selectCluster(String clusterId) {
        LOGGER.info("Select Cluster: " + clusterId);
        removeClusterIfPresent();
        applicationsPageObject.clusterSearchBox.click();
        waitExecuter.sleep(1000);
        LOGGER.info("Search for cluster: " + clusterId);
        applicationsPageObject.clusterSearchBox.sendKeys(clusterId);
        waitExecuter.sleep(1000);
        applicationsPageObject.select1stCluster.click();
        waitExecuter.sleep(4000);

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

    /* Add all application count */
    public int addApplicationTypeCount() {
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
        List<Integer> listOfJobCounts = new ArrayList<>();
        int totalCount = 0;
        for (int i = 0; i < appJobCounts.size(); i++) {
            listOfJobCounts.add(Integer.parseInt(appJobCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim()));
        }
        for (int jobCount : listOfJobCounts) {
            totalCount += jobCount;
        }
        return totalCount;
    }

    /* De-Select all app types */
    public void deselectAllAppTypes() {
        List<WebElement> appTypes = applicationsPageObject.selectOneApplicationType;
        for (int i = 0; i < appTypes.size(); i++) {
            appTypes.get(i).click();
        }
        waitExecuter.sleep(1000);
        // Assert if the application type is selected successfully.
        Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                "After de-selecting all application types, the application are still displayed ");
    }

    /* De-Select all status types */
    public void deselectAllStatusTypes() {
        List<WebElement> statusTypes = applicationsPageObject.selectSingleStatusType;
        for (int i = 0; i < statusTypes.size(); i++) {
            statusTypes.get(i).click();
        }
        waitExecuter.sleep(1000);
        // Assert if the application type is selected successfully.
        Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                "After de-selecting all application types, the application are still displayed ");
    }

    /* Slide the slider */
    public void moveTheSlider(WebElement sliderElement, int width) {
        action.dragAndDropBy(sliderElement, width, 0).click();
        action.build().perform();
    }

    /* Navigate to Jobs Tab */
    public void navigateToJobsTab() {
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.jobs);
        waitExecuter.sleep(1000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Select last 7 days from date range */
    public void select7Days(){
        LOGGER.info("Select last 7 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast7Days();
        waitExecuter.sleep(2000);
    }

    /* Select cluster and Last 7 days */
    public void inJobsSelectClusterAndLast7Days(String clusterId){
        // Navigate to Jobs tab from header
        allApps.navigateToJobsTab();
        // Select last 7 days from date picker
        allApps.select7Days();
        // Select cluster
        LOGGER.info("Select clusterId : " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
    }
}
