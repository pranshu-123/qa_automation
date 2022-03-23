package com.qa.scripts.cloud.databricks;

import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ankur Jaiswal
 * This class contains script for data overview page of Databricks
 */
public class DataOverviewHelper {
    private final DataPageObject dataPageObject;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ExtentTest test;
    private final UserActions actions;
    private final ActionPerformer actionPerformer;
    private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

    public DataOverviewHelper(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.test = extentTest;
        this.dataPageObject = new DataPageObject(driver);
        this.waitExecuter = new WaitExecuter(driver);
        this.actions = new UserActions(driver);
        this.actionPerformer = new ActionPerformer(driver);
    }

    /**
     * get last day KPIs data displayed on data overview page
     */
    public Map<String,String> getLastDayTablesKPIs() {
        Map<String,String> lastDayTablesKPIsKV = new HashMap<>();
        if (dataPageObject.noTableKPIsElements.size() > 0) {
            loggingUtils.info("No Table KPIs present.", test);
        } else {
            for (int i=0; i<dataPageObject.tablesKPIsLastDayTitles.size(); i++) {
                lastDayTablesKPIsKV.put(dataPageObject.tablesKPIsLastDayTitles.get(i).getText(),
                        dataPageObject.tablesKPIsLastDayValues.get(i).getText());
            }
        }
        return lastDayTablesKPIsKV;
    }


    /**
     * get last day KPIs data displayed on data overview page
     */
    public Map<String,String> getLastDayPartitionKPIs() {
        Map<String,String> lastDayPartitionsKPIsKV = new HashMap<>();
        if (dataPageObject.noPartitionKPIsElements.size() > 0) {
            loggingUtils.info("No Table KPIs present.", test);
        } else {
            for (int i=0; i<dataPageObject.partitionsKPIsLastDayTitles.size(); i++) {
                lastDayPartitionsKPIsKV.put(dataPageObject.partitionsKPIsLastDayTitles.get(i).getText(),
                    dataPageObject.partitionsKPIsLastDayValues.get(i).getText());
            }
        }
        return lastDayPartitionsKPIsKV;
    }

    /**
     * Get toollTip values for loaded graph
     * @param graphType - Users/Apps/Size
     * @return - Tooltip value
     */
    public String getTooltipValuesOfLoadedGraph(String graphType) {
        if (dataPageObject.displayedGraphs.size() > 0) {
            switch (graphType.toLowerCase().trim()) {
                case "number of tables created":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(0), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(0), 50, 50);
                    String numberOfTablesCreatedTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Users graph displayed. Tooltip loaded: " + numberOfTablesCreatedTooltip , test);
                    return numberOfTablesCreatedTooltip;
                case "total number of tables":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(1), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(1), 50, 50);
                    String totalNumberOfTablesTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Apps graph displayed. Tooltip loaded: " + totalNumberOfTablesTooltip , test);
                    return totalNumberOfTablesTooltip;
                case "number of tables accessed":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(2), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(2), 50, 50);
                    String numberOfTablesAccessedTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + numberOfTablesAccessedTooltip , test);
                    return numberOfTablesAccessedTooltip;
                case "number of queries":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(3), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(3), 50, 50);
                    String numberOfQueriesTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + numberOfQueriesTooltip , test);
                    return numberOfQueriesTooltip;
                case "number of users":
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(4), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(4), 50, 50);
                    String numberOfUsersTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + numberOfUsersTooltip , test);
                    return numberOfUsersTooltip;
                case "number of partitions created":
                    actionPerformer.moveToTheElement(dataPageObject.displayedGraphs.get(6));
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(6), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(6), 50, 50);
                    String numberOfPartitionsCreatedTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + numberOfPartitionsCreatedTooltip , test);
                    return numberOfPartitionsCreatedTooltip;
                case "total number of partitions":
                    actionPerformer.moveToTheElement(dataPageObject.displayedGraphs.get(7));
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(7), 0, 0);
                    actionPerformer.moveToTheElementByOffset(dataPageObject.displayedGraphs.get(7), 50, 50);
                    String totalNumberOfPartitionsTooltip = dataPageObject.graphsTooltips.get(0).getText();
                    loggingUtils.pass("Size graph displayed. Tooltip loaded: " + totalNumberOfPartitionsTooltip , test);
                    return totalNumberOfPartitionsTooltip;
                default:
                    loggingUtils.error("Incorrect value is passed for graph", test);
                    return "";
            }
        } else {
            return "";
        }
    }

    /**
     * Get Table State Values
     */
    public Map<String,String> getTableStatePieChartValues() {
        actionPerformer.moveToTheElement(dataPageObject.tableStateGraphFooter);
        waitExecuter.sleep(2000);
        WebElement graphFooter = dataPageObject.tableStateGraphFooter;
        Map<String,String> tableStateValues = new HashMap<>();
        List<String> tableStates =
                graphFooter.findElements(By.tagName("label")).stream().map(element -> element.getText().replaceAll(
                        "[\\n\\r]","")).collect(Collectors.toList());
        Assert.assertTrue(tableStates.size() > 0, "Label is not displayed for pie chart");
        for (String tableState : tableStates) {
            tableStateValues.put(tableState.split(" ")[0],
                    tableState.split(" ")[1].replaceAll("[()]",""));
        }
        return tableStateValues;
    }

    /**
     * Get applied state filter of hot and cold tables
     * @return - Slider values for hot and cold
     */
    public Boolean changeAppliedStateSettings(String settingType) {
        try {
            loggingUtils.info("Click on setting icon on table state", test);
            actionPerformer.moveToTheElement(dataPageObject.settingsIcon);
            actions.performActionWithPolling(dataPageObject.settingsIcon, UserAction.CLICK);
            waitExecuter.waitUntilPageFullyLoaded();
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
        return true;
    }

    public void selectOnlyTableState(String tableState) {
        List<WebElement> tableStatesLabels = dataPageObject.tableStateGraphFooter.findElements(By.tagName("label"));
        switch (tableState.toLowerCase()) {
            case "hot": {
                actions.performActionWithPolling(tableStatesLabels.get(1), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(2), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(3), UserAction.CLICK);
                Assert.assertTrue(tableStatesLabels.get(0).getText().trim().toLowerCase()
                    .contains(dataPageObject.pieChartValue.getText().trim().toLowerCase()), "Value does not match for" +
                    " only selection of Hot");
                break;
            }
            case "warm": {
                actions.performActionWithPolling(tableStatesLabels.get(0), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(2), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(3), UserAction.CLICK);
                Assert.assertTrue(tableStatesLabels.get(1).getText().trim().toLowerCase()
                    .contains(dataPageObject.pieChartValue.getText().trim().toLowerCase()), "Value does not match for" +
                    " only selection of Warm");
                break;
            }
            case "cold": {
                actions.performActionWithPolling(tableStatesLabels.get(0), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(1), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(3), UserAction.CLICK);
                Assert.assertTrue(tableStatesLabels.get(2).getText().trim().toLowerCase()
                    .contains(dataPageObject.pieChartValue.getText().trim().toLowerCase()), "Value does not match for" +
                    " only selection of Cold");
                break;
            }
            default: {
                actions.performActionWithPolling(tableStatesLabels.get(0), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(1), UserAction.CLICK);
                actions.performActionWithPolling(tableStatesLabels.get(2), UserAction.CLICK);
                Assert.assertTrue(tableStatesLabels.get(3).getText().trim().toLowerCase()
                    .contains(dataPageObject.pieChartValue.getText().trim().toLowerCase()), "Value does not match for" +
                    " only selection of Unknown");
            }
        }
        actionPerformer.moveToTheElement(dataPageObject.tableStatePieChart);
    }
}
