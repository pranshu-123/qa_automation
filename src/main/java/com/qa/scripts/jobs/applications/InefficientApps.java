package com.qa.scripts.jobs.applications;

import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

/**
 * @author Briender Kumar
 * This class contains all Jobs Application InefficientApps related action methods
 */
public class InefficientApps {
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private ApplicationsPageObject applicationsPageObject;
    private static final Logger LOGGER = Logger.getLogger(InefficientApps.class.getName());

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public InefficientApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        applicationsPageObject = new ApplicationsPageObject(driver);
    }

    /* Check for InefficientApps Table */
    public Boolean verifyInefficientTbl(){
        waitExecuter.waitUntilElementPresent(applicationsPageObject.tblInefficientApps);
        LOGGER.info("Inefficient Rows List: "+applicationsPageObject.tblInefficientAppsRowsList.size());
        Boolean boolTbl = false;
        if(applicationsPageObject.tblInefficientAppsRowsList.size() > 0){
            boolTbl = true;
        }
        return boolTbl;
    }

}
