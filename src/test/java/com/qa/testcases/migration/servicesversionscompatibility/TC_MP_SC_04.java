package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_04 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_04.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndCompatibilityReportForAmazonEMR(String clusterId) {

        test = extent.startTest("TC_MP_SC_04.validateServicesAndCompatibilityReportForAmazonEMR: " + clusterId,
                "Verify User can run a report for 'Amazon EMR' ");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject =
                new ServicesAndVersionsCompatibilityPageObject(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        //servicesAndVersionsCompatibility.closeMessageBanner();
        servicesAndVersionsCompatibility.clickOnRunButton();
        String cloudProductName = "Amazon EMR";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        servicesAndVersionsCompatibility.clickOnRunModalButton();
        List<String> expectedPlatforms = Arrays.asList(PageConstants.MigrationAndServices.Amazon_EMR);

        try {
            waitExecuter.sleep(50000);
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.runBtn, "Run");
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.confirmationMessageElement,
                    "Services and Versions Compatibility completed successfully.");
            servicesAndVersionsCompatibility.validateLatestReport();
            List<String> actualPlatforms = servicesAndVersionsCompatibility.getPlatforms();
            Assert.assertTrue(expectedPlatforms.equals(actualPlatforms), "Mismatch actual platforms: "+actualPlatforms);
            test.log(LogStatus.PASS, "Verified Services and Versions Compatibility report is loaded properly " +
                    "for Amazon EMR.");

        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully" +
                    " for Amazon EMR.");
        }

    }

}
