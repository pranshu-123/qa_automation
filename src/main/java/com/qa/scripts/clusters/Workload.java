package com.qa.scripts.clusters;


import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
        dateFormatter = new SimpleDateFormat("MMMM");
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

    /* Get time range message cluster workload */
    public List<WebElement> gettimerangeMessage() {
        return workloadPageObject.timerangeMessageElement;
    }

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
            workloadPageObject.viewByMonth.stream()
                    .filter(WebElement::isDisplayed).findFirst().get().click();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get Day dropdown cluster workload */
    public void clickOnDay() {
        try {
            LOGGER.info("Click On Day dropdown");
            workloadPageObject.viewByDay.stream()
                    .filter(WebElement::isDisplayed).findFirst().get().click();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get Hour dropdown cluster workload */
    public void clickOnHour() {
        try {
            LOGGER.info("Click on Hour dropdown");
            workloadPageObject.viewByHour.stream()
                    .filter(WebElement::isDisplayed).findFirst().get().click();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnHourDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get Hour dropdown cluster workload */
    public void clickOnHourDay() {
        try {
            LOGGER.info("Click on HourDay dropdown");
            WebElement HourDay = (workloadPageObject.viewByHourDay);
            LOGGER.info("X coordinate: " + HourDay.getLocation().getX()
                    + ", Y coordinate: " + HourDay.getLocation().getY());
            Actions actions = new Actions(driver);
            actions.moveByOffset(HourDay.getLocation().getX() + 1, HourDay.
                    getLocation().getY() + 1);
            actions.perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnHourDay | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /* Get list of Users from workload table */
    public Boolean validateHeaderColumnNameInworkloadJobsTable() {
        System.out.println("Size of Headers in Yarn Jobs Table: " + getworkloadJobsTableHeaderNames().size());
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
            LOGGER.info("Click on Sum in Hour page");
            WebElement Sum = (workloadPageObject.viewBySum);
            Actions actions = new Actions(driver);
            actions.moveToElement(Sum)
                    .contextClick()
                    .click(workloadPageObject.viewBySum)
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
            workloadPageObject.viewByAverage.stream()
                    .filter(WebElement::isDisplayed).findFirst().get().click();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnAverage | Exception desc" + e.getMessage());
            throw (e);
        }
    }

    /*Method to click on Jobs table Header */
    public String getJobstableHeader() {
        String gettableValue = workloadPageObject.jobtableHeader.getText();
        LOGGER.info("The header value of Cpu graph " + gettableValue);
        return gettableValue;
    }

    /*Method to click on Date */
    public boolean clickOnDate() {
        try {
            WebElement clickdate = workloadPageObject.ViewByCal;
            Actions actionBuilder = new Actions(driver);
            actionBuilder.click(clickdate).build().perform();
        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnAverage | Exception desc" + e.getMessage());
            throw (e);
        }
        return false;
    }

    /**
     * Method to Click on workload CoreHours tab
     */
    public void selectByvCoreHours() {
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

    /**
     * Method to Click on workload MemoryHours tab
     */
    public void selectByMemoryHours() {
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
}
