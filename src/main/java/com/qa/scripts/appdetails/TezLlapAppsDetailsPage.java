package com.qa.scripts.appdetails;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.TezLlapAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TezLlapAppsDetailsPage {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TezAppsDetailsPage.class.getName());
    private static Boolean isDignosticWin = false;
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private UserActions actions;
    private TezLlapAppsDetailsPageObject tezLlapPage;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public TezLlapAppsDetailsPage(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
    }


    /**
     * Method to validate the header app details page and DAG.
     *
     * @return
     */
    public void validateHeaderTab(TezLlapAppsDetailsPageObject tezLlapPage, ExtentTest test) {
        String startTime = tezLlapPage.startTime.getText();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Startime is displayed in the Header: " + startTime);
        String endTime = tezLlapPage.EndTime.getText();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Endtime is displayed in the Header: " + endTime);
        String duration = tezLlapPage.Duration.getText();
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Duration is displayed in the Header: " + duration);
        LOGGER.info("Duration = " + duration + " JobId = " + " starttime = " + startTime + " EndTime = " + endTime + " DataIO = ");
        Assert.assertNotSame("", startTime, "Value for startTime missing");
        Assert.assertNotSame("", endTime, "Value for duration missing");
        Assert.assertNotSame("", duration, "Value for duration missing");

    }

    public void clickOnGroupBySearchBox() {
        JavaScriptExecuter.clickOnElement(driver, tezLlapPage.queueBySearchBox);
    }



    public void selectOptionsInGroupBy(String optionName) {
        // Get the list of webelements of queue options
        List<WebElement> listOfWebElemnts = tezLlapPage.listOfGroupByOptions;
        // Iterate Webelement list to get the value of each element
        for (int i = 0; i < listOfWebElemnts.size(); i++) {
            System.out.println(listOfWebElemnts.get(i).getText());
            if (listOfWebElemnts.get(i).getText().contains(optionName)) {
                listOfWebElemnts.get(i).click();
            }
        }
    }


    /**
     * Method to validate  top right of the app details page.
     *
     * @return
     */
    public void validateTopRightTab(TezLlapAppsDetailsPageObject tezLlapPage, ExtentTest test) {
        String Owner = tezLlapPage.Owner.getText();
        test.log(LogStatus.PASS, "Owner  is displayed in the Header: " + Owner);
        String Cluster = tezLlapPage.Cluster.getText();
        test.log(LogStatus.PASS, "Cluster  is displayed in the Header: " + Cluster);
        String Queue = tezLlapPage.Queue.getText();
        test.log(LogStatus.PASS, "Queue  is displayed in the Header: " + Queue);
        LOGGER.info("Owner = " + Owner + " Cluster = " + Cluster + " starttime = " + Queue + " Queue");
        Assert.assertNotSame("", Owner, "Value for Owner missing");
        Assert.assertNotSame("", Cluster, "Value for Cluster missing");
        Assert.assertNotSame("", Queue, "Value for Queue missing");

    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppId(TezLlapAppsDetailsPageObject TezLlapApps, ApplicationsPageObject appPageObj) {
        String appId = TezLlapApps.getAppId.getText().trim();
        LOGGER.info("Tez application Id is " + appId);
        appPageObj.getTypeFromTable.click();
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = TezLlapApps.getHeaderAppId.getText().trim();
        Assert.assertNotSame("", headerAppId, "Tez Application Id is not displayed in the Header");
        return headerAppId;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppStatus(TezLlapAppsDetailsPageObject tezLlapPage) {
        String Status = tezLlapPage.status.getText();
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Tez application Id is " + Status);
        Assert.assertNotSame("", Status, "Tez Application Id is not displayed in the Header");
        return Status;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyStatus(TezLlapAppsDetailsPageObject tezLlapPage, ApplicationsPageObject appPageObj) {
        String statusTable = tezLlapPage.status.getText();
        LOGGER.info("Tez application Id is " + statusTable);
        appPageObj.getTypeFromTable.click();
        waitExecuter.waitUntilPageFullyLoaded();
        String status = tezLlapPage.appStatus.getText();
        Assert.assertNotSame("", status, "Tez Status is not displayed in the Header");
        return status;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  start time .
     */
    public String verifyStartTime(TezLlapAppsDetailsPageObject tezLlapPage) {
        String typeStartTime = tezLlapPage.getstartTime.getText();
        LOGGER.info("Tez Status is " + typeStartTime);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typeStartTime, "Tez Start Time is not displayed in the Table");
        return typeStartTime;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  duration .
     */
    public String verifyDuration(TezLlapAppsDetailsPageObject tezLlapPage) {
        String typeDuration = tezLlapPage.getduration.getText();
        LOGGER.info("Tez Status is " + typeDuration);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", typeDuration, "TezLlap duration is not displayed in the Table");
        return typeDuration;
    }


    /**
     * Common steps to navigate to the Jobs page from header.
     * Clicks on jobs tab
     * Selects a specific cluster
     * Selects 90 days time interval
     * Deselsects all the selected apps from the left pane on jobs page.
     */
    public void navigateToJobsTabFromHeader(SubTopPanelModulePageObject topPanelObj, AllApps allApps,
                                            DatePicker datePicker, ApplicationsPageObject appPageObj, String clusterId) {
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
        waitExecuter.sleep(1000);
        topPanelObj.jobs.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);

        //Select cluster
        LOGGER.info("Select Cluster: " + clusterId);
        allApps.selectCluster(clusterId);
        waitExecuter.sleep(3000);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast90Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
    }


    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left pane
     *
     * @param types Types can be appType | status Type
     */
    public int clickOnlyLink(String types) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[2]"))).click().build().perform();
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]",
                "").trim());
        wait.pollingEvery(Duration.ofMillis(20));
        waitExecuter.waitUntilPageFullyLoaded();
        return appCount;
    }

    /* Select cluster from list */
    public void selectQueue(String queueName) {
        tezLlapPage.queueSearchBox.click();
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Search for Queue: " + queueName);
        tezLlapPage.queueSearchBox.sendKeys(queueName);
        waitExecuter.waitUntilPageFullyLoaded();
        tezLlapPage.select1stQueue.click();
        waitExecuter.waitUntilPageFullyLoaded();
    }


    /* Get all app types that have run in unravel UI */
    public List<String> getAllApplicationQueue() {
        List<WebElement> appTypes = tezLlapPage.getApplicationQueue;
        List<String> nameOfAppTypes = new ArrayList<>();
        for (WebElement appType : appTypes) {
            nameOfAppTypes.add(appType.getText().trim());
        }
        return nameOfAppTypes;
    }


    /***
     * Common actions listed in one method that does the following:
     * Navigate to Jobs tab from header
     * Verify that the left pane has Tez app
     * Get Job count of selected App click on it and go to apps details page
     * Verify specific summary tabs.
     * */
    public void commonTabValidation(ExtentTest test, String clusterId, String tabName, Logger logger, Boolean isFailedApp) {
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");

        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has Tez check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Tez check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        int totalTezAppCnt = tezDetailsPage.clickOnlyLink("Tez");
        if (totalTezAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            int appCount = 0;
            if (isFailedApp)
                appCount = tezDetailsPage.clickOnlyLink("Failed");
            else
                appCount = tezDetailsPage.clickOnlyLink("Success");
            //Clicking on the Tez app must go to apps detail page
            if (appCount > 0) {
                String headerAppId = tezDetailsPage.verifyAppId(tezApps, applicationsPageObject);
                test.log(LogStatus.PASS, "Tez Application Id is displayed in the Header: " + headerAppId);
                tezDetailsPage.verifyAppSummaryTabs(tezApps, tabName, test);
                //Close apps details page
                MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
            } else {
                test.log(LogStatus.SKIP, "No Tez Application present");
                logger.info("No Tez Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            logger.info("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
        }
    }

    /**
     * Verify that Left pane must be opened and should have KPIs listed
     * (start, end and duration are listed and should not be empty)
     */
    public void validateLeftPaneKpis(List<WebElement> kpiList) {
        Assert.assertFalse(kpiList.isEmpty(), "The kpi list is empty");
        for (WebElement webElement : kpiList) {
            LOGGER.info("The leftPane kpi is " + webElement.getText());
            String kpis = webElement.getText();
            Assert.assertNotSame("", kpis, "The kpis is empty");
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            LOGGER.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotSame("", kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotSame("", kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }

    /**
     * Method to verify the following:
     * 1.All the KPIs should be listed and the data must be populated.
     * (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis(TezLlapAppsDetailsPageObject tezLlapPage) {
        List<WebElement> kpiList = tezLlapPage.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = tezLlapPage.rightPaneAppKpis;
        List<WebElement> appKpiVal = tezLlapPage.rightPaneAppKpiVal;
        Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
        Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
        String appDuration = "0";
        for (int i = 0; i < appKpis.size(); i++) {
            Assert.assertNotSame("", appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotSame("", appKpiVal.get(i).getText(), "Kpi Value is empty");
            appDuration = (appKpiVal.get(0).getText().trim());
            LOGGER.info("Kpi Name = " + appKpis.get(i).getText() + " Value = " + appKpiVal.get(i).getText());
        }
        LOGGER.info("The application duration is " + appDuration);
        return appDuration;
    }
}