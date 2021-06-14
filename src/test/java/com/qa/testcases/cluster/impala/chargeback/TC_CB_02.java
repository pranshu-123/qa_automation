package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.testcases.cluster.impala.resources.IM_RES_02;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;

import java.util.logging.Logger;

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
        chargebackImpala = new ChargeBackImpala(driver);
        picker = new DatePickerPageObject(driver);
        chargebackImpalaPageObject = new ChargebackImpalaPageObject(driver);
        topPanelPageObject = new TopPanelPageObject(driver);

        // Click on Chargeback tab
        test.log(LogStatus.INFO, "Click on Chargeback tab");
        LOGGER.info("Click on Chargeback tab");
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.clusterChargeBackTab);
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.clusterChargeBackTab);
        waitExecuter.sleep(1000);

        // Click on chargeback dropdown and select Impala
        test.log(LogStatus.INFO, "Click on chargeback dropdown and select Impala");
        LOGGER.info("Click on chargeback dropdown and select Impala");
        JavaScriptExecuter.clickOnElement(driver, chargebackImpalaPageObject.chargeBackDropdownOptionsButton);
        waitExecuter.sleep(1000);
        chargebackImpalaPageObject.chargeBackDropdownImpalaOption.click();
        waitExecuter.sleep(1000);

        //Select last 30 days from date picker
        test.log(LogStatus.INFO, "Select last 30 days from date picker");
        LOGGER.info("Select last 30 days from date picker");
        picker.calendarDate.click();
        waitExecuter.sleep(1000);
        picker.last30Days.click();
        waitExecuter.sleep(1000);

        // Click on cluster dropdown
        test.log(LogStatus.INFO, "Click on cluster dropdown");
        LOGGER.info("Click on cluster dropdown");
        waitExecuter.waitUntilElementClickable(chargebackImpalaPageObject.containerDropdownArrow);
        //chargebackImpalaPageObject.containerDropdownArrow.click();
        MouseActions.clickOnElement(driver, chargebackImpalaPageObject.containerDropdownArrow);
        waitExecuter.sleep(1000);

        // Assert the cluster size is not null
        Assert.assertNotNull(chargebackImpala.getListOfClusters(chargebackImpalaPageObject.listOfClusters),
            "There are no cluster available");
        test.log(LogStatus.PASS, "The cluster list is not empty.");

    }

}
