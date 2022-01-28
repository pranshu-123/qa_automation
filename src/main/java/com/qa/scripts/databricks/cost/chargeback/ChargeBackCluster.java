package com.qa.scripts.databricks.cost.chargeback;

import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class ChargeBackCluster {
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
    public ChargeBackCluster(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        applicationsPageObject = new ApplicationsPageObject(driver);
        userActions = new UserActions(driver);



    }
}

