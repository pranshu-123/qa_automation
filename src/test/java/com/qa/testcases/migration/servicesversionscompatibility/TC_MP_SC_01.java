package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_01 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndCompatibilityReport(String clusterId) {

        test = extent.startTest("TC_MP_SC_01.validateServicesAndCompatibilityReport: " + clusterId,
                "Access to Services & Compatibility report.");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject =
                new ServicesAndVersionsCompatibilityPageObject(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        servicesAndVersionsCompatibility.closeMessageBanner();
        servicesAndVersionsCompatibility.clickOnRunButton();
        String cloudProductName = "Google Dataproc";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        servicesAndVersionsCompatibility.clickOnRunModalButton();

        try {
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.confirmationMessageElement,
                    "Services and Versions Compatibility completed successfully.");
            test.log(LogStatus.PASS, "Verified Services and Versions Compatibility report is loaded properly.");
            servicesAndVersionsCompatibility.validateLatestReport();
        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully.");
        }

    }

}