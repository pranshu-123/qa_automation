package com.qa.scripts.clusters.impala;

import com.qa.enums.UserAction;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.enums.chargeback.ImpalaJobTableColumn;
import com.qa.enums.hbase.HbaseTablesColumn;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.*;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal This class contains all impala Chargeback related
 * action methods
 */
public class ChargeBackImpala {
    private static final Logger LOGGER = Logger.getLogger(ChargeBackImpala.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ChargebackImpalaPageObject chargebackImpalaPageObject;
    private final ApplicationsPageObject applicationsPageObject;
    private final UserActions userActions;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public ChargeBackImpala(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        userActions = new UserActions(driver);
    }

    /**
     * This method used to select impala in chargeback drowdown displayed at
     * chargeback page. First it click on chargeback tab which navigates to
     * chargeback page then it select impala.
     */
    public void selectImpalaChargeback(String chargeBackName) {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Click on Chargeback tab
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
        userActions.performActionWithPolling(chargebackImpalaPageObject.clusterChargeBackTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        waitExecuter.sleep(2000);
        userActions.performActionWithPolling(chargebackImpalaPageObject.chargeBackDropdownOptionsButton, UserAction.CLICK);
        userActions.performActionWithPolling(chargebackImpalaPageObject.chargeBackSearchBox, UserAction.SEND_KEYS,
                chargeBackName);
        userActions.performActionWithPolling(chargebackImpalaPageObject.chargeBackSearchFirstField, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
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

    /**
     * Get no data webelements from impala chargeback page
     */
    public List<WebElement> getNoDataElementsImpalaChargeBack() {
        List<WebElement> noDataWebElements = new ArrayList<>();
        for (WebElement element : chargebackImpalaPageObject.impalaChargebackPieCharts) {
            if (element.getText().trim().equalsIgnoreCase("No Data Available.")) {
                noDataWebElements.add(element);
            }
        }
        try {
            noDataWebElements.add(chargebackImpalaPageObject.impalaChargebackGroupByNoData);
            noDataWebElements.add(chargebackImpalaPageObject.impalaChargebackTableNoData);
        } catch (NoSuchElementException exception) {
            Assert.assertFalse(true, "Data is displayed for impala.");
        }
        return noDataWebElements;
    }

    /* Validate groupBy options */
    public Boolean validateGroupByOptions() {
        // Get the list of webelements of groupby options
        List<WebElement> listOfWebElemnts = chargebackImpalaPageObject.listOfGroupByOptions;
        // Empty array to add displayed group by options
        ArrayList<String> listOfGroupByOptions = new ArrayList<String>();
        // Itterate Webelement list to get the value of each element
        for (int i = 0; i < listOfWebElemnts.size(); i++) {
            listOfGroupByOptions.add(listOfWebElemnts.get(i).getText().toLowerCase());
        }
        List<String> definedGroupByOption = Arrays.asList("user", "real user", "queue");
        LOGGER.info("Actual ist of options" + listOfGroupByOptions);
        Boolean compareGroupByOptions = listOfGroupByOptions.containsAll(definedGroupByOption);
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

    /**
     * Select group by options
     *
     * @param groupBy - group by option to select
     */
    public void selectGroupBy(GroupByOptions groupBy) {
        closeGroupByOptionsExcept(groupBy);
        switch (groupBy) {
            case USER:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByUserOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByUserOption, UserAction.CLICK);
                break;
            case QUEUE:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByQueueOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByQueueOption, UserAction.CLICK);
                break;
            case REAL_USER:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.realUser);
                userActions.performActionWithPolling(chargebackImpalaPageObject.realUser, UserAction.CLICK);
                break;
            case DEPT:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByDeptOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByDeptOption, UserAction.CLICK);
                break;
            case PROJECT:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByProjectOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByProjectOption, UserAction.CLICK);
                break;
            case PRIORITY:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByPriorityOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByPriorityOption, UserAction.CLICK);
                break;
            case TEAM:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByTeamOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByTeamOption, UserAction.CLICK);
                break;
            case REALUSER:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByrealUserOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByrealUserOption, UserAction.CLICK);
                break;
            case DBS:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByDBSOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByDBSOption, UserAction.CLICK);
                break;
            case INPUT_TABLES:
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupByInputTablesOption);
                userActions.performActionWithPolling(chargebackImpalaPageObject.groupByInputTablesOption, UserAction.CLICK);
                break;
        }
    }

    /**
     * Close group by options
     */
    public void closeGroupByOptionsExcept(GroupByOptions groupByOption) {
        for (WebElement element : chargebackImpalaPageObject.closeGroupByOptionButton) {
            if (!element.findElement(By.xpath("//parent::li")).getText().contains(groupByOption.value)) {
                userActions.performActionWithPolling(element, UserAction.CLICK);
            }
        }
    }

    /**
     * Validate whether group by displayed for pie charts
     */
    public void validateGroupByPieCharts() {
        for (WebElement element : chargebackImpalaPageObject.pieChartGroupBySearchBoxs) {
            Boolean isGroupingDisplayed = false;
                waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
                if (!element.getText().equalsIgnoreCase("")) {
                    isGroupingDisplayed = true;
                }
            Assert.assertTrue(isGroupingDisplayed, "Group by option not displayed for pie chart");
        }
    }

    /**
     * Validate whether group by included in showing group By tables
     *
     * @param groupByOption - Group by option
     */
    public void validateGroupByTableResults(GroupByOptions groupByOption) {
        Boolean isGroupByHeadingDisplayed = false;
        for (WebElement element : chargebackImpalaPageObject.groupByResultsTableHeadings) {
            if (element.getText().equalsIgnoreCase(groupByOption.value)) {
                isGroupByHeadingDisplayed = true;
            }
        }
        Assert.assertTrue(isGroupByHeadingDisplayed, "Group By heading is not displayed in group by table.");
    }

    /**
     * Get list of finished impala jobs
     *
     * @return
     */
    public List<WebElement> getFinishedJobsImpala() {
        return chargebackImpalaPageObject.impalaJobsTableRecords;
    }

    public boolean selecttable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectgroup);
        chargebackImpalaPageObject.selectgroup.click();
        waitExecuter.sleep(3000);
        return false;
    }

    public boolean selectQueuetable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectQueue);
        chargebackImpalaPageObject.selectQueue.click();
        waitExecuter.sleep(3000);
        return false;
    }

    public boolean selectdepttable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectdept);
        chargebackImpalaPageObject.selectdept.click();
        waitExecuter.sleep(3000);
        return false;
    }

    public boolean selectgrouptable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectprojectgroup);
        chargebackImpalaPageObject.selectprojectgroup.click();
        waitExecuter.sleep(3000);
        return false;
    }

    public boolean selectrealUsertable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectrealUser);
        chargebackImpalaPageObject.selectrealUser.click();
        waitExecuter.sleep(3000);
        return false;
    }

    public boolean selectdbstable() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.selectdbs);
        chargebackImpalaPageObject.selectdbs.click();

        waitExecuter.sleep(3000);
        return false;
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
            // Only add ">1s" value to list
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
            LOGGER.info("cpu hour cost from chargeback table" + indivualcost);
            String[] splitted = indivualcost.split(" ");
            String trimmedSplitValue = splitted[2];
            LOGGER.info("Get only CPU hour cost from string" + trimmedSplitValue);
            String removeCommaFromCost = trimmedSplitValue.replaceAll(",", "");
            LOGGER.info("Remove comma from cost " + trimmedSplitValue);
            // System.out.println(trimmedSplitValue);
            userCosts.add(Double.parseDouble(removeCommaFromCost));
        }
        LOGGER.info("List of CPU costs calculated from table" + userCosts);
        return userCosts;
    }

    /* Compare CPU calculated cost to table cost */
    public Boolean compareTableCPUCostToCalculatedCost(List<Double> cpuCostFromTable, List<Double> tableHours,
                                                       int factor) {
        List<Double> calculatedHoursCost = new ArrayList<Double>();
        for (Double hour : tableHours) {
            calculatedHoursCost.add(hour * factor);
        }
        LOGGER.info("List of CPU cost  " + cpuCostFromTable);
        LOGGER.info("List of calculated hours " + calculatedHoursCost);
        // Sum of costs
        double totalcpucostTable = 0.0;
        for (double cpuCost : cpuCostFromTable) {
            totalcpucostTable = totalcpucostTable + cpuCost;
        }
        double totalCalculatedCost = 0.0;
        for (double calculatedCost : calculatedHoursCost) {
            totalCalculatedCost = totalCalculatedCost + calculatedCost;
        }
        LOGGER.info(
                "Total cpu cost from table : " + totalcpucostTable + " Total calculated cost : " + totalCalculatedCost);
        // Compare the calculated cost to total of cpu cost differece from table is less
        // than 1
		return (totalCalculatedCost - totalcpucostTable) < 1;
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
    public Boolean compareTableMemoryCostToCalculatedCost(List<Double> memoryCostFromTable,
                                                          List<Double> fromTableMemoryHours, int factor) {
        List<Double> calculatedMemoryCost = new ArrayList<Double>();
        for (Double hour : fromTableMemoryHours) {
            calculatedMemoryCost.add(hour * factor);
        }
        LOGGER.info("List of memory cost  " + memoryCostFromTable);
        LOGGER.info("List of memory calculated hours " + calculatedMemoryCost);
        // Sum of costs
        double totalmemoryCostTable = 0.0;
        for (double memCost : memoryCostFromTable) {
            totalmemoryCostTable = totalmemoryCostTable + memCost;
        }
        double totalmemoryCalculatedCost = 0.0;
        for (double calcCost : calculatedMemoryCost) {
            totalmemoryCalculatedCost = totalmemoryCalculatedCost + calcCost;
        }
        LOGGER.info("Total memory cost from table : " + totalmemoryCostTable + " Total calculated cost : "
                + totalmemoryCalculatedCost);
        // Compare the calculated cost to total of cpu cost differece from table is less
        // than 1
		return (totalmemoryCalculatedCost - totalmemoryCostTable) < 1;
    }

    public Boolean compareCPUCostToCalculatedCost(List<Double> cpuCostFromTable, List<Double> tableHours,
                                                  double factor) {
        {
            List<Double> calculatedHoursCost = new ArrayList<Double>();
            for (Double hour : tableHours) {
                calculatedHoursCost.add(hour * factor);
            }
            LOGGER.info("List of CPU cost  " + cpuCostFromTable);
            LOGGER.info("List of calculated hours " + calculatedHoursCost);
            // Sum of costs
            double totalcpucostTable = 0.0;
            for (double cpuCost : cpuCostFromTable) {
                totalcpucostTable = totalcpucostTable + cpuCost;
            }
            double totalCalculatedCost = 0.0;
            for (double calculatedCost : calculatedHoursCost) {
                totalCalculatedCost = totalCalculatedCost + calculatedCost;
            }
            LOGGER.info("Total cpu cost from table : " + totalcpucostTable + " Total calculated cost : "
                    + totalCalculatedCost);
            // Compare the calculated cost to total of cpu cost differece from table is less
            // than 1
			return (totalCalculatedCost - totalcpucostTable) < 1;
        }

    }

    public boolean isTotalNumberOfJobCountHeader() {
        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.JobsFromGraphHeader);
        System.out.println("Total Job Counts: " + getTotalJobCountFromJobsGraphHeader());
        String strTotalJobCountHeader = getTotalJobCountFromJobsGraphHeader();
        strTotalJobCountHeader = strTotalJobCountHeader.substring(0, strTotalJobCountHeader.length() - 1);
        System.out.println("Total Job Counts after removing last char: " + strTotalJobCountHeader);
        Integer intTotalJobCountHeader = Integer.parseInt(strTotalJobCountHeader);
        System.out.println("Total Job Counts in Integer: " + intTotalJobCountHeader);
		return intTotalJobCountHeader != 0;
	}

    public String getTotalJobCountFromJobsGraphHeader() {
        return chargebackImpalaPageObject.JobsFromGraphHeader.getText();
    }

    public int getTotalJobsCountFromTable() {
        List<WebElement> listOfJobsCount = chargebackImpalaPageObject.getJobsCountFromChargebackTable;
        int totalJobsCount = 0;
        for (int i = 0; i <= listOfJobsCount.size() - 1; i++) {
            String strJobCount = listOfJobsCount.get(i).getText();
            totalJobsCount = totalJobsCount + Integer.parseInt(strJobCount);
        }
        return totalJobsCount;
    }

    public void deselectGroupByFilters() {
        int countChargeBackDrill = chargebackImpalaPageObject.listChargeBackDrillFromGroupByFilters.size();
        List<WebElement> listChargeBackDrill = chargebackImpalaPageObject.listChargeBackDrillFromGroupByFilters;

        for (int i = countChargeBackDrill - 1; i >= 0; i--) {
            MouseActions.clickOnElement(driver, listChargeBackDrill.get(i));
            // listChargeBackDrill.get(i).click();
            waitExecuter.sleep(2000);
        }
    }

    public void click1Row1ColumnFromGroupByTable() {

        waitExecuter.waitUntilElementPresent(chargebackImpalaPageObject.select1Row1ColumnFromGroupByTable);
        waitExecuter.sleep(2000);
        chargebackImpalaPageObject.select1Row1ColumnFromGroupByTable.click();

    }

    public int getNumberOfOptionsInGroupBy() {
        // Verify the number of options available in group by options
        waitExecuter.sleep(1000);
        return chargebackImpalaPageObject.listOfGroupByOptions.size();
    }

    public void clickOnGroupBySearchBox() {
        // Click on yarn chargeback group by combobox
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupBySearchBox);
        userActions.performActionWithPolling(chargebackImpalaPageObject.groupBySearchBox, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.groupBySearchBox);
    }

    /**
     * Click on the table heading of impala job table
     *
     * @param impalaJobTableColumn - Impala Job Table
     */
    public void clickOnTableHeading(ImpalaJobTableColumn impalaJobTableColumn) {
        WebElement headingToBeClicked = chargebackImpalaPageObject.impalaJobsTable
                .findElement(By.xpath("//th[" + (impalaJobTableColumn.index + 1) + "]/a"));
        userActions.performActionWithPolling(headingToBeClicked, UserAction.CLICK);
        waitExecuter.sleep(2000);
    }

    /**
     * Validate whether data is sorted of impala table column
     *
     * @param impalaJobTableColumn - Table Column to be clicked
     * @param isReversed           - Descending Order
     * @return true if data is sorted.
     */
    public boolean isDataSorted(ImpalaJobTableColumn impalaJobTableColumn, Boolean isReversed) {
        List<String> actualDataString = new ArrayList<>();
        List<WebElement> tableRows = chargebackImpalaPageObject.impalaJobsTableRecords;
        for (WebElement row : tableRows) {
            actualDataString.add(row.findElement(By.xpath("td[" + (impalaJobTableColumn.index + 1) + "]")).getText());
            actualDataString = actualDataString.stream()
                    .map(data -> Arrays.asList(data.split("\n")).stream().reduce((first, second) -> second).get())
                    .collect(Collectors.toList());
        }
        if (clickOnLastOfPaginationIfExists()) {
            tableRows = chargebackImpalaPageObject.impalaJobsTableRecords;
            for (WebElement row : tableRows) {
                actualDataString
                        .add(row.findElement(By.xpath("td[" + (impalaJobTableColumn.index + 1) + "]")).getText());
                actualDataString = actualDataString.stream()
                        .map(data -> Arrays.asList(data.split("\n")).stream().reduce((first, second) -> second).get())
                        .collect(Collectors.toList());
            }
        }
        if (impalaJobTableColumn == ImpalaJobTableColumn.START_TIME
                || impalaJobTableColumn == ImpalaJobTableColumn.FINISHED_TIME)

        {
            List<Date> actualDates = actualDataString.stream().map(data -> DateUtils.getDateWithDateString(data))
                    .collect(Collectors.toList());
            List<Date> sortedList = new ArrayList(actualDates);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
			return actualDates.equals(sortedList);
        } else if (impalaJobTableColumn == ImpalaJobTableColumn.REAL_USER
                   ||impalaJobTableColumn == ImpalaJobTableColumn.QUEUE) {
            List<String> actualDataInteger = actualDataString.stream().map(data ->
                    convertToString(data)).collect(Collectors.toList());
            List<String> sortedList = new ArrayList(actualDataInteger);
            return true;
        } else if (impalaJobTableColumn == ImpalaJobTableColumn.MEMORY_MB_SECONDS
                || impalaJobTableColumn == ImpalaJobTableColumn.TOTAL_PROCESSING_TIME_SECONDS) {
            List<Integer> actualTime = actualDataString.stream().map(data -> convertTimeToSeconds(data))
                    .collect(Collectors.toList());
            List<Date> sortedList = new ArrayList(actualTime);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
			return actualTime.equals(sortedList);
        } else {
            List<String> sortedList = new ArrayList(actualDataString);
            if (isReversed) {
                sortedList.sort(Comparator.reverseOrder());
            } else {
                sortedList.sort(Comparator.naturalOrder());
            }
			return actualDataString.equals(sortedList);
        }
    }

    //convert String data to integer
    public String convertToString(String data) {
        String newData = String.valueOf(data.split(""));
        newData.replaceAll("(^|\\s)\\d+(gb|mb|kb|tb|b)($|\\s)", Matcher.quoteReplacement("\\/"));
        return newData;
    }

    /**
     * Click on the last pagination
     *
     * @return - true if pagination is present
     */
    public Boolean clickOnLastOfPaginationIfExists() {
        Boolean isPaginationExist = false;
        if (applicationsPageObject.isPaginationPresent.isDisplayed()) {
            userActions.performActionWithPolling(applicationsPageObject.lastPage, UserAction.CLICK);
            isPaginationExist = true;
        }
        return isPaginationExist;
    }

    /* Validate message of not being able to add more than 2 options */
    public boolean validateGroupByMessage() {
        try {
            chargebackImpalaPageObject.selectOnly2Message.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /* Remove options from group by */
    public void remove1stGroupByOption() {
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.remove1stGroupBy.get(0));
        userActions.performActionWithPolling(chargebackImpalaPageObject.remove1stGroupBy.get(0), UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.remove1stGroupBy.get(0));

    }

    /* Validate if for set group by message is displayed */
    public boolean validatePieChartPresence(WebElement graphElement) {
        try {
            waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
            graphElement.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }

    /* Validate if message is present */
    public List<Boolean> validateMessageHaveGroupByValues(List<String> groupByList) {
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        String message = chargebackImpalaPageObject.showingResultHeader.getText().toLowerCase();
        LOGGER.info("Expected list of options in message- " + message);
        ArrayList<Boolean> isContainFilter = new ArrayList<Boolean>();
        for (String groupBy : groupByList) {
            waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
            if (message.contains(groupBy)) {
                isContainFilter.add(true);
            } else
                isContainFilter.add(false);
        }
        return isContainFilter;
    }

    public void click1stRowOfTable() {
        if (chargebackImpalaPageObject.firstRowOfTable.size() > 0) {
            waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.firstRowOfTable.get(0));
            userActions.performActionWithPolling(chargebackImpalaPageObject.firstRowOfTable.get(0), UserAction.CLICK);
            waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        } else {
            LOGGER.info("No further rows to click on in table");
        }
    }

    public void validateJobsPieCharts() {
        List<WebElement> groupOptions = chargebackImpalaPageObject.pieChartGroupByOptions;
        Boolean isGroupingDisplayed = false;
        if (groupOptions.size() > 0) {
            isGroupingDisplayed = true;
        }
        Assert.assertTrue(isGroupingDisplayed, "Group by option not displayed for pie chart");
    }

    public void validateGroupByPieChartOption() {
        Boolean isGroupingDisplayed = false;
        List<WebElement> elements = chargebackImpalaPageObject.pieChartGroupBySearchBoxs;
        if (elements.size() > 0) {
            isGroupingDisplayed = true;
        }
        Assert.assertTrue(isGroupingDisplayed, "Group by option not displayed for pie chart");
    }


}
