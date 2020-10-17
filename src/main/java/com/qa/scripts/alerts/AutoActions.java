package com.qa.scripts.alerts;

import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Birender Kumar
 */
public class AutoActions {

    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private AutoActionsPageObject aaPageObject;

    Logger logger = LoggerFactory.getLogger(AutoActions.class);

    /**
     * Constructer to initialize wait, driver and necessary objects
     * @param driver
     *            - WebDriver instance
     */
    public AutoActions(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        aaPageObject = new AutoActionsPageObject(driver);
    }


    public String getAutoActionsHeader(){
        waitExecuter.sleep(1000);
        logger.info("Auto Actions component Header: "+ aaPageObject.autoActionComponentHeader.getText());
        return aaPageObject.autoActionComponentHeader.getText();
    }

}
