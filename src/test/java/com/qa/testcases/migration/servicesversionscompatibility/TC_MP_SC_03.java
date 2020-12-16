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
public class TC_MP_SC_03 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndCompatibilityReportForGoogleDataproc(String clusterId) {

        test = extent.startTest("validateServicesAndCompatibilityReportForGoogleDataproc: " + clusterId,
                "Verify the user is able to run a report for Google Dataproc providers.");
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
            test.log(LogStatus.PASS, "Verified Services and Versions Compatibility report is loaded properly" +
                    " for Google Dataproc.");
            servicesAndVersionsCompatibility.validateLatestReport();
        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully" +
                    " for Google Dataproc.");
        }

    }

}