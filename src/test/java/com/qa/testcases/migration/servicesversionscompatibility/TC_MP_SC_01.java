package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.base.BaseClass;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_MP_SC_01 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void ValidateVCoresGraphDownloadAsXLS(String clusterId) {

        test = extent.startTest("TC_CO_31.ValidateVCoresGraphDownloadAsXLS: " + clusterId,
                "Access to Services & Compatibility report.");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        servicesAndVersionsCompatibility.closeMessageBanner();
        servicesAndVersionsCompatibility.clickOnRunNewButton();
        String cloudProductName = "Google Dataproc";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        servicesAndVersionsCompatibility.clickOnRunButton();

    }

}
