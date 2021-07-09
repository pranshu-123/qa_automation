package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.CommonPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

@Marker.YarnChargeback
@Marker.All
public class YC_021 extends BaseClass {

    /**
     * Verify User is able to access Yarn chargeback page and Verify the calender time
     */
    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the timestamp on the calendar on the yarn chargeback page.")
    public void YC_021_Verifythecalender(String clusterId) {
        test = extent.startTest("YC_021_Verifythecalender"+clusterId, "Verify the calender time");
        test.assignCategory(" Cluster - Yarn Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "Passed Parameter Is : " + clusterId);

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        chargeBackYarn.selectYarnChargeback();
        waitExecuter.sleep(2000);

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : "+clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(1000);
        chargeBackYarn.selectChargebackType("Yarn");
        waitExecuter.sleep(2000);

        try {
        //click on cluster drop down
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        commonPageObject.clusterDropdown.click();
        waitExecuter.sleep(3000);
        System.out.println("Size of cluster in dropdown: " + commonPageObject.clustersList.size());

        for (int i = 0; i < commonPageObject.clustersList.size(); i++) {
            commonPageObject.clustersList.get(i).click();
            waitExecuter.sleep(2000);

            // Click on datepicker button
            DatePicker datePicker = new DatePicker(driver);
            datePicker.clickOnDatePicker();


            // Click on combination of daterange
            datePicker.selectLastOneHour();
            waitExecuter.sleep(1000);
            test.log(LogStatus.PASS, "Last 1 Hour field is successfully verified in date range");


            //select 'Last 2 Hour'
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);

            datePicker.selectLastTwoHour();
            waitExecuter.sleep(2000);

            test.log(LogStatus.PASS, "Last 2 Hours field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLastSixHour();
            waitExecuter.sleep(2000);
            waitExecuter.waitUntilPageFullyLoaded();


            test.log(LogStatus.PASS, "lastSixHour field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLast12Hour();
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "last12Hours field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectToday();
            waitExecuter.sleep(2000);
            test.log(LogStatus.PASS, "today field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectYesterday();
            waitExecuter.sleep(2000);


            test.log(LogStatus.PASS, "yesterday field is successfully verified in date range");
            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLast7Days();
            waitExecuter.sleep(2000);


            test.log(LogStatus.PASS, "Last 7 Days field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLast30Days();
            waitExecuter.sleep(1000);


            test.log(LogStatus.PASS, "Last 30 Days field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLast90Days();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilPageFullyLoaded();

            test.log(LogStatus.PASS, "Last 90 Days field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectThisMonth();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "This Month field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectLastMonth();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilPageFullyLoaded();
            test.log(LogStatus.PASS, "Last Month field is successfully verified in date range");

            datePicker.clickOnDatePicker();
            waitExecuter.sleep(1000);
            datePicker.selectCustomRange();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilPageFullyLoaded();

            // Set Start date by substracting days from current date and end date as current
            // date
            datePicker.setCurrentAndPastDate(-3);
            waitExecuter.sleep(1000);

            // Click on apply button of calendar
            datePicker.clickOnCustomDateApplyBtn();
            waitExecuter.sleep(1000);
            waitExecuter.waitUntilPageFullyLoaded();

            test.log(LogStatus.PASS, "Start Date field is successfully verified in custom date range");
        }
        } catch (TimeoutException | NoSuchElementException te) {
            throw new AssertionError("Successfully not verified date range."+te);
        }

    }
}