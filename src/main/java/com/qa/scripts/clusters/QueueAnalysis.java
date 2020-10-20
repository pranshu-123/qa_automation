package com.qa.scripts.clusters;

import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class QueueAnalysis {
    private static final Logger LOGGER = Logger.getLogger(QueueAnalysis.class.getName());
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final QueueAnalysisPageObject queueAnalysisPageObject;
    private final TopPanelPageObject topPanelPageObject;
    private final QueueAnalysisPageObject qaPageObject;
    private CommonPageObject commonPageObject;

    public QueueAnalysis(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        queueAnalysisPageObject = new QueueAnalysisPageObject(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        qaPageObject = new QueueAnalysisPageObject(driver);
    }

    public void closeConfirmationMessageNotification() {
        if (queueAnalysisPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, queueAnalysisPageObject.confirmationMessageElementClose.get(0));
        }
    }

    //click on cluster drop down
    public void selectMultiClusterId(String clusterId) {
        waitExecuter.sleep(2000);
        commonPageObject = new CommonPageObject(driver);
        MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
        waitExecuter.sleep(2000);
        System.out.println("Size of cluster in dropdown: " + commonPageObject.clustersList.size());
        waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
        commonPageObject.clusterSearchBox.sendKeys(clusterId);
        commonPageObject.clusterSearchFirstField.click();
        waitExecuter.sleep(2000);
    }

    //Navigate to queue analysis tab
    public void navigateToQueueAnalysis() {
        LOGGER.info("Navigate to Queue Analysis tab from header");
        waitExecuter.waitUntilElementClickable(topPanelPageObject.queueAnalysisTab);
        waitExecuter.sleep(1000);
        //Click on Queue Analysis tab
        LOGGER.info("Clicked on Queue Analysis tab");
        topPanelPageObject.queueAnalysisTab.click();
        waitExecuter.sleep(3000);
        //Validate Queue Analysis tab loaded successfully
        LOGGER.info("Validate Queue Analysis tab loaded successfully");
        waitExecuter.waitUntilElementPresent(qaPageObject.queueAnalysisHeading);
        waitExecuter.waitUntilPageFullyLoaded();
    }

}
