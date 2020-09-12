package com.qa.scripts.clusters.impala;

import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal This class contains all impala Chargeback related
 * action methods
 */
public class ChargeBackImpala {
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public ChargeBackImpala(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
    }

    /**
     * This method used to select impala in chargeback drowdown displayed at
     * chargeback page. First it click on chargeback tab which navigates to
     * chargeback page then it select impala.
     */
    public void selectImpalaChargeback() {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Click on Chargeback tab
        MouseActions.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
        // Click on chargeback dropdown
        MouseActions.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        // Selecting the impala option
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownImpalaOption);
    }

    /**
     * This method is use to return all results grouped by user table rows.
     *
     * @return - results grouped by user table rows
     */
    public List<WebElement> getResultsGroupedByTableRecords() {
        return chargebackImpalaPageObject.resultsGroupedByTableRecords;
    }

    /**
     * This method is use to return all impala jobs table rows.
     *
     * @return - impala jobs table rows
     */
    public List<WebElement> getImpalaJobsTableRecord() {
        return chargebackImpalaPageObject.impalaJobsTableRecords;
    }

    /**
     * @return Heading of the page
     */
    public String getHeading() {
        return chargebackImpalaPageObject.chargeBackPageHeading.getText();
    }

    /* Get the list of clusters from UI */
    public int getListOfClusters(List<WebElement> clusterList) {
        LOGGER.info("Number of clusters available are : " + clusterList.size());
        int clusterSize = clusterList.size();
        if (clusterSize > 0) {
            for (int i = 0; i < clusterList.size(); i++) {
                String clusterNames = chargebackImpalaPageObject.listOfClusters.get(i).getText();
                LOGGER.info("The cluster name is : " + clusterNames);
            }
        }
        return clusterSize;
    }

    /* Validate groupBy options */
    public Boolean validateGroupByOptions() {
        // Get the list of webelements of groupby options
        List<WebElement> listOfWebElemnts = chargebackImpalaPageObject.listOfGroupByOptions;
        // Empty array to add displayed group by options
        ArrayList<String> listOfGroupByOptions = new ArrayList<String>();
        // Itterate Webelement list to get the value of each element
        for (int i = 0; i < listOfWebElemnts.size(); i++) {
            listOfGroupByOptions.add(listOfWebElemnts.get(i).getText());
        }
        List<String> definedGroupByOption = Arrays.asList("User", "Real User", "Queue", "Department", "Priority",
                "team", "project", "realUser", "dBs");
        LOGGER.info("Actual ist of options" + listOfGroupByOptions);
        Boolean compareGroupByOptions = listOfGroupByOptions.equals(definedGroupByOption);
        LOGGER.info("Expected list of options" + definedGroupByOption);
        return compareGroupByOptions;

    }

    /* Get list of Users from chargeback table */
    public List<String> getUsersFromTable() {

        List<WebElement> getAllUsers = chargebackImpalaPageObject.getUsersFromChargebackTable;
        List<String> listOfUsers = new ArrayList<String>();

        for (int i = 0; i < getAllUsers.size(); i++) {
            String indivualUser = getAllUsers.get(i).getText();
            System.out.println("getUsersFromTable: " + indivualUser);
            listOfUsers.add(indivualUser);
        }
        LOGGER.info("List of users from chargeback table" + listOfUsers);
        return listOfUsers;
    }

    /* Get list of Users from Finished Impala Jobs Table */
    public List<String> getUsersFromImpalaJobsTable() {

        List<WebElement> getAllUsers = chargebackImpalaPageObject.getUsersFromFinishedImpalaJobsTable;
        List<String> listOfUsers = new ArrayList<String>();

        for (int i = 0; i < getAllUsers.size(); i++) {
            String indivualUser = getAllUsers.get(i).getText();
            System.out.println("getUsersFromImpalaJobsTable: " + indivualUser);
            listOfUsers.add(indivualUser);
        }
        LOGGER.info("List of users from finished impala jobs table" + listOfUsers);
        return listOfUsers;
    }


    public void selectgroupby() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.realUser);
        chargebackImpalaPageObject.realUser.click();
    }

    public void selecttable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectgroup);
        chargebackImpalaPageObject.selectgroup.click();
    }

    public void selectQueuetable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectQueue);
        chargebackImpalaPageObject.selectQueue.click();
    }

    public void selectdepttable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectdept);
        chargebackImpalaPageObject.selectdept.click();
    }

    public void selectgrouptable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectprojectgroup);
        chargebackImpalaPageObject.selectprojectgroup.click();
    }

    public void selectrealUsertable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectrealUser);
        chargebackImpalaPageObject.selectrealUser.click();
    }

    public void selectdbstable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectdbs);
        chargebackImpalaPageObject.selectdbs.click();
    }

    public void selectinputtables() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectinput);
        chargebackImpalaPageObject.selectinput.click();
    }


    /* Get CPU hours from graph header */
    public double getCPUHoursFromGraphHeader() {
        String getHeaderValue = chargebackImpalaPageObject.CPUHoursFromGraphHeader.getText();
        Double stringValueToDouble = Double.parseDouble(getHeaderValue);
        double roundOff = Math.round(stringValueToDouble * 100) / 100;
        LOGGER.info("CPU hours from graph header" + roundOff);
        return roundOff;
    }

    /* Get total CPU hours from table */
    public double getTotalCPUHoursFromTable() {
        List<Double> eachUserCPUHours = getUsersCPUHoursFromTable();
        Double total_secs = 0.0;
        for (Double arrValue : eachUserCPUHours) {
            total_secs = total_secs + arrValue;

        }
        LOGGER.info("Total CPU hours from table" + total_secs);
        return total_secs;
    }

    /* Get Memory hours from graph header */
    public int getMemoryHoursInSecFromGraphHeader() {
        String getHeaderValue = chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText();
        LOGGER.info("The header value of memory graph " + getHeaderValue);
        int headerInSeconds = convertTimeToSeconds(getHeaderValue);
        LOGGER.info(" Header value in sec " + headerInSeconds);
        return headerInSeconds;
    }

    /* Get total Memory hours from table */
    public int getTotalMemoryHoursToSecondFromTable() {
        List<String> memoryHoursArray = new ArrayList<String>();
        List<WebElement> listOfHours = chargebackImpalaPageObject.getMemoryDataFromTable;
        for (int i = 0; i < listOfHours.size(); i++) {
            String hours = listOfHours.get(i).getText().trim();

            // System.out.println(hours);
            if (!hours.contains("<1s")) {
                memoryHoursArray.add(hours);
            }
        }
        int total_secs = 0;
        for (String arrValue : memoryHoursArray) {
            int tableDataInSeconds = convertTimeToSeconds(arrValue);
            total_secs = total_secs + tableDataInSeconds;
            LOGGER.info(tableDataInSeconds + " Table value in sec");
        }
        LOGGER.info("Total Secs of all users " + total_secs);
        return total_secs;
    }

    public String getMemoryGraphHeader() {
        String getHeaderValue = chargebackImpalaPageObject.MemoryHoursFromGraphHeader.getText();
        LOGGER.info("The header value of memory graph " + getHeaderValue);
        return getHeaderValue;
    }

    public String getCpuGraphHeader() {
        String getCpuValue = chargebackImpalaPageObject.cpuGraphHeader.getText();
        LOGGER.info("The header value of Cpu graph " + getCpuValue);
        return getCpuValue;
    }

    public String getdonutchartHeader() {
        String getdonutchartValue = chargebackImpalaPageObject.donutchart.getText();
        LOGGER.info("The header value of donutchart graph " + getdonutchartValue);
        return getdonutchartValue;
    }

    public String getImpalatableHeader() {
        String getImpalaValue = chargebackImpalaPageObject.impalajobs.getText();
        LOGGER.info("The header value of Impala graph " + getImpalaValue);
        return getImpalaValue;
    }

    public String getchargebacktableHeader() {
        String getchargebackValue = chargebackImpalaPageObject.Chargebacktable.getText();
        LOGGER.info("The header value of Impala graph " + getchargebackValue);
        return getchargebackValue;
    }

    /* Convert Months, Days, Hours, Minutes to Seconds */
    public int convertTimeToSeconds(String time) {
        int secs = 0;
        String[] splitted = time.split(" ");
        for (String val : splitted) {
            if (val.endsWith("M")) {
                String[] split2 = val.split("M");
                int integerValueOfMonths = Integer.valueOf(split2[0]);
                secs = secs + (integerValueOfMonths * 2592000);
            }
            if (val.contains("D")) {
                String[] split2 = val.split("D");
                int integerValueOfDays = Integer.valueOf(split2[0]);
                secs = secs + (integerValueOfDays * 86400);
            }
            if (val.contains("h")) {
                String[] split2 = val.split("h");
                int integerValueOfHours = Integer.valueOf(split2[0]);
                secs = secs + (integerValueOfHours * 3600);
            }
            if (val.contains("m")) {
                String[] split2 = val.split("m");
                int integerValueOfMinutes = Integer.valueOf(split2[0]);
                secs = secs + (integerValueOfMinutes * 60);
            }
            if (val.contains("s") && !val.equals("<1s")) {
                String[] split2 = val.split("s");
                int integerValueOfSeconds = Integer.valueOf(split2[0]);
                secs = secs + integerValueOfSeconds;
            }
        }
        return secs;
    }

    public boolean isJobsFromGraphHeaderDisplayed() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.JobsFromGraphHeader);
        return chargebackImpalaPageObject.JobsFromGraphHeader.isDisplayed();
    }

    public String getNoDataAvailableText() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.jobsNoDataAvailableText);
        waitExecuter.sleep(2000);
        String text = JavaScriptExecuter.getTextFromElement(driver, chargebackImpalaPageObject.jobsNoDataAvailableText);
        return text;
    }

    public void clikOnDownloadCSV() {

        List<WebElement> downloadCSVList = TestUtils.getWebElements(driver, "xpath", "//button[@class='btn-csv']");
        System.out.println("List of downloadCSV file: " + downloadCSVList.size());
        waitExecuter.sleep(2000);

        for (int i = 0; i < downloadCSVList.size(); i++) {
            downloadCSVList.get(i).click();
            waitExecuter.sleep(2000);
        }
    }

    /* Get memory data list from table */
    public List<WebElement> getMemoryDataFromTable() {
        List<WebElement> listOfHours = chargebackImpalaPageObject.getMemoryDataFromTable;
        return listOfHours;
    }

    /* Get list of cpu hours from chargeback table */
    public List<WebElement> getCPUHourListFromTable() {
        List<WebElement> getAllUsersCPUHours = chargebackImpalaPageObject.getUsersCPUHoursFromChargebackTable;
        return getAllUsersCPUHours;
    }

    /* Convert Months, Days, Hours, Minutes to Hours */
    public double convertTimeToHours(String time) {
        double hours = 0;
        String[] splitted = time.split(" ");
        for (String val : splitted) {
            if (val.endsWith("M")) {
                String[] split2 = val.split("M");
                double integerValueOfMonths = Double.valueOf(split2[0]);
                hours = hours + (integerValueOfMonths * (30 * 24));
            }
            if (val.contains("D")) {
                String[] split2 = val.split("D");
                double integerValueOfDays = Double.valueOf(split2[0]);
                hours = hours + (integerValueOfDays * 24);
            }
            if (val.contains("h")) {
                String[] split2 = val.split("h");
                double integerValueOfHours = Double.valueOf(split2[0]);
                hours = hours + integerValueOfHours;
            }
            if (val.contains("m")) {
                String[] split2 = val.split("m");
                double integerValueOfMinutes = Double.valueOf(split2[0]);
                hours = hours + (integerValueOfMinutes / 60);
            }
            if (val.contains("s")) {
                String[] split2 = val.split("s");
                double integerValueOfSeconds = Double.valueOf(split2[0]);
                hours = hours + (integerValueOfSeconds / 3600);
            }
        }
        return hours;
    }

    /* Get list of Memory time in hours from chargeback table */
    public List<Double> getUsersMemoryHoursFromTable() {
        List<WebElement> memoryHoursListFromTable = chargebackImpalaPageObject.getUsersMemoryHoursFromChargebackTable;
        List<String> memoryUsageTimePerUser = new ArrayList<String>();
        List<Double> memoryListInHours = new ArrayList<Double>();
        for (int i = 0; i < memoryHoursListFromTable.size(); i++) {
            String indivualHours = memoryHoursListFromTable.get(i).getText();
            //Only add ">1s" value to list
            if (!indivualHours.contains("<1s")) {
                memoryUsageTimePerUser.add(indivualHours);
            }
        }
        for (String memoryHours : memoryUsageTimePerUser) {
            double hours = convertTimeToHours(memoryHours);
            memoryListInHours.add(hours);
        }
        LOGGER.info("List of CPU hours in integer from table" + memoryListInHours);
        return memoryListInHours;
    }

    /* Get list of CPU hours from chargeback table */
    public List<Double> getUsersCPUHoursFromTable() {
        List<WebElement> getCPUHoursList = chargebackImpalaPageObject.getUsersCPUHoursFromChargebackTable;
        List<Double> userHours = new ArrayList<Double>();
        for (int i = 0; i < getCPUHoursList.size(); i++) {
            String indivualHours = getCPUHoursList.get(i).getText();
            userHours.add(Double.parseDouble(indivualHours));
        }
        LOGGER.info("List of CPU hours from table" + userHours);
        return userHours;
    }

    /* Get list of CPU costs from chargeback table */
    public List<Double> getUsersCPUHoursCostFromTable() {
        List<WebElement> getAllUsersCPUHoursCosts = chargebackImpalaPageObject.getUsersCPUHoursCostFromChargebackTable;
        List<Double> userCosts = new ArrayList<Double>();
        for (int i = 0; i < getAllUsersCPUHoursCosts.size(); i++) {
            String indivualcost = getAllUsersCPUHoursCosts.get(i).getText();
            String[] splitted = indivualcost.split(" ");
            String trimmedSplitValue = splitted[2];
            // System.out.println(trimmedSplitValue);
            userCosts.add(Double.parseDouble(trimmedSplitValue));
        }
        LOGGER.info("List of CPU costs calculated from table" + userCosts);
        return userCosts;
    }

    /* Compare CPU calculated cost to table cost */
    public Boolean compareTableCPUCostToCalculatedCost(List<Double> cpuCostFromTable, List<Double> tableHours, int factor) {
        List<Double> calculatedHoursCost = new ArrayList<Double>();
        for (Double hour : tableHours) {
            calculatedHoursCost.add(hour * factor);
        }
        LOGGER.info("List of CPU cost  " + cpuCostFromTable);
        LOGGER.info("List of calculated hours " + calculatedHoursCost);
        //Sum of costs
        double totalcpucostTable = 0.0;
        for (double cpuCost : cpuCostFromTable) {
            totalcpucostTable = totalcpucostTable + cpuCost;
        }
        double totalCalculatedCost = 0.0;
        for (double calculatedCost : calculatedHoursCost) {
            totalCalculatedCost = totalCalculatedCost + calculatedCost;
        }
        LOGGER.info("Total cpu cost from table : " + totalcpucostTable + " Total calculated cost : " + totalCalculatedCost);
        //Compare the calculated cost to total of cpu cost differece from table is less than 1
        if ((totalCalculatedCost - totalcpucostTable) < 1)
            return true;
        else
            return false;
    }


    /* Get list of memory costs from chargeback table */
    public List<Double> getUsersMemoryHoursCostFromTable() {

        List<WebElement> getAllUsersMemoryHoursCosts = chargebackImpalaPageObject.getUsersMemoryHoursCostFromChargebackTable;
        List<Double> perUserMemoryCost = new ArrayList<Double>();

        for (int i = 0; i < getAllUsersMemoryHoursCosts.size(); i++) {
            String indivualcost = getAllUsersMemoryHoursCosts.get(i).getText();
            String[] splitted = indivualcost.split(" ");
            String trimmedSplitValue = splitted[2];
            String str = trimmedSplitValue.replaceAll(",", "");
            // System.out.println(trimmedSplitValue);
            perUserMemoryCost.add(Double.parseDouble(str));
        }
        LOGGER.info("List of memory costs calculated from table" + perUserMemoryCost);
        return perUserMemoryCost;
    }

    /* Compare memory calculated cost to table cost */
    public Boolean compareTableMemoryCostToCalculatedCost
    (List<Double> memoryCostFromTable, List<Double> fromTableMemoryHours, int factor) {
        List<Double> calculatedMemoryCost = new ArrayList<Double>();
        for (Double hour : fromTableMemoryHours) {
            calculatedMemoryCost.add(hour * factor);
        }
        LOGGER.info("List of memory cost  " + memoryCostFromTable);
        LOGGER.info("List of memory calculated hours " + calculatedMemoryCost);
        //Sum of costs
        double totalmemoryCostTable = 0.0;
        for (double memCost : memoryCostFromTable) {
            totalmemoryCostTable = totalmemoryCostTable + memCost;
        }
        double totalmemoryCalculatedCost = 0.0;
        for (double calcCost : calculatedMemoryCost) {
            totalmemoryCalculatedCost = totalmemoryCalculatedCost + calcCost;
        }
        LOGGER.info("Total memory cost from table : " + totalmemoryCostTable + " Total calculated cost : " + totalmemoryCalculatedCost);
        //Compare the calculated cost to total of cpu cost differece from table is less than 1
        if ((totalmemoryCalculatedCost - totalmemoryCostTable) < 1)
            return true;
        else
            return false;
    }

    public Boolean compareCPUCostToCalculatedCost(List<Double> cpuCostFromTable,
                                                  List<Double> tableHours,
                                                  double factor) {
        {
            List<Double> calculatedHoursCost = new ArrayList<Double>();
            for (Double hour : tableHours) {
                calculatedHoursCost.add(hour * factor);
            }
            LOGGER.info("List of CPU cost  " + cpuCostFromTable);
            LOGGER.info("List of calculated hours " + calculatedHoursCost);
            //Sum of costs
            double totalcpucostTable = 0.0;
            for (double cpuCost : cpuCostFromTable) {
                totalcpucostTable = totalcpucostTable + cpuCost;
            }
            double totalCalculatedCost = 0.0;
            for (double calculatedCost : calculatedHoursCost) {
                totalCalculatedCost = totalCalculatedCost + calculatedCost;
            }
            LOGGER.info("Total cpu cost from table : " + totalcpucostTable + " Total calculated cost : " + totalCalculatedCost);
            //Compare the calculated cost to total of cpu cost differece from table is less than 1
            if ((totalCalculatedCost - totalcpucostTable) < 1)
                return true;
            else
                return false;
        }

    }


    public boolean isTotalNumberOfJobCountHeader(){
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.JobsFromGraphHeader);
        System.out.println("Total Job Counts: "+getTotalJobCountFromJobsGraphHeader());
        String strTotalJobCountHeader = getTotalJobCountFromJobsGraphHeader();
        Integer intTotalJobCountHeader = Integer.parseInt(strTotalJobCountHeader);
        System.out.println("Total Job Counts in Integer: "+intTotalJobCountHeader);
        if(intTotalJobCountHeader == 0)
            return false;

        return true;
    }

    public String getTotalJobCountFromJobsGraphHeader(){
        return 	chargebackImpalaPageObject.JobsFromGraphHeader.getText();
    }


    public int getTotalJobsCountFromTable(){
        List<WebElement> listOfJobsCount = chargebackImpalaPageObject.getJobsCountFromChargebackTable;
        int totalJobsCount=0;
        for(int i=0; i<=listOfJobsCount.size()-1; i++){
            String strJobCount = listOfJobsCount.get(i).getText();
            totalJobsCount = totalJobsCount + Integer.parseInt(strJobCount);
        }
        return totalJobsCount;
    }

    public void deselectGroupByFilters(){
        int countChargeBackDrill = chargebackImpalaPageObject.listChargeBackDrillFromGroupByFilters.size();
        List<WebElement> listChargeBackDrill = chargebackImpalaPageObject.listChargeBackDrillFromGroupByFilters;

        for(int i =countChargeBackDrill-1 ; i >= 0; i-- ){
            listChargeBackDrill.get(i).click();
            waitExecuter.sleep(2000);
        }
    }

    public void click1Row1ColumnFromGroupByTable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.select1Row1ColumnFromGroupByTable);
        waitExecuter.sleep(2000);
        chargebackImpalaPageObject.select1Row1ColumnFromGroupByTable.click();
    }

    public int getNumberOfOptionsInGroupBy(){
        // Verify the number of options available in group by options
        waitExecuter.sleep(1000);
        return chargebackImpalaPageObject.listOfGroupByOptions.size();
    }

    public void clickOnGroupBySearchBox(){
        // Click on yarn chargeback group by combobox
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.groupBySearchBox.click();
    }

}