package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.clusters.Tuning;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
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
        List<String> allClusters = null;
        if(commonPageObject.clustersList.size() > 0){
            //Get all clusters from UI
            allClusters = tuning.getClusterOptions(commonPageObject);
            for(String expectedCluster: expectedClusterOptions){
                Assert.assertTrue(allClusters.contains(expectedCluster),
                        "Cluster list does not contain: " + expectedCluster);
                test.log(LogStatus.PASS, "Cluster list contains option: " + expectedCluster);
            }
        }else{
            Assert.assertTrue(false, "Clusters not available.");
        }

    }

}
