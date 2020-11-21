package com.qa.testcases.cluster.tuning;

import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.clusters.Tuning;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TC_CTR_04 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_04.class.getName());


    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningDatePickerList(String clusterId) {
        test = extent.startTest("TC_CTR_04.validateTuningDatePickerList: "+ clusterId,
                "Verify cluster filter in UI");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);
        LOGGER.info("Clicked on Tuning Tab");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab");

        Tuning tuning = new Tuning(driver);
        tuning.closeConfirmationMessageNotification();
        tuning.clickOnRunButton();
        LOGGER.info("Clicked on Run button");
        test.log(LogStatus.INFO,"Clicked on Run button");

        UserActions userActions = new UserActions(driver);
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
        String[] expectedClusterOptions = {"tnode28-HDP315-TLS-Kerb-Ranger", "tnode3-CDH633-TLS-Kerb-Sentry", "tnode40-CDH5162"};
        if(commonPageObject.clustersList.size() > 0){
            //Get all clusters from UI
            List<String> allClusters = getClusterOptions(commonPageObject);
            for(String expectedCluster: expectedClusterOptions){
                Assert.assertTrue(allClusters.contains(expectedCluster),
                        "Cluster list does not contain: " + expectedCluster);
                test.log(LogStatus.PASS, "Cluster list contains option: " + expectedCluster);
            }
        }else{
            Assert.assertTrue(false, "Clusters not available.");
        }

    }


    /**
     * This method used to get Cluster options
     */
    public List<String> getClusterOptions(CommonPageObject commonPageObject) {
        List<String> list = new ArrayList<>();
        for (WebElement element : commonPageObject.clustersList) {
            list.add(element.getText());
        }
        return list;
    }

}
