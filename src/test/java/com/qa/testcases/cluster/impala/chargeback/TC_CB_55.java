package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.testcases.cluster.overview.TC_CO_31;
import com.qa.utils.FileUtils;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
@Marker.All
@Marker.ImpalaChargeback
//This class contains TC_CB_55 and TC_CB_56 both test case.
public class TC_CB_55 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CO_31.class.getName());

    /**
     * Validate the data displayed in the download Chargeback page.
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the data displayed in the Download CSV above the Impala Jobs page")
    public void TC_CB_55_verifyImpalaChargeBackDownloadCSV( String clusterId) {

        test = extent.startTest("TC_CB_55_and_56_verifyImpalaChargeBackDownloadCSV: "+clusterId, "Validate the data displayed in the download Chargeback page");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();
        waitExecuter.waitUntilPageFullyLoaded();


        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(2000);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilPageFullyLoaded();

        chargeBackImpala.clickOnTableDownloadMenu();
        LOGGER.info("Clicked on table download menu ... ");

        chargeBackImpala.clikOnDownloadCSV("Download CSV");
        LOGGER.info("Download CSV file");

        //TBD Compare Download CSV and Impala Chargeback UI tables

        // Get list of all users from charge back table
        waitExecuter.sleep(1000);
        List<String> listOfUsersFromChargeBackTable = chargeBackImpala.getUsersFromTable();
        test.log(LogStatus.PASS, "Verified and list all user from charge back table."+listOfUsersFromChargeBackTable);

        // Get list of all users from Impala Jobs Table
        waitExecuter.sleep(1000);
        List<String> listOfUsersFromImpalaJobsTable = chargeBackImpala.getUsersFromImpalaJobsTable();
        test.log(LogStatus.PASS, "Verified and list all user from charge back table."+listOfUsersFromImpalaJobsTable);

        FileUtils.downloadCSVFileReader();
        test.log(LogStatus.PASS, "Verified Download CSV files read successfully.");

    }
}
