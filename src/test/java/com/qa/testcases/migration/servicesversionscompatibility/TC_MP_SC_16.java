package com.qa.testcases.migration.servicesversionscompatibility;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.scripts.migration.ServicesAndVersionsCompatibility;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_16 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_16.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateMSVAreMissingInSrcButAvailableInTargetForAmazonEMR(String clusterId) {

        test = extent.startTest("TC_MP_SC_16.validateMSVAreMissingInSrcButAvailableInTargetForAmazonEMR: "
                + clusterId, " Validate \"Service missing in Source, but available in Target\" legend " +
                "for cloud provider - \"Amazon EMR\" ");
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
        servicesAndVersionsCompatibility.closeMessageBanner();
        LOGGER.info("Clicked on close banner");
        servicesAndVersionsCompatibility.clickOnRunButton();
        LOGGER.info("Clicked on Run button");
        String cloudProductName = "Amazon EMR";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        LOGGER.info("Selected platform : "+cloudProductName+" from the drop down.");
        servicesAndVersionsCompatibility.clickOnRunModalButton();
        List<String> expectedPlatforms = Arrays.asList(PageConstants.MigrationAndServices.Amazon_EMR);

        try {
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.confirmationMessageElement,
                    "Services and Versions Compatibility completed successfully.");
            LOGGER.info("Services and Versions Compatibility completed successfully message displayed.");

            List<WebElement> reportList = servicesAndVersionsCompatibilityPageObject.latestReportList;
            Assert.assertFalse(reportList.isEmpty(), "No latest report generated.");
            //Validating the report generated is latest report.
            waitExecuter.waitUntilTextToBeInWebElement(reportList.get(1),"Amazon EMR");
            LOGGER.info("Verified report for Amazon EMR.");
            servicesAndVersionsCompatibility.validateLatestReport();
            Assert.assertTrue(expectedPlatforms.equals(servicesAndVersionsCompatibility.getPlatforms()));
            servicesAndVersionsCompatibility.verifyServicesAndVersionsAreMissingInSourceButAvailableInTarget();
            test.log(LogStatus.PASS, "Verified Services and Versions are missing in Source, " +
                    "but available in Target legend for cloud provider Amazon EMR.");
        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully" +
                    " for Amazon EMR.");
        }

    }

}
