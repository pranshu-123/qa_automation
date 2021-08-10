package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.testcases.cluster.impala.resources.IM_RES_02;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;

import java.util.List;
import java.util.logging.Logger;

import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;


/**
 * @author - Ojasvi Pandey
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_02 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargeBackImpala chargebackImpala;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePickerPageObject picker;
    private TopPanelPageObject topPanelPageObject;
    private static final Logger LOGGER = Logger.getLogger(TC_CB_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the UI should list all the clusters connected to Unravel")
    public void TC_CB_02_VerifyClusterList(String clusterId) {
        test = extent.startTest("TC_CB_02_VerifyClusterList: " + clusterId,
            "Verify User is able to see list of clusters available");
        test.assignCategory(" Cluster - Impala Chargeback");
        test.log(LogStatus.INFO, "Login to the application");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        waitExecuter = new WaitExecuter(driver);
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        picker = new DatePickerPageObject(driver);
        UserActions userActions = new UserActions(driver);
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        chargeBackImpala.selectImpalaChargeback();

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(3000);

        // Get cluster list
        test.log(LogStatus.INFO, "Get cluster list");
        LOGGER.info("Get cluster list");
        waitExecuter.sleep(1000);
        applicationsPageObject.clusterSearchBox.click();
        List<WebElement> listOfClusters = applicationsPageObject.getclusterListDropdown;
        waitExecuter.sleep(1000);
        //Verify that cluster list is not empty
        Assert.assertTrue(listOfClusters.size() > 0, "Cluster list is empty");
        test.log(LogStatus.PASS, "Verified that the cluster list is not empty. The size is: "
                + listOfClusters.size());

    }

}
