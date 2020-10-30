package com.qa.scripts.jobs.applications;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * @author Ankur Jaiswal
 * This class contains all actions or activity perform on Jobs Page.
 */
public class Jobs {
    private TopPanelPageObject topPanelPageObject;
    private SubTopPanelModulePageObject subTopPanelModulePageObject;
    private WebDriver driver;
    private LoggingUtils logger = new LoggingUtils(Jobs.class);

    /**
     * @param driver WebDriver instance
     */
    public Jobs(WebDriver driver) {
        this.driver = driver;
        topPanelPageObject = new TopPanelPageObject(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
    }

    /**
     * Click on Jobs Pipeline page
     * Actions Click On Cluster > Jobs > Pipelines
     */
    public void clickOnJobsPipelines() {
        MouseActions.clickOnElement(driver, topPanelPageObject.jobsTab);
        MouseActions.clickOnElement(driver,subTopPanelModulePageObject.pipelinesTab);
    }
}
