package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Birender Kumar
 */
@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_30 extends BaseClass {

    /**
     * Validate the number of applications displayed in the chargeback report for Different Date pickers.
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_30_verifyICB_JobsCountForDifferentDatePickers( String clusterId) {
        test = extent.startTest("TC_CB_30_verifyICB_JobsCountForDifferentDatePickers: " + clusterId, "Validate the number of applications displayed in the chargeback report for Different Date pickers.");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        //Cluster selected
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);

        //Validate job count by comparing the number of
        // 1. Impala jobs displayed in the applications,
        // 2. Donut chart,
        // 3. Chargeback Table and
        // 4. Impala queries table
        if(chargeBackImpala.isTotalNumberOfJobCountHeader()){
            test.log(LogStatus.PASS, "Jobs count displayed.");
            System.out.println("Jobs count displayed");

            // 1. Impala jobs displayed in the applications,
            String strJobsCountFromHeader = chargeBackImpala.getTotalJobCountFromJobsGraphHeader();
            strJobsCountFromHeader= strJobsCountFromHeader.substring(0, strJobsCountFromHeader.length()-1);
            int jobsCountFromHeader = Integer.parseInt(strJobsCountFromHeader);

            //2. TBD Job Count from Donut chart

            //3. Total Jobs Count from Chargeback table
            int jobsCountFromChargebackTable = chargeBackImpala.getTotalJobsCountFromTable();

            //4. Total Jobs Count from Impala queries table headers.
            chargeBackImpala.getImpalatableHeader().contains(strJobsCountFromHeader);
            Assert.assertTrue(jobsCountFromChargebackTable >= jobsCountFromHeader, "Jobs Count does not matches.");
            test.log(LogStatus.PASS, "Validate the jobs count from chargeback Header, chargeback table and from impala table.");

        }else{
            System.out.println("Jobs count displayed is Zero");
            test.log(LogStatus.FAIL, "No Data Available.");
        }


        datePicker.clickOnDatePicker();
        waitExecuter.sleep(2000);
        datePicker.selectLast12Hour();
        waitExecuter.sleep(1000);

        if(chargeBackImpala.isTotalNumberOfJobCountHeader()){
            test.log(LogStatus.PASS, "Jobs count displayed.");
            System.out.println("Jobs count displayed");

            // 1. Impala jobs displayed in the applications,
            String strJobsCountFromHeader = chargeBackImpala.getTotalJobCountFromJobsGraphHeader();
            strJobsCountFromHeader= strJobsCountFromHeader.substring(0, strJobsCountFromHeader.length()-1);
            int jobsCountFromHeader = Integer.parseInt(strJobsCountFromHeader);

            //2. TBD Job Count from Donut chart

            //3. Total Jobs Count from Chargeback table
            int jobsCountFromChargebackTable = chargeBackImpala.getTotalJobsCountFromTable();

            //4. Total Jobs Count from Impala queries table headers.
            chargeBackImpala.getImpalatableHeader().contains(strJobsCountFromHeader);
            Assert.assertTrue(jobsCountFromChargebackTable >= jobsCountFromHeader, "Jobs Count does not matches.");
            test.log(LogStatus.PASS, "Validate the jobs count from chargeback Header, chargeback table and from impala table.");

        }else{
            System.out.println("Jobs count displayed is Zero");
            Assert.assertEquals("","Jobs count displayed is Zero");
            test.log(LogStatus.FAIL, "No Data Available for Last 12 Hours date picker.");
        }

    }
}