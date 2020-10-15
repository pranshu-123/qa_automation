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
public class TC_CB_34 extends BaseClass {

    /**
     * Validate the user is able to deselect the filters
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CB_34_verifyICB_UserAbleToDeselectFilters( String clusterId) {
        test = extent.startTest("TC_CB_34_verifyICB_UserAbleToDeselectFilters: " + clusterId, "Validate the user is able to deselect the filters.");
        test.assignCategory("4620 Cluster - Impala Chargeback");

        WaitExecuter waitExecuter = new WaitExecuter(driver);

        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        chargeBackImpala.selectImpalaChargeback();

        //Cluster selected
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        // Click on datepicker button
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(2000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);

        if(chargeBackImpala.isTotalNumberOfJobCountHeader()){
            test.log(LogStatus.PASS, "Jobs count displayed.");
            System.out.println("Jobs count displayed");
            chargeBackImpala.clickOnGroupBySearchBox();
            //chargeBackImpala.verifyNumberOfOptionsInGroupBy();
            int totalOptionInGroupBy =  chargeBackImpala.getNumberOfOptionsInGroupBy();
            System.out.println("Toal number of GroupBy Options: "+totalOptionInGroupBy);
            for(int i =0 ; i< totalOptionInGroupBy-2; i++){
                chargeBackImpala.click1Row1ColumnFromGroupByTable();
                waitExecuter.waitUntilPageFullyLoaded();
            }
            chargeBackImpala.deselectGroupByFilters();
            test.log(LogStatus.PASS, "Validate the user is able to deselect the filters.");

        }else{
            System.out.println("Jobs count displayed is Zero");
            test.log(LogStatus.FAIL, "No Data Available.");

        }

    }
}
