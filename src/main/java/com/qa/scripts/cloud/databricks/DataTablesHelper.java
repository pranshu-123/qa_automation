package com.qa.scripts.cloud.databricks;

import com.qa.constants.DirectoryConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.utils.*;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 * This class contains methods or action to be perform on DataPage.
 */
public class DataTablesHelper {

    private final DataPageObject dataPageObject;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ExtentTest test;
    private final UserActions actions;
    private final ActionPerformer actionPerformer;
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());


    public DataTablesHelper(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.test = extentTest;
        this.dataPageObject = new DataPageObject(driver);
        this.waitExecuter = new WaitExecuter(driver);
        this.actions = new UserActions(driver);
        this.actionPerformer = new ActionPerformer(driver);
    }

    /**
     * Click on the data tab present on top of the page
     */
    public void clickOnDataTab() {
        this.actions.performActionWithPolling(dataPageObject.dataTab, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        loggingUtils.info("Click on data tab", test);
    }

    /**
     * Click on the tables tab present in data page
     */
    public void clickOnDataTablesTab() {
        this.actions.performActionWithPolling(this.dataPageObject.dataTablesTab, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        loggingUtils.info("Click on data tables tab", test);
    }

    /**
     * Check element present on data page
     */
    public boolean checkIfElementPresent(WebElement element) {
        try {
            waitExecuter.waitUntilElementPresent(element);
        } catch (NoSuchElementException elementException) {
            return false;
        }
        return true;
    }

    /**
     * Click on workspace dropdown
     */
    public void clickOnWorkspaceDropdown() {
        this.actions.performActionWithPolling(dataPageObject.workspaceDropdown, UserAction.CLICK);
    }

    /**
     * Select workspace which has metastore configured
     */
    public void selectWorkspaceForConfiguredMetastore() {
        loggingUtils.info("Click on workspace dropdown", test);
        clickOnWorkspaceDropdown();
        List<WebElement> dropDownValues = dataPageObject.dropdownValues;
        Boolean isMetastorePresent = false;
        for (int i=0; i<dropDownValues.size(); i++) {
            WebElement dropDownValue = dropDownValues.get(i);
            String dropdownValueText = dropDownValue.getText();
            actions.performActionWithPolling(dropDownValue, UserAction.CLICK);
            loggingUtils.info(String.format("Selecting dropdown value %s", dropdownValueText), test);
            if (dataPageObject.metastoreParentElement.getText().trim().equals("")) {
                clickOnWorkspaceDropdown();
                dropDownValues = dataPageObject.dropdownValues;
            } else {
                isMetastorePresent = true;
                loggingUtils.info(String.format("Metastore present for selected workspace %s",
                        dropdownValueText), test);
                break;
            }
        }
        Assert.assertTrue(isMetastorePresent, "Metastore not present");
    }



    /**
     * Check whether data table is loaded
     */
    public boolean isTableLoadedWithData() {
        List<WebElement> dataTableHeading = dataPageObject.tableHeadings;
        List<String> expectedHeadings = Arrays.asList("Database", "Table", "Owner", "Path", "Table Type",
            "File System", "Storage Format", "Created", "Latest Access", "Size", "Apps", "Partitions",
            "Users", "More Info", "");
        if (dataTableHeading.size() == 0) {
            return false;
        } else {
            dataTableHeading.stream().forEach(heading -> Assert.assertTrue(expectedHeadings.contains(heading.getText().trim())
                ,String.format("Mismatch in heading for %s", heading.getText().trim())));
        }
        if (dataPageObject.tableRows.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void verifyTableFilterByColumn(String column) throws InterruptedException {
        int columnIndex = -1;
        column = column.trim();
        switch (column) {
            case "Database":
                columnIndex = 1;
                break;
            case "Table":
                columnIndex = 2;
                break;
            case "Owner":
                columnIndex = 3;
                break;
            case "Path":
                columnIndex = 4;
                break;
            case "Table Type":
                columnIndex = 5;
                break;
            case "File System":
                columnIndex = 6;
                break;
            case "Storage Format":
                columnIndex = 7;
                break;
            case "Created":
                columnIndex = 8;
                break;
            case "Latest Access":
                columnIndex = 9;
                break;
            case "Size":
                columnIndex = 10;
                break;
            case "Apps":
                columnIndex = 11;
                break;
            case "Partitions":
                columnIndex = 12;
                break;
            case "Users":
                columnIndex = 13;
                break;
        }
        String textToFilter =
            dataPageObject.tableRows.get(0).findElement(By.xpath("//td[" + (columnIndex+1) + "]")).getText();
        textToFilter = !textToFilter.endsWith("...") ? textToFilter : textToFilter.substring(0, textToFilter.indexOf(
            "..."));
        actions.performActionWithPolling(dataPageObject.searchBoxForTableData, UserAction.SEND_KEYS, textToFilter);
        actions.performActionWithPolling(dataPageObject.searchBoxButton, UserAction.CLICK);
        waitExecuter.waitForSeconds(5);

        loggingUtils.info("Sorting data in descending order by column " + column, test);
        actions.performActionWithPolling(dataPageObject.tableHeadings.get(columnIndex), UserAction.CLICK);
        waitExecuter.waitForSeconds(3);
        for (WebElement row: dataPageObject.tableRows) {
            if (row.findElements(By.xpath("//td")).size() != 1) {
                String columnDataText = row.findElement(By.xpath("//td[" + (columnIndex+1) + "]")).getText();
                if (!column.equals("Latest Access")) {
                    try {
                        Assert.assertEquals(columnDataText, textToFilter, "");
                    } catch (AssertionError assertionError) {
                        if (!row.getText().toLowerCase().trim().contains(textToFilter.toLowerCase())) {
                            throw assertionError;
                        }
                    }
                }
            } else {
                loggingUtils.error("No Tables Available displayed", test);
            }
        }

        loggingUtils.info("Sorting data in ascending order by column " + column, test);
        actions.performActionWithPolling(dataPageObject.tableHeadings.get(columnIndex), UserAction.CLICK);
        for (WebElement row: dataPageObject.tableRows) {
            if (row.findElements(By.xpath("//td")).size() != 1) {
                String columnDataText = row.findElement(By.xpath("//td[" + (columnIndex+1) + "]")).getText();
                if (!column.equals("Latest Access")) {
                    try {
                        Assert.assertEquals(columnDataText, textToFilter, "");
                    } catch (AssertionError assertionError) {
                        if (!row.getText().toLowerCase().trim().contains(textToFilter)) {
                            throw assertionError;
                        }
                    }
                }
            } else {
                loggingUtils.error("No Tables Available displayed", test);
            }
        }
    }

    /**
     * Click on download CSV button and return the File object of
     * downloaded file
     * @return - Downloaded File Object
     */
    public File downloadDataTable() {
        actions.performActionWithPolling(dataPageObject.downloadCSVButton, UserAction.CLICK);
        waitExecuter.sleep(5000);
        String downloadDirPath = DirectoryConstants.getDownloadsDir();
        File downloadedCSVFile = new File(downloadDirPath + File.separator + "data.csv");
        return downloadedCSVFile;
    }

    /**
     * Get Cell values of nth row of data table
     * @param rowNum - Row number to get the info
     * @return - Cell Values list of String
     */
    public List<String>  getCellValuesOfNthRow(int rowNum) {
        WebElement row = dataPageObject.tableRows.get(rowNum);
        List<WebElement> rowValues = row.findElements(By.xpath("//td"));
        List<String> cellStrValue = rowValues.stream().map(rowValue -> rowValue.getText().trim())
            .collect(Collectors.toList());
        return cellStrValue;
    }

    /**
     * Click on more info button of Nth row
     * @param rowNum - Row Number to get info
     */
    public void clickOnMoreInfoOfNthRow(int rowNum) {
        int scrollY = 600;
        JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //executor.executeScript("arguments[0].scrollIntoView(true);", dataPageObject.logScrollable);
        WebElement row = dataPageObject.tableRows.get(rowNum);
        actions.performActionWithPolling(dataPageObject.moreInfo, UserAction.CLICK);
        waitExecuter.sleep(4000);
    }

    /**
     * Go back to tables page from table details page
     */
    public void backToTablesPage() {
        loggingUtils.info("Go back to tables page", test);
        actions.performActionWithPolling(dataPageObject.backToTablesLinkOnTableDetails, UserAction.CLICK);
    }
    /**
     * Go back to home page from table details page
     */
    public void backToHomePage() {
        loggingUtils.info("Go back to home page", test);
        actions.performActionWithPolling(dataPageObject.homeTab, UserAction.CLICK);
    }

    /**
     * Click on tab on table details page
     */
    public void clickOnTabOnTableDetails(String tabName) {
        switch (tabName.toLowerCase().trim()) {
            case "analysis":
                loggingUtils.info("Click on Analysis tab", test);
                actions.performActionWithPolling(dataPageObject.analysisTabOnTableDetails, UserAction.CLICK);
                break;
            case "applications":
                loggingUtils.info("Click on Applications tab", test);
                actions.performActionWithPolling(dataPageObject.applicationTabOnTableDetails, UserAction.CLICK);
                break;
            case "partition detail":
                loggingUtils.info("Click on Partition Detail tab", test);
                actions.performActionWithPolling(dataPageObject.partitionDetailTabOnTableDetails, UserAction.CLICK);
                break;
            default:
                backToTablesPage();
                loggingUtils.error("Incorrect tab name is passed to click", test);
                break;
        }
    }

    /**
     * Get toollTip values for loaded graph
     * @param graphType - Users/Apps/Size
     * @return - Tooltip value
     */
    public String getTooltipValuesOfLoadedGraph(String graphType) {
        if (dataPageObject.displayedGraphs.size() > 0) {
            switch (graphType.toLowerCase().trim()) {
                case "users":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(0), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(0), 100, 100);
                    String usersTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Users graph displayed. Tooltip loaded: " + usersTooltip , test);
                    return usersTooltip;
                case "apps":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(1), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(1), 100, 100);
                    String appsTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Apps graph displayed. Tooltip loaded: " + appsTooltip , test);
                    return appsTooltip;
                case "size":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(2), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(2), 100, 100);
                    String sizeTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + sizeTooltip , test);
                    return sizeTooltip;
                default:
                    loggingUtils.error("Incorrect value is passed for graph", test);
                    return "";
            }
        } else {
            return "";
        }
    }


    /**
     * Click filter by table state or events
     */
    public void filterByTableStateOrEvents(String label) {
        WebElement element = dataPageObject.tablesFilterItems.stream().filter(item -> item.getText().toLowerCase()
            .contains(label.toLowerCase())).collect(Collectors.toList()).get(0);
        waitExecuter.sleep(300);
        actionPerformer.moveToTheElement(dataPageObject.tableEventsFilterLabel);
        actionPerformer.moveToTheElement(element);
        actions.performActionWithPolling(element.findElement(By.xpath("a[@class='state-show-only']")), UserAction.CLICK);
    }

    /**
     * Get applied state filter of hot and cold tables
     * @return - Slider values for hot and cold
     */
    public List<Integer> getAppliedStateFilter() {
        int hotByAge, coldByAge, hotByLatestAccess, coldByLatestAccess;
        try {
            loggingUtils.info("Click on setting icon on table state", test);
            actions.performActionWithPolling(dataPageObject.tableStateSettings, UserAction.CLICK);
            waitExecuter.sleep(3000);
            actionPerformer.moveToTheElement(dataPageObject.labelTablesSlider.get(0));
            hotByAge = Integer.valueOf(dataPageObject.slidersTooltipValues.get(0).getText());
            waitExecuter.sleep(500);
            actionPerformer.moveToTheElement(dataPageObject.labelTablesSlider.get(2));
            coldByAge = Integer.valueOf(dataPageObject.slidersTooltipValues.get(2).getText());
            waitExecuter.sleep(500);
            actionPerformer.moveToTheElement(dataPageObject.labelTablesSlider.get(1));
            hotByLatestAccess = Integer.valueOf(dataPageObject.slidersTooltipValues.get(1).getText());
            waitExecuter.sleep(500);
            actionPerformer.moveToTheElement(dataPageObject.labelTablesSlider.get(3));
            coldByLatestAccess = Integer.valueOf(dataPageObject.slidersTooltipValues.get(3).getText());
        } finally {
            actions.performActionWithPolling(dataPageObject.modalCloseButton, UserAction.CLICK);
        }
        return Arrays.asList(hotByAge, coldByAge, hotByLatestAccess, coldByLatestAccess);
    }

    /**
     * Get applied state filter of hot and cold tables
     * @return - Slider values for hot and cold
     */
    public void changeAppliedStateSettings(String settingType) throws InterruptedException {
        try {
            loggingUtils.info("Click on setting icon on table state", test);
            waitExecuter.waitForSeconds(5);
            actions.performActionWithPolling(dataPageObject.tableStateSettings, UserAction.CLICK);
            waitExecuter.waitForSeconds(10);
            waitExecuter.sleep(3000);
            if (settingType.equalsIgnoreCase("age")) {
                actionPerformer.slideElementHorizontallyByOffset(dataPageObject.labelTablesSlider.get(0), -20);
                waitExecuter.sleep(1000);
                actionPerformer.slideElementHorizontallyByOffset(dataPageObject.labelTablesSlider.get(1),20);
            } else {
                actionPerformer.slideElementHorizontallyByOffset(dataPageObject.labelTablesSlider.get(2), -20);
                waitExecuter.sleep(1000);
                actionPerformer.slideElementHorizontallyByOffset(dataPageObject.labelTablesSlider.get(3),20);
            }
            waitExecuter.sleep(1000);
            actions.performActionWithPolling(dataPageObject.saveRulesButton, UserAction.CLICK);
        } finally {
            actions.performActionWithPolling(dataPageObject.modalCloseButton, UserAction.CLICK);
        }
    }

    public void checkIfTablesDisplayedBasedOnAppliedFilter(String label, int[] appliedSetting) {
        filterByTableStateOrEvents(label);
        if (dataPageObject.noTablesAvailableElement.size() > 0) {
            loggingUtils.info("No Tables present for hot", test);
        } else {
            File downloadedCSV = downloadDataTable();
            List<Object> createdDates = readCSVFileForColumn(downloadedCSV, "Created");
            List<Object> latestAccessDates = readCSVFileForColumn(downloadedCSV, "Latest Access");

            for (int i=0; i<createdDates.size(); i++) {
                if (label.equalsIgnoreCase("hot")) {
                    Assert.assertTrue(((Date)createdDates.get(i)).after(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[0])) ||
                        ((Date)latestAccessDates.get(i)).after(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[1])));
                } else if (label.equalsIgnoreCase("cold")){
                    Assert.assertTrue(((Date)createdDates.get(i)).before(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[0])) ||
                        ((Date)latestAccessDates.get(i)).before(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[1])));
                }  else {
                    Assert.assertTrue((((Date)createdDates.get(i)).before(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[0])) &&
                            ((Date)createdDates.get(i)).after(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[1]))) ||
                            (((Date)createdDates.get(i)).before(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[2])) &&
                                    ((Date)createdDates.get(i)).after(DateUtils.getDateDifferenceFromCurrentDate(-appliedSetting[3]))));
                }
            }
        }
    }

    /**
     * Read column value from the CSV file
     */
    public List<Object> readCSVFileForColumn(File csvFile, String column) {
        try (FileReader fileReader = new FileReader(csvFile)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            switch (column.trim().toLowerCase()) {
                case "created":
                    return bufferedReader.lines().skip(1).map(line -> DateUtils.getDateWithDateString(line.split(",")[7].replaceAll("[\"=]","")))
                        .collect(Collectors.toList());
                case "latest access":
                    return bufferedReader.lines().skip(1).map(line -> DateUtils.getDateWithDateString(line.split(",")[8].replaceAll("[\"=]","")))
                        .collect(Collectors.toList());
                default:
                    throw new IllegalStateException("Unexpected value: " + column.trim().toLowerCase());
            }
        } catch (IOException io) {
            return null;
        }
    }

    /**
     * Select All applications columns to display on UI
     * Data -> Tables -> Table Details -> Applications
     */
    public void selectAllApplicationsColumn() {
        actions.performActionWithPolling(dataPageObject.settingsIcon, UserAction.CLICK);
        List<WebElement> columnCheckboxes = dataPageObject.columnCheckboxes;
        columnCheckboxes.stream().forEach(checkbox -> {
            if (!checkbox.isSelected()) {
                actions.performActionWithPolling(checkbox.findElement(By.xpath("parent::label")), UserAction.CLICK);
            }
        });
        actions.performActionWithPolling(dataPageObject.settingsIcon, UserAction.CLICK);
    }

    /**
     * Read column values from application table
     */
    public List<String> getColumnValuesFromApplicationsTable(String column) {
        switch (column.toLowerCase().trim()) {
            case "type":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(0).getText()).collect(Collectors.toList());
            case "status":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(1).getText()).collect(Collectors.toList());
            case "user":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(2).getText()).collect(Collectors.toList());
            case "app name / id":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(3).getText()).collect(Collectors.toList());
            case "insights":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(4).getText()).collect(Collectors.toList());
            case "cluster id":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(5).getText()).collect(Collectors.toList());
            case "start time":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(6).getText()).collect(Collectors.toList());
            case "duration":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(7).getText()).collect(Collectors.toList());
            case "queue":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(8).getText()).collect(Collectors.toList());
            case "read":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(9).getText()).collect(Collectors.toList());
            case "write":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(10).getText()).collect(Collectors.toList());
            case "events":
                return dataPageObject.tableRows.stream().map(row -> row.findElements(By.tagName("td"))
                    .get(12).getText()).collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Click on parent app column of nth row
     * @param rowNum
     */
    public void clickOnParentAppOfNthRow(int rowNum) {
        actions.performActionWithPolling(dataPageObject.tableRows.get(rowNum).findElements(By.tagName("td"))
            .get(13),UserAction.CLICK);
    }

    /**
     * Click on parent app column of nth row
     * @param rowNum
     */
    public void clickOnParentApp(int rowNum) {
        actions.performActionWithPolling(dataPageObject.parentRows.get(rowNum), UserAction.CLICK);
    }

    /**
     * Click on close button to close application details page which navigates user
     * to table details page
     */
    public void homePage() {
        loggingUtils.info("Click on close button, go back to table details page", test);
        actions.performActionWithPolling(dataPageObject.homeTab, UserAction.CLICK);
    }


    /**
     * Click on close button to close application details page which navigates user
     * to table details page
     */
    public void closeApplicationDetailsPage() {
        loggingUtils.info("Click on close button, go back to table details page", test);
        actions.performActionWithPolling(dataPageObject.closeApplicationsDetailsButton, UserAction.CLICK);
    }
}
