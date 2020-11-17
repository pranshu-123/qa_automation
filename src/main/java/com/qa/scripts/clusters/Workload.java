package com.qa.scripts.clusters;


import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.utils.*;
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
          /*  LOGGER.info("Click on HourDay dropdown");
            WebElement HourDay = (workloadPageObject.viewByHourDay);
            LOGGER.info("X coordinate: " + HourDay.getLocation().getX()
                    + ", Y coordinate: " + HourDay.getLocation().getY());
            Actions actions = new Actions(driver);
            actions.moveByOffset(HourDay.getLocation().getX() + 3, HourDay.
                    getLocation().getY() + 1);
            actions.perform();*/
            WebElement Hour = (workloadPageObject.viewByHourDay);
            Actions actions = new Actions(driver);
            actions.moveToElement(Hour)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByHourDay)
                    .perform();

        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnHourDay | Exception desc" + e.getMessage());
            throw (e);
        }
        return false;
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
            WebElement Sum = (workloadPageObject.viewByAverage);
            Actions actions = new Actions(driver);
            actions.moveToElement(Sum)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByAverage)
                    .perform();
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
            WebElement ClickDate = (workloadPageObject.viewByAverage);
            Actions actions = new Actions(driver);
            actions.moveToElement(ClickDate)
                    .contextClick()
                    .doubleClick(workloadPageObject.viewByAverage)
                    .perform();

        } catch (NoSuchElementException e) {
            LOGGER.severe("Class Workload | Method clickOnAverage | Exception desc" + e.getMessage());
            throw (e);
        }
        return false;
    }


    /*Method to click on List Date */
    public void selectDateRange(String date){
        waitExecuter.sleep(1000);
        int allDateCount = workloadPageObject.tspanCal.size();
        System.out.println("Scope count: "+ allDateCount);
        for(int i=0; i<allDateCount-1 ; i++){
            if(workloadPageObject.tspanCal.get(i).getText().equals(date)){
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, workloadPageObject.tspanCal.get(i));
            }
        }
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
    private List<String> memoryTooltipValues;
    private List<String> queriesTooltipValues;

    /**
     * Constuctor to initialize members
     */
    public Workload() {
        memoryTooltipValues = new ArrayList<String>();
        queriesTooltipValues = new ArrayList<String>();
    }

    public void navigateTextClickCheckworkloadTbl(WebDriver driver, WebElement graphElement) {
        WaitExecuter waitExecuter = new WaitExecuter(driver);

        int width = graphElement.getSize().getWidth();
        int height = graphElement.getSize().getHeight();

        int incrementalWidth = width/100;
        int incrementalHeight = height/100;

        int runningWidth = incrementalWidth;
        int runningHeight = incrementalHeight;

        ActionPerformer actionPerformer = new ActionPerformer(driver);
        // When we use moveToElement, offsets are from the center of element
        actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
        waitExecuter.sleep(2000);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);

        for (int i=0; i<4; i++) {
            /** Moving the cursor to the left of the graph since offsets are from
             the center of element **/
            actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
            memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
            //Move and click on the graph
            actionPerformer.moveToTheElementByOffsetAndClick(graphElement, -runningWidth, 0);

            waitExecuter.sleep(2000);
            if(workloadPageObject.workloadHeader.getText().contains("No Impala queries")){
                System.out.println("workload query header :"+workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :"+workloadPageObject.workloadHeader.getText());
            }else{
                System.out.println("workload query header :"+workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :"+workloadPageObject.workloadHeader.getText());
                //If Impala queries table populated then verufy for other details.

            }

            actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
            memoryTooltipValues.add(workloadPageObject.memoryTooltip.getText());
            //Move and click on the graph
            actionPerformer.moveToTheElementByOffsetAndClick(graphElement, runningWidth, 0);

            waitExecuter.sleep(2000);
            if(!workloadPageObject.workloadHeader.getText().contains("No workload queries")){
                System.out.println("workload query header :"+workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :"+workloadPageObject.workloadHeader.getText());
            }else{
                System.out.println("workload query header :"+workloadPageObject.workloadHeader.getText());
                Log.info("workload query header :"+workloadPageObject.workloadHeader.getText());
            }
            runningWidth += incrementalWidth;
        }
    }

    /* Get list of Users from chargeback table */
    public List<String> getUsersFromTable() {

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
