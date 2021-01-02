package com.qa.scripts.migration;

import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.ClusterDiscoveryPageObject;
import com.qa.pagefactory.migration.WorkloadFitPageObject;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;

import java.util.List;
import java.util.logging.Logger;

public class WorkloadFit {
    private static final Logger LOGGER = Logger.getLogger(WorkloadFit.class.getName());
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final TopPanelPageObject topPanelPageObject;
    private final UserActions userAction;
    private final SubTopPanelModulePageObject topPanelComponentPageObject;
    private final ClusterDiscoveryPageObject cdPageObject;
    private final DatePickerPageObject datePickerPageObject;
    private final JavascriptExecutor jse;
    private final WorkloadFitPageObject fitPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public WorkloadFit(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        userAction = new UserActions(driver);
        topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        cdPageObject = new ClusterDiscoveryPageObject(driver);
        datePickerPageObject = new DatePickerPageObject(driver);
        jse = (JavascriptExecutor) driver;
        fitPageObject = new WorkloadFitPageObject(driver);

    }

    /* Close last run message banner */
    public void closeConfirmationMessageNotification() {
        if (cdPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(cdPageObject.confirmationMessageElementClose.get(0));
            userAction.performActionWithPolling(cdPageObject.confirmationMessageElementClose.get(0), UserAction.CLICK);
        }
    }

    /* To zoom out of the page by defining percentage of zoom out */
    public void zoomOut(String percentage) {
        jse.executeScript("document.body.style.zoom = " + "'" + percentage + "%';");

    }

    /* Navigate to Cluster Discovery Tab */
    public void navigateToWorkloadFit() {
        LOGGER.info("Navigate to Cluster Discovery tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.migrationTab);
        userAction.performActionWithPolling(topPanelPageObject.migrationTab, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.WorkloadFitTab);
        // Click on Cluster Discovery tab
        LOGGER.info("Clicked on Workload Fit tab");
        userAction.performActionWithPolling(topPanelComponentPageObject.WorkloadFitTab, UserAction.CLICK);
        // Validate Cluster Discovery tab loaded successfully
        LOGGER.info("Validate Workload Fit Discovery tab loaded successfully");
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Select last 7 days */
    public void selectLast7Days() {
        LOGGER.info("Click on date range");
        userAction.performActionWithPolling(datePickerPageObject.dateRange, UserAction.CLICK);
        userAction.performActionWithPolling(datePickerPageObject.last7Days, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(fitPageObject.generateHeatMap);

    }

    /* Select all Job/Tag/Queue/User types */
    public void selectAllCheckboxTypes(List<WebElement> isAllCheckboxPresent, WebElement selectAllCheckbox) {
        try {
            if (isAllCheckboxPresent.size() != 0) {
                userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);

                if (fitPageObject.noJobSelectedErrorMessage.size() > 0) {
                    userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);
                } else {
                    LOGGER.info("All checkbox already selected.");
                }
            } else {
                LOGGER.info("There are no options for selected duration");
            }
        } catch (NoSuchElementException ex) {
            LOGGER.info("There are no options for selected duration");
        }
    }

    /* To create a new report click on Run button */
    public void clickRunForNewReport() {
        try {
            LOGGER.info("Click on Run button to open report page");
            userAction.performActionWithPolling(cdPageObject.runButton, UserAction.CLICK);
            waitExecuter.sleep(1000);
        } catch (TimeoutException | NoSuchElementException ex) {

            try {
                LOGGER.info("Click on Edit Preferences to open report page");
                userAction.performActionWithPolling(fitPageObject.editPreferences, UserAction.CLICK);
            } catch (AssertionError | NoSuchElementException | TimeoutException exception) {
                exception.printStackTrace();
                LOGGER.info("Edit Preferences not present trying with Add preferences to open report page");
                userAction.performActionWithPolling(fitPageObject.addPreferences, UserAction.CLICK);
            }
            try {
                waitExecuter.waitUntilElementClickable(fitPageObject.vmPreferenceSearchBox);
                if (fitPageObject.activeVMPreference.size() > 0) {
                    LOGGER.info("Click on Set button if VM preference is already selected");
                    userAction.performActionWithPolling(fitPageObject.clickSetButton, UserAction.CLICK);
                    waitExecuter.waitUntilElementClickable(cdPageObject.runButton);
                    userAction.performActionWithPolling(cdPageObject.runButton, UserAction.CLICK);
                } else {
                    LOGGER.info("Click on Set button after selecting VM preference");
                    userAction.performActionWithPolling(fitPageObject.selectFirstVMPreference, UserAction.CLICK);
                    userAction.performActionWithPolling(fitPageObject.clickSetButton, UserAction.CLICK);
                    waitExecuter.waitUntilElementClickable(cdPageObject.runButton);
                    userAction.performActionWithPolling(cdPageObject.runButton, UserAction.CLICK);
                }
            } catch (TimeoutException exc) {
                exc.printStackTrace();
                LOGGER.info("Loader does not disappear from add preferences page");
                throw new SkipException("The loader does not disappeared from vm preference page.");
            }

        }
    }

    /* Click on Generate Heat Map */
    public void clickGenerateHeatMap() {
        LOGGER.info("Click on Generate Heat Map button");
        waitExecuter.waitUntilElementClickable(fitPageObject.generateHeatMap);
        userAction.performActionWithPolling(fitPageObject.generateHeatMap, UserAction.CLICK);
    }

    /* Select all tag option from pie charts */
    public void selectAllOptionsOfPieCharts() {
        selectAllCheckboxTypes(fitPageObject.AllTagOfJobTypes, fitPageObject.selectAllJobTypes);
        selectAllCheckboxTypes(fitPageObject.AllTagOfUserTypes, fitPageObject.selectAllUserTypes);
        selectAllCheckboxTypes(fitPageObject.AllTagOfQueuesTypes, fitPageObject.selectAllQueueTypes);
        selectAllCheckboxTypes(fitPageObject.AllTagOfTagsTypes, fitPageObject.selectAllTagTypes);
    }

    /* Select type of heat map */
    public void selectHeatMapTypeFromDropdown(String type) {
        Select select = new Select(fitPageObject.heatMapDropdown);
        select.selectByValue(type);
        waitExecuter.waitUntilElementClickable(fitPageObject.generateHeatMap);
    }

    /* Select single Job/Tag/Queue/User type */
    public void selectSingleCheckboxType(List<WebElement> isAllCheckboxPresent, WebElement selectAllCheckbox,
                                         WebElement selectFirstCheckBox) {
        try {
            if (isAllCheckboxPresent.size() != 0) {
                userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);

                if (fitPageObject.noJobSelectedErrorMessage.size() > 0) {
                    userAction.performActionWithPolling(selectFirstCheckBox, UserAction.CLICK);
                } else {
                    LOGGER.info("All checkbox already selected, deselecting all checkboxes to select signle checkbox.");
                    userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);
                    userAction.performActionWithPolling(selectFirstCheckBox, UserAction.CLICK);
                }
            } else {
                LOGGER.info("There are no options for selected duration");
            }
        } catch (NoSuchElementException ex) {
            LOGGER.info("There are no options for selected duration");
        }
    }

    /* De-Select All checkboxes for Job/Tag/Queue/User type */
    public void deselectAllCheckboxType(List<WebElement> isAllCheckboxPresent, WebElement selectAllCheckbox) {
        try {
            if (isAllCheckboxPresent.size() != 0) {
                userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);

                if (fitPageObject.noJobSelectedErrorMessage.size() > 0) {
                    LOGGER.info("De-selected all checkboxes");
                } else {
                    LOGGER.info("All checkbox already selected, deselecting all checkboxes to select signle checkbox.");
                    userAction.performActionWithPolling(selectAllCheckbox, UserAction.CLICK);
                    LOGGER.info("De-selected all checkboxes");
                }
            } else {
                LOGGER.info("There are no options for selected duration");
            }
        } catch (NoSuchElementException ex) {
            LOGGER.info("There are no options for selected duration");
        }
    }
}
