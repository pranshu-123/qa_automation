package com.qa.scripts.appdetails;

import com.qa.pagefactory.SubTopPanelModulePageObject;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class TezLlapAppsDetailsPage {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(TezAppsDetailsPage.class.getName());
    private static final Boolean isDignosticWin = false;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
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
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify status .
     */
    public String verifyAppStatus(TezLlapAppsDetailsPageObject tezLlapPage) {
        String user = tezLlapPage.Status.getText();
        waitExecuter.sleep(5000);
        LOGGER.info("Tez application status is " + user);
        Assert.assertNotSame("", user, "Tez application status is not displayed in the Header");
        return user;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify status .
     */
    public String verifyAppUser(TezLlapAppsDetailsPageObject tezLlapPage) {
        String Status = tezLlapPage.user.getText();
        waitExecuter.sleep(5000);
        LOGGER.info("Tez application status is " + Status);
        Assert.assertNotSame("", Status, "Tez application status is not displayed in the Header");
        return Status;
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
     * and verify  clusterId .
     */
    public String verifyclusterId(TezLlapAppsDetailsPageObject tezLlapPage) {
        WebElement Appid = tezLlapPage.getClusterId;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppnametoolTip = tezLlapPage.getClusterId;
        String AppIdText = AppnametoolTip.getText().trim();
        LOGGER.info("Tez llap Status is " + Appid);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appid, "Tezllap App id name is not displayed in the Table");
        return AppIdText;
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appname .
     */
    public String verifyAppname(TezLlapAppsDetailsPageObject tezLlapPage) {
        WebElement Appname = tezLlapPage.getAppname;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appname).build().perform();
        WebElement AppnametoolTip = driver.findElement(By.xpath("//*[@id=\"allApps-body\"]/tr[1]/td[4]"));
        waitExecuter.sleep(3000);
        String AppnameText = AppnametoolTip.getText().trim();
        System.out.println(AppnameText);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appname, "Tez App name is not displayed in the Table");
        return AppnameText;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appid .
     */
    public String verifyappId(TezLlapAppsDetailsPageObject tezLlapPage, ApplicationsPageObject appPageObj) {
        WebElement Appid = tezLlapPage.getAppid;
        Actions toolAct = new Actions(driver);
        toolAct.moveToElement(Appid).build().perform();
        WebElement AppnametoolTip = driver.findElement(By.xpath("//*[@id=\"allApps-body\"]/tr[1]/td[4]/div"));
        waitExecuter.sleep(3000);
        String AppIdText = AppnametoolTip.getText().trim();
        LOGGER.info("Tez Status is " + Appid);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", Appid, "Tez App id name is not displayed in the Table");
        return AppIdText;
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
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Read IO .
     */
    public String verifyRead(TezLlapAppsDetailsPageObject tezLlapPage) {
        String ReadIO = tezLlapPage.getRead.getText();
        LOGGER.info("Tez Status is " + ReadIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", ReadIO, "Tez User name is not displayed in the Table");
        return ReadIO;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Parent App
     */
    public String verifyParentApp(TezLlapAppsDetailsPageObject tezLlapPage) {
        String ParentApp = tezLlapPage.getParentApp.getText();
        LOGGER.info("Hive-Tez LLAP Status is " + ParentApp);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        tezLlapPage.getParentApp.click();
        Assert.assertNotSame("", ParentApp, "Hive-Tez LLAP Parent App is not displayed in the Table");
        return ParentApp;
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Parent App
     */
    public String verifyQueueName(TezLlapAppsDetailsPageObject tezLlapPage) {
        String queueName = tezLlapPage.getQueue.getText();
        LOGGER.info("Hive-Tez LLAP Status is " + queueName);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", queueName, "Hive-Tez LLAP Parent App is not displayed in the Table");
        return queueName;
    }


    /***
     * Common Queue dropdown:
     * * */
    public void commonQueueValidation(ExtentTest test, Logger logger, String upTo10CharQueueName) {

        // Get llap username from table for tez apps
        logger.info("Queue name should be filtered by- " + upTo10CharQueueName);
        waitExecuter.waitUntilPageFullyLoaded();
        if (!upTo10CharQueueName.trim().isEmpty() || !upTo10CharQueueName.trim().equals("-")) {
            tezLlapPage.queueSearchBox.click();
            waitExecuter.waitUntilPageFullyLoaded();
            tezLlapPage.queueSearchBox.sendKeys(upTo10CharQueueName);
            waitExecuter.waitUntilPageFullyLoaded();
            List<WebElement> queueList = tezLlapPage.getNamesFromDropDown;
            String queuenameSelected = null;
            if (!upTo10CharQueueName.isEmpty() || !upTo10CharQueueName.equals("_"))
                for (int i = 0; i < queueList.size(); i++) {
                    if (queueList.get(i).getText().equals(upTo10CharQueueName)) {
                        queuenameSelected = queueList.get(i).getText();
                        logger.info("Selected username from dropdown " + queuenameSelected);
                        test.log(LogStatus.PASS, "Queue name should be filtered by- " + queuenameSelected);
                        queueList.get(i).click();
                        waitExecuter.waitUntilPageFullyLoaded();
                        break;
                    }

                }
        }
    }


    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Write .
     */
    public String verifyWrite(TezLlapAppsDetailsPageObject tezLlapPage) {
        String WriteIO = tezLlapPage.getWrite.getText();
        LOGGER.info("Tez Status is " + WriteIO);
        waitExecuter.sleep(5000);
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertNotSame("", WriteIO, "Tez User name is not displayed in the Table");
        return WriteIO;
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
        // Navigate to Jobs tab from header
        LOGGER.info("Navigate to jobs tab from header");
        waitExecuter.waitUntilElementClickable(topPanelObj.jobs);
        waitExecuter.sleep(3000);
        topPanelObj.jobs.click();
        waitExecuter.waitUntilElementPresent(appPageObj.jobsPageHeader);
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();

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
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[2]"))).click().build().perform();
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])" +
                "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]",
                "").trim());
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        return appCount;
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

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify  Appid .
     */


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
        TezLlapAppsDetailsPageObject tezLlapPage = new TezLlapAppsDetailsPageObject(driver);
        TezLlapAppsDetailsPage tezLlapApps = new TezLlapAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        // Navigate to Jobs tab from header
        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezLlapApps.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);

        //Verify that the left pane has Tez check box and the apps number
        test.log(LogStatus.INFO, "Verify that the left pane has Tez check box and the apps number");
        logger.info("Select individual app and assert that table contain its data");

        int totalTezAppCnt = tezLlapApps.clickOnlyLink("Tez");
        if (totalTezAppCnt > 0) {
            applicationsPageObject.expandStatus.click();
            int appCount = 0;
            if (isFailedApp)
                appCount = tezLlapApps.clickOnlyLink("Failed");
            else
                appCount = tezLlapApps.clickOnlyLink("Success");
            //Clicking on the Tez app must go to apps detail page
            if (appCount > 0) {
                String headerAppId = tezLlapApps.verifyappId(tezLlapPage, applicationsPageObject);
                test.log(LogStatus.PASS, "Tez Application Id is displayed in the Header: " + headerAppId);
                //Close apps details page
                MouseActions.clickOnElement(driver, tezLlapPage.closeAppsPageTab);
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
