package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_02 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndCompatibilityReportForAzureHDI(String clusterId) {

        test = extent.startTest("TC_MP_SC_02.validateServicesAndCompatibilityReportForAzureHDI: " + clusterId,
                "Access to all supported cloud providers ");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        LOGGER.info("Clicked on Migration and accessing Services And Versions Compatibility page ");
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        LOGGER.info("Clicked on Services And Versions Compatibility tab");
        servicesAndVersionsCompatibility.closeMessageBanner();
        LOGGER.info("Clicked on close banner");
        servicesAndVersionsCompatibility.clickOnRunButton();
        LOGGER.info("Clicked on Run button");
        servicesAndVersionsCompatibility.getCloudProducts();
        LOGGER.info("Clicked on cloud product drop down to get all cloud providers");
        test.log(LogStatus.PASS, "Verified all supported cloud providers");
    }
}
