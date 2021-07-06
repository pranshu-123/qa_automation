package com.qa.testcases.cluster.yarn.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.scripts.clusters.yarn.ChargeBackYarn;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.YarnChargeback
@Marker.All
public class YC_033 extends BaseClass {

    /**
     * Verify the columns of the Yarn Job table are editable by clicking the settings icon.
     */

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify that the columns of the yarn job table should be editable by clicking the settings icon")
    public void YC_033_verifyYarnJobsTableColumnsEditable(String clusterId) {
        test = extent.startTest("YC_033_verifyYarnJobsTableColumnsEditable: " + clusterId, "Verify yarn jobs table columns on Yarn chrageback page.");
        test.assignCategory(" Cluster - Yarn Chargeback");

        ChargeBackYarn chargeBackYarn = new ChargeBackYarn(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        chargeBackYarn.selectYarnChargeback();
        waitExecuter.sleep(2000);

        chargeBackYarn.selectMultiClusterId(clusterId);
        chargeBackYarn.selectChargebackType("Yarn");
        waitExecuter.sleep(2000);


        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        test.log(LogStatus.PASS, "Selected 30 Days date picker for Yarn chargeback page");


        // Select Yarn Charge back
        chargeBackYarn.clickOnGroupBySearchBox();
        String optionName = "User";
        chargeBackYarn.selectOptionsInGroupBy(optionName);
        test.log(LogStatus.PASS, "Selected " + optionName + " as option in Group By filter, yarn chargeback page");

        // Check Yarn Jobs Table Records populated
        chargeBackYarn.getYarnJobsTableRecord();
        test.log(LogStatus.PASS, "Verified Yarn Jobs Table is available on Yarn chargeback page");

        //Verify Header columns size before uncheck
        List<WebElement> beforeYarnJobsTblHeaderColumn = chargeBackYarn.getYarnJobsTableHeaderNames();
        System.out.println("Before uncheck Type from IconGear, Header Size: "+beforeYarnJobsTblHeaderColumn.size());
        int beforeTypeChkBox = beforeYarnJobsTblHeaderColumn.size();

        // click on Icon Gear Settings and un-check Type CheckBox
        chargeBackYarn.clickOnIconGearUnChekckTypeChkBox();
        test.log(LogStatus.PASS, "Verified Icon Gear Settings clicked and unchecked Type checkbox successfully");

        //Verify Header columns size after uncheck
        List<WebElement> afterYarnJobsTblHeaderColumn = chargeBackYarn.getYarnJobsTableHeaderNames();
        System.out.println("After uncheck Type from IconGear, Header Size: "+afterYarnJobsTblHeaderColumn.size());
        int afterTypeChkBox =afterYarnJobsTblHeaderColumn.size();

        Assert.assertTrue((beforeTypeChkBox>afterTypeChkBox),"Column of Yarn Jobs Table is not Editable");
        test.log(LogStatus.PASS, "Verified columns of the Yarn Job table are editable by clicking the settings icon.");
    }

}
