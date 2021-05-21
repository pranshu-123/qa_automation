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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.All
@Marker.MigrationServices
public class TC_MP_SC_03 extends BaseClass {

    private static final Logger LOGGER = Logger.getLogger(TC_MP_SC_03.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateServicesAndCompatibilityReportForGoogleDataproc(String clusterId) {

        test = extent.startTest("TC_MP_SC_03.validateServicesAndCompatibilityReportForGoogleDataproc: " + clusterId,
                "Verify the user is able to run a report for Google Dataproc providers.");
        test.assignCategory(" Migration - Services And Versions Compatibility ");

        //Initialize object
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ServicesAndVersionsCompatibility servicesAndVersionsCompatibility = new ServicesAndVersionsCompatibility(driver);
        ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject =
                new ServicesAndVersionsCompatibilityPageObject(driver);

        servicesAndVersionsCompatibility.setupServicesAndVersionsCompatibilityPage();
        servicesAndVersionsCompatibility.clickOnServicesAndVersionMigrationTab();
        servicesAndVersionsCompatibility.clickOnRunButton();
        String cloudProductName = "Google Dataproc";
        servicesAndVersionsCompatibility.selectCloudProduct(cloudProductName);
        servicesAndVersionsCompatibility.clickOnRunModalButton();
        List<String> expectedPlatforms = Arrays.asList(PageConstants.MigrationAndServices.Google_Dataproc);
        waitExecuter.waitUntilTextNotToBeInWebElement(servicesAndVersionsCompatibilityPageObject.modalAfterRunButton, "Please Wait");
        waitExecuter.waitUntilTextNotToBeInWebElement(servicesAndVersionsCompatibilityPageObject.banner, "Services and Versions Compatibility completed successfully.");
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.runModalBtn);
        waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.hdpHeaderList.get(1),"Dataproc");
        try {
            waitExecuter.sleep(5000);
            waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.runBtn, "Run");
            servicesAndVersionsCompatibility.validateLatestReport();
            List<String> actualVersionList = servicesAndVersionsCompatibility.getPlatforms();
            LOGGER.info("Expected List- " + expectedPlatforms);
            LOGGER.info("Actual List- " + actualVersionList);
            Assert.assertEquals(actualVersionList,expectedPlatforms, "There is a mismatch in versions expected for Google DataProc");
            test.log(LogStatus.PASS, "Verified Services and Versions Compatibility report is loaded properly" +
                    " for Google Dataproc.");

        } catch (TimeoutException te) {
            throw new AssertionError("Services and Versions Compatibility Report not completed successfully" +
                    " for Google Dataproc.");
        }

    }

}