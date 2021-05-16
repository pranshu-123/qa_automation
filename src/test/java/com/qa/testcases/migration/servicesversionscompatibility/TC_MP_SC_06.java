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
public class TC_MP_SC_06 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_06.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndVersionsAreCompatibleForGoogle(String clusterId) {

        test = extent.startTest("TC_MP_SC_06.validateServicesAndVersionsAreCompatibleForGoogle: " + clusterId,
                "Validate \"Services and Versions are Compatible\" legend for cloud provider - \"Google data proc\" ");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject =
                new ServicesAndVersionsCompatibilityPageObject(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        LOGGER.info("Clicked on Migration and accessing Services And Versions Compatibility page ");
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        LOGGER.info("Clicked on Services And Versions Compatibility tab");
        servicesAndVersionsCompatibility.clickOnRunButton();
        LOGGER.info("Clicked on Run button");
        String cloudProductName = "Google Dataproc";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        LOGGER.info("Selected platform : "+cloudProductName+" from the drop down.");
        servicesAndVersionsCompatibility.clickOnRunModalButton();
        List<String> expectedPlatforms = Arrays.asList(PageConstants.MigrationAndServices.Google_Dataproc);

        try {
            waitExecuter.sleep(50000);
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.runBtn, "Run");
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.confirmationMessageElement,
                    "Services and Versions Compatibility completed successfully.");
            servicesAndVersionsCompatibility.validateLatestReport();
            Assert.assertTrue(expectedPlatforms.equals(servicesAndVersionsCompatibility.getPlatforms()));
            servicesAndVersionsCompatibility.verifyServicesAndVersionsAreCompatible();
            test.log(LogStatus.PASS, "Verified Services and Versions are Compatible" +
                    " for Google Dataproc.");

        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully" +
                    " for Google Dataproc.");
        }

    }

}