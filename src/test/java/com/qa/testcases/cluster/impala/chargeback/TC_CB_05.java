package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
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
public class TC_CB_05 extends BaseClass {

    /**
     * Verify User is able to access Impala chargeback page and able to pick all the date range.
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify that the UI should display data for the all the date ranges")
    public void TC_CB_05_VerifyImpalaChargeBackDatePicker(String clusterId) {
        test = extent.startTest("TC_CB_05_VerifyImpalaChargeBackDatePicker: "+clusterId, "Verify the user can pick all the date ranges from Impala chrageback page");
        test.assignCategory(" Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();


        //Cluster selected
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.waitUntilPageFullyLoaded();

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);

        //click on cluster drop down
        //CommonPageObject commonPageObject = new CommonPageObject(driver);
        //commonPageObject.clusterDropdown.click();
        //waitExecuter.sleep(2000);
        //System.out.println("Size of cluster in dropdown: "+commonPageObject.clustersList.size());
        //for (int i=0; i<commonPageObject.clustersList.size(); i++) {
            //commonPageObject.clustersList.get(i).click();
            //waitExecuter.sleep(2000);

            // Click on datepicker button
            DatePicker datePicker = new DatePicker(driver);
            waitExecuter.sleep(3000);

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            //select 'Last 1 Hour'
            datePicker.selectLastOneHour();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 1 Hour Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 1 Hour Data range.");
            }

            //select 'Last 2 Hour'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLastTwoHour();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 2 Hour Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 2 Hour Data range.");
            }

            //select 'Last 6 Hour'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLastSixHour();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 6 Hour Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 6 Hour Data range.");
            }

            //select 'Last 12 Hour'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLast12Hour();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 12 Hour Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 12 Hour Data range.");
            }

            //select 'Today'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectToday();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Today Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Today Data range.");
            }

            //select 'Yesterday'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectYesterday();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Yesterday Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Yesterday Data range.");
            }

            //select 'Last 7 Days'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLast7Days();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 7 Days Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 7 Days Data range.");
            }

            //select 'Last 30 Days'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLast30Days();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 30 Days Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 30 Days Data range.");
            }

            //select 'Last 90 Days'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLast90Days();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last 90 Days Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last 90 Days Data range.");
            }

            //select 'This Month'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectThisMonth();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for This Month Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for This Month Data range.");
            }

            //select 'Last Month'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLastMonth();
            waitExecuter.sleep(2000);

            if(chargeBackImpala.isJobsFromGraphHeaderDisplayed()){
                test.log(LogStatus.PASS, "Jobs count displayed for Last Month Data range.");
            }else{
                chargeBackImpala.getNoDataAvailableText().contains("No Data Available.");
                test.log(LogStatus.PASS, "No Data Available for Last Month Data range.");
            }
        }

}

