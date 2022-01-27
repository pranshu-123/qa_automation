package com.qa.scripts.databricks.jobs;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.logging.Logger;

public class AllApps {
    private static final Logger LOGGER = Logger.getLogger(com.qa.scripts.jobs.applications.AllApps.class.getName());
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ApplicationsPageObject applicationsPageObject;
    private final Actions action;
    private final SubTopPanelModulePageObject subTopPanelModulePageObject;
    private final DatePicker datePicker;
    private final UserActions userAction;

    /**
     * Constructer to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */

    public AllApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        action = new Actions(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        datePicker = new DatePicker(driver);
        userAction = new UserActions(driver);
        this.driver = driver;
    }
}
