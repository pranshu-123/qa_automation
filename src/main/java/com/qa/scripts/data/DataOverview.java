package com.qa.scripts.data;

import com.qa.enums.UserAction;
import com.qa.pagefactory.data.DataOverviewPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataOverview {
        private final DataOverviewPageObject overviewPageObject;
        private final WaitExecuter waitExecuter;
        private final WebDriver driver;
        private final ExtentTest test;
        private final UserActions actions;
        private final ActionPerformer actionPerformer;
        private final LoggingUtils loggingUtils = new LoggingUtils(this.getClass());

        public DataOverview(WebDriver driver, ExtentTest extentTest) {
            this.driver = driver;
            this.test = extentTest;
            this.overviewPageObject = new DataOverviewPageObject(driver);
            this.waitExecuter = new WaitExecuter(driver);
            this.actions = new UserActions(driver);
            this.actionPerformer = new ActionPerformer(driver);
        }

        /**
         * get last day KPIs data displayed on data overview page
         */
        public Map<String,String> getLastDayTablesKPIs() {
            Map<String,String> lastDayTablesKPIsKV = new HashMap<>();
            if(overviewPageObject.tableKPIsElements.size() > 0) {
                for (int i = 0; i < overviewPageObject.tablesKPIsLastDayTitles.size(); i++) {
                    lastDayTablesKPIsKV.put(overviewPageObject.tablesKPIsLastDayTitles.get(i).getText(),
                            overviewPageObject.tablesKPIsLastDayValues.get(i).getText());
                    waitExecuter.sleep(1000);
                }
            }
            else  {
                int tablevalue=overviewPageObject.noTableKPIsElements.size();
                loggingUtils.info("No Table KPIs present."+tablevalue, test);
            }
            return lastDayTablesKPIsKV;
        }

    /**
     * Click on the data tab present on top of the page
     */
    public void clickOnDataTab() {
        this.actions.performActionWithPolling(overviewPageObject.dataTab, UserAction.CLICK);
        waitExecuter.waitUntilPageFullyLoaded();
        loggingUtils.info("Click on data tab", test);
    }

    /**
     * Select workspace which has metastore configured
     */
    public void selectWorkspaceForConfiguredMetastore() {
        loggingUtils.info("Click on workspace dropdown", test);
        clickOnWorkspaceDropdown();
        List<WebElement> dropDownValues = overviewPageObject.dropdownValues;
        Boolean isMetastorePresent = false;
        for (int i=0; i<dropDownValues.size(); i++) {
            WebElement dropDownValue = dropDownValues.get(i);
            String dropdownValueText = dropDownValue.getText();
            actions.performActionWithPolling(dropDownValue, UserAction.CLICK);
            loggingUtils.info(String.format("Selecting dropdown value %s", dropdownValueText), test);
            if (overviewPageObject.metastoreParentElement.getText().trim().equals("")) {
                clickOnWorkspaceDropdown();
                dropDownValues = overviewPageObject.dropdownValues;
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
        this.actions.performActionWithPolling(overviewPageObject.workspaceDropdown, UserAction.CLICK);
    }
}
