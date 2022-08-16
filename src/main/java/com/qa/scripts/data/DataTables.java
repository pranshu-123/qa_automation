package com.qa.scripts.data;

import com.qa.enums.UserAction;
import com.qa.pagefactory.cloud.databricks.DataPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

public class DataTables{
        private final DataPageObject dataPageObject;
        private final WaitExecuter waitExecuter;
        private final WebDriver driver;
        private final ExtentTest test;
        private final UserActions actions;
        private final ActionPerformer actionPerformer;
        private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());


        public DataTables(WebDriver driver, ExtentTest extentTest) {
            this.driver = driver;
            this.test = extentTest;
            this.dataPageObject = new DataPageObject(driver);
            this.waitExecuter = new WaitExecuter(driver);
            this.actions = new UserActions(driver);
            this.actionPerformer = new ActionPerformer(driver);
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
     * Click on the tables tab present in data page
     */
    public void clickOnDataTablesTab() {
        this.actions.performActionWithPolling(this.dataPageObject.dataTablesTab, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        loggingUtils.info("Click on data tables tab", test);
    }

    public void clickOnDataTab(String dataPage) {
        try {
            waitExecuter.sleep(3000);
            test.log(LogStatus.INFO, "Verified Data Tab is clicked.");
            if (dataPage.equalsIgnoreCase("Data")) {
                this.actions.performActionWithPolling(dataPageObject.dataTab, UserAction.CLICK);
            } else if (dataPage.equalsIgnoreCase("Tables")) {
                this.actions.performActionWithPolling(this.dataPageObject.dataTablesTab, UserAction.CLICK);
            } else if (dataPage.equalsIgnoreCase("Workspace")) {
                this.actions.performActionWithPolling(dataPageObject.workspaceDropdown, UserAction.CLICK);
                waitExecuter.waitUntilElementPresent(dataPageObject.dataTab);
            }
        } catch (InvalidElementStateException e) {
            loggingUtils.error("Could not click on data tab" + e, test);
        } catch (WebDriverException e) {
            e.printStackTrace();
            loggingUtils.error("Unknown exception occured while click  data tab" + e, test);
        }
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
     * Click on workspace dropdown
     */
    public void clickOnWorkspaceDropdown() {
        this.actions.performActionWithPolling(dataPageObject.workspaceDropdown, UserAction.CLICK);
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
}
