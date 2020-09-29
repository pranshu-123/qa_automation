package com.qa.scripts.jobs.applications;

import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;

public class InefficientApps {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private ApplicationsPageObject applicationsPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     * @param driver
     * - WebDriver instance
     */
    public InefficientApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        applicationsPageObject = new ApplicationsPageObject(driver);
    }

    public Boolean verifyInefficientTbl(){
        waitExecuter.waitUntilElementPresent(applicationsPageObject.tblInefficientApps);
        System.out.println(applicationsPageObject.tblInefficientAppsRowsList.size());
        Boolean boolTbl = false;
        if(applicationsPageObject.tblInefficientAppsRowsList.size() > 0){
            boolTbl = true;
        }

        return boolTbl;
    }


}
