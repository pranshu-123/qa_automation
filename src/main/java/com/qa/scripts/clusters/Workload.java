package com.qa.scripts.clusters;


import com.qa.enums.UserAction;
import com.qa.pagefactory.appsDetailsPage.SparkAppsDetailsPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.*;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Sarbashree Ray
 */
public class Workload {

    private static final Logger LOGGER = Logger.getLogger(Workload.class.getName());
    private static Calendar cal;
    private static SimpleDateFormat dateFormatter;
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private WorkloadPageObject workloadPageObject;
    UserActions userActions;
    private static Boolean isDignosticWin = false;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Workload(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        workloadPageObject = new WorkloadPageObject(driver);
    }

    /**
     * Method to return the 1st Jan date of current year.
     */
    public static String getFirstDateOfYear() {
        cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, Calendar.OCTOBER);
        dateFormatter = new SimpleDateFormat("d");
        String startDate = dateFormatter.format(cal.getTime()).toString();
        return startDate;
    }

    public List<WebElement> getClustersList() {
        return workloadPageObject.clusterList;
    }

    /* Get the list of clusters from UI */
    public List<String> getListOfClusters(List<WebElement> clusterList) {
        int clusterSize = clusterList.size();
        List<String> listOfClusters = new ArrayList<>();

        if (clusterSize > 0) {
            for (int i = 0; i < clusterList.size(); i++) {
                String clusterNames = clusterList.get(i).getText();
                //System.out.println("Cluster name: "+ clusterNames);
                listOfClusters.add(clusterNames);
            }
        }
        return listOfClusters;
    }

   /* *//* Get time range message cluster workload *//*
    public List<WebElement> gettimerangeMessage() {
        return workloadPageObject.timerangeMessageElement;
    }*/

    /**
     * This method is use to return all yarn jobs table rows.
     *
     * @return - workload jobs table rows
     */
    public List<WebElement> getworkloadJobsTableRecord() {
        return workloadPageObject.workloadJobsTableRecords;
    }

    public List<WebElement> getworkloadJobsTableHeaderNames() {
        return workloadPageObject.workloadJobsTableHeaderNames;
    }

    public void clickOnMonth() {
        try {
            LOGGER.info("Click On Month dropdown");
            WebElement Sum = (workloadPageObject.viewByMonth);
            Actions actions = new Actions(driver);
            actions.moveToElement(Sum)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByMonth)
                    .perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }


    public void validateStagesTabs(WorkloadPageObject workloadPageObject) {
        // click the stageId to sort it
        /*driver.findElement(By.xpath("//*[@id='sparkStageNavigation-head']/tr/th[1]")).click();*/
        List<WebElement> stageRowList = workloadPageObject.tspanCal;
        verifyAssertFalse(stageRowList.isEmpty(), workloadPageObject, " No stages displayed for JobId");
        for (int i = 0; i < stageRowList.size(); i++) {
            Actions builder = new Actions(driver);
            builder.click((WebElement) stageRowList).build().perform();
            waitExecuter.waitUntilPageFullyLoaded();
        }
    }

    public void clickVCoreHour() {
        try {
            LOGGER.info("Click On Month dropdown");
            WebElement Sum = (workloadPageObject.vcoreHour);
            Actions actions = new Actions(driver);
            actions.moveToElement(Sum)
                    .contextClick()
                    .doubleClick(workloadPageObject.vcoreHour)
                    .perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    public void verifyAssertFalse(Boolean condition, WorkloadPageObject workloadPageObject, String msg) {
        String appDuration = "0";
        try {
            // appDuration = verifyRightPaneKpis(sparkAppPageObj);
            Assert.assertFalse(condition, msg);
        } catch (Throwable e) {
            // Close apps details page
            if (isDignosticWin)
                MouseActions.clickOnElement(driver, workloadPageObject.loadWinClose);
            else
                MouseActions.clickOnElement(driver, workloadPageObject.closeAppsPageTab);
            throw new AssertionError(msg + e.getMessage());
        }
    }

    /* Get Day dropdown cluster workload */
    public void clickOnDay() {
        try {
            LOGGER.info("Click On Day dropdown");
            WebElement Day = (workloadPageObject.viewByDay);
            Actions actions = new Actions(driver);
            actions.moveToElement(Day)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByDay)
                    .perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get Hour dropdown cluster workload */
    public void clickOnHour() {
        try {
            LOGGER.info("Click on Hour dropdown");
            WebElement Hour = (workloadPageObject.viewByHour);
            Actions actions = new Actions(driver);
            actions.moveToElement(Hour)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByHour)
                    .perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnHourDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get Hour dropdown cluster workload */
    public boolean clickOnHourDay() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            WebElement Hour = (workloadPageObject.viewByHourDay);
            Actions actions = new Actions(driver);
            actions.moveToElement(Hour)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByHourDay)
                    .perform();
            return true;

        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnHourDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get list of Users from workload table */
    public Boolean validateHeaderColumnNameInworkloadJobsTable() {
        System.out.println("Size of Headers in Workload Jobs Table: " + getworkloadJobsTableHeaderNames().size());
        List<WebElement> listOfworkloadJobsTableHeaderNames = getworkloadJobsTableHeaderNames();

        ArrayList<String> listOfYarnJobsColumnNames = new ArrayList<String>();

        for (int i = 0; i < listOfworkloadJobsTableHeaderNames.size() - 1; i++) {
            listOfYarnJobsColumnNames.add(listOfworkloadJobsTableHeaderNames.get(i).getText());
        }
        List<String> definedworkloadJobsColumnNames = Arrays.asList("Job Type", "Job Count");
        for (String definedworkloadjobsColumnNames : definedworkloadJobsColumnNames) {
            System.out.println(definedworkloadjobsColumnNames);
            definedworkloadJobsColumnNames.stream().forEach(System.out::println);
        }
        Boolean boolColumnNames = listOfYarnJobsColumnNames.equals(definedworkloadJobsColumnNames);
        return boolColumnNames;
    }

    /*Method to Click on Sum */
    public void clickOnSum() {
        try {
            LOGGER.info("Click on Hour dropdown");
            WebElement Hour = (workloadPageObject.viewBySum);
            Actions actions = new Actions(driver);
            actions.moveToElement(Hour)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewBySum)
                    .perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnSum | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /*Method to Click on Average */
    public void clickOnAverage() {
        try {
            LOGGER.info("Click on Sum in Hour page");
            WebDriverWait wait = new WebDriverWait(driver, 40);
            WebElement Sum = (workloadPageObject.viewByAverage);
            Actions actions = new Actions(driver);
            actions.moveToElement(Sum)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByAverage)
                    .perform();
            wait.pollingEvery(Duration.ofMillis(10));
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnAverage | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /*Method to click on Jobs table Header */
    public String getJobstableHeader() {
        String gettableValue = workloadPageObject.jobtableHeader.getText().trim();
        LOGGER.info("The header value of Cpu graph " + gettableValue);
        return gettableValue;
    }

    /*Method to click on Jobs table Header */
    public String getselectedTimeRange() {
        String selectedTimeRange = workloadPageObject.timeRange.getText();
        LOGGER.info("The header value of Cpu graph " + selectedTimeRange);
        return selectedTimeRange;
    }


    public static class ByStatusGraph {
        public static final String COLOR = "#007fad";
    }


    public String clickOnDateRange(WorkloadPageObject workloadPageObject) throws AWTException {
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        List<WebElement> dateRange = workloadPageObject.tspanCal;
        waitExecuter.waitUntilPageFullyLoaded();
        Actions actions = new Actions(driver);
        Assert.assertFalse(dateRange.isEmpty(), "There are no date range listed , expected date range");
        String iconXpath = null;
        int index = 0;
        for (int i = 0; i < dateRange.size(); i++) {
            String reportName = dateRange.get(i).getText().trim();
            System.out.println("reportName: " + reportName);
            if (reportName.equals(date)) {
                LOGGER.info("The report name is " + reportName);
                index = i + 1;
                iconXpath = "(//*[name()='svg'])[4][" + index + "]//*[local-name()='g']//*[local-name()='text']//*[local-name()='tspan']";
                //table/tbody/tr[1]/td[4]/div/span/span[contains(@class,'icon-expand')]
                //System.out.println("iconXpath: "+iconXpath);
                WebElement iconElement = driver.findElement(By.xpath(iconXpath));
                waitExecuter.waitUntilElementPresent(iconElement);
                actions.moveToElement(iconElement)
                        .contextClick()
                        .doubleClick(iconElement)
                        .perform();
                waitExecuter.waitUntilPageFullyLoaded();
                break;
            }
        }

        String statusXpath = null;
        if(index > 0){
            statusXpath = "(//*[name()='svg'])[4][" + index + "]//*[local-name()='g']";
            //statusText = getStatusText(statusXpath);
        }
        return statusXpath;
    }

        /**
         * Method to Click on workload CoreHours tab
         */
        public void selectByvCoreHours () {
            try {
                LOGGER.info("Click on workload tab");
                WaitExecuter waitExecuter = new WaitExecuter(driver);
                // Click on workload tab
                MouseActions.clickOnElement(driver, workloadPageObject.workloadTab);
                // Click on workload dropdown
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownOptionsButton);
                // Selecting the vCoreHours
                waitExecuter.sleep(3000);
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownvCoreHours);
            } catch (NoSuchElementException e) {
                LOGGER.severe("Class Workload | Method selectByvCoreHours | Exception desc" + e.getMessage());
                throw (e);
            }

        }
        private List<String> memoryTooltipValues;
        private List<String> queriesTooltipValues;

        /**
         * Constuctor to initialize members
         */
    public Workload() {
            memoryTooltipValues = new ArrayList<String>();
            queriesTooltipValues = new ArrayList<String>();
        }

        public void navigateTextClickCheckworkloadTbl (WebDriver driver, WebElement graphElement){
            WaitExecuter waitExecuter = new WaitExecuter(driver);

            int width = graphElement.getSize().getWidth();
            int height = graphElement.getSize().getHeight();

            int incrementalWidth = width / 100;
            int incrementalHeight = height / 100;

            int runningWidth = incrementalWidth;
            int runningHeight = incrementalHeight;

            ActionPerformer actionPerformer = new ActionPerformer(driver);
            // When we use moveToElement, offsets are from the center of element
            actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
            waitExecuter.sleep(2000);
            WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);

            for (int i = 0; i < 4; i++) {
                /** Moving the cursor to the left of the graph since offsets are from
                 the center of element **/
                actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
                memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
                //Move and click on the graph
                actionPerformer.moveToTheElementByOffsetAndClick(graphElement, -runningWidth, 0);

                waitExecuter.sleep(2000);
                if (workloadPageObject.workloadHeader.getText().contains("No workload queries")) {
                    System.out.println("workload query header :" + workloadPageObject.workloadHeader.getText());
                    Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                } else {
                    System.out.println("workload query header :" + workloadPageObject.workloadHeader.getText());
                    Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                    //If Impala queries table populated then verufy for other details.

                }

                actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
                memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
                //Move and click on the graph
                actionPerformer.moveToTheElementByOffsetAndClick(graphElement, runningWidth, 0);

                waitExecuter.sleep(2000);
                if (!workloadPageObject.workloadHeader.getText().contains("No workload queries")) {
                    System.out.println("workload query header :" + workloadPageObject.workloadHeader.getText());
                    Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                } else {
                    System.out.println("workload query header :" + workloadPageObject.workloadHeader.getText());
                    Log.info("workload query header :" + workloadPageObject.workloadHeader.getText());
                }
                runningWidth += incrementalWidth;
            }
        }

        /* Get list of Users from chargeback table */
        public List<String> getUsersFromTable () {

            List<WebElement> getAllUsers = workloadPageObject.getUsersFromworkloadTable;
            List<String> listOfUsers = new ArrayList<String>();

            for (int i = 0; i < getAllUsers.size(); i++) {
                String indivualUser = getAllUsers.get(i).getText();
                System.out.println("getUsersFromTable: " + indivualUser);
                listOfUsers.add(indivualUser);
            }
            LOGGER.info("List of users from chargeback table" + listOfUsers);
            return listOfUsers;
        }


        /**
         * Method to Click on workload MemoryHours tab
         */
        public void selectByMemoryHours () {
            try {
                LOGGER.info("Click on workload tab");
                WaitExecuter waitExecuter = new WaitExecuter(driver);
                // Click on workload tab
                MouseActions.clickOnElement(driver, workloadPageObject.workloadTab);
                // Click on workload dropdown
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownOptionsButton);
                // Selecting the Memory Hours
                waitExecuter.sleep(3000);
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownMemoryHours);
            } catch (NoSuchElementException e) {
                LOGGER.severe("Class Workload | Method selectByMemoryHours | Exception desc" + e.getMessage());
                throw (e);
            }
        }

        /**
         * Method to Click on workload Yarn tab
         */
        public void selectByYarn () {
            try {
                LOGGER.info("Click on workload tab");
                WaitExecuter waitExecuter = new WaitExecuter(driver);
                // Click on workload tab
                MouseActions.clickOnElement(driver, workloadPageObject.workloadTab);
                // Click on workload dropdown
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownOptionsButton);
                // Selecting the Memory Hours
                waitExecuter.sleep(3000);
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownYarn);
            } catch (NoSuchElementException e) {
                LOGGER.severe("Class Workload | Method selectByMemoryHours | Exception desc" + e.getMessage());
                throw (e);
            }
        }


        /**
         * Method to Click on workload Impala tab
         */
        public void selectByImpala () {
            try {
                LOGGER.info("Click on workload tab");
                WaitExecuter waitExecuter = new WaitExecuter(driver);
                // Click on workload tab
                MouseActions.clickOnElement(driver, workloadPageObject.workloadTab);
                // Click on workload dropdown
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownOptionsButton);
                // Selecting the Memory Hours
                waitExecuter.sleep(3000);
                MouseActions.clickOnElement(driver, workloadPageObject.workloadDropdownImpala);
            } catch (NoSuchElementException e) {
                LOGGER.severe("Class Workload | Method selectByMemoryHours | Exception desc" + e.getMessage());
                throw (e);
            }
        }
    }
