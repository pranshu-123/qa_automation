package com.qa.testcases.cluster.impala.chargeback;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.chargeback.GroupByOptions;
import com.qa.pagefactory.clusters.ChargebackImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.ChargeBackImpala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Marker.All
@Marker.ImpalaChargeback
public class TC_CB_29 extends BaseClass {
    private WaitExecuter waitExecuter;
    private ChargebackImpalaPageObject chargebackImpalaPageObject;
    private DatePicker picker;

    @Test(dataProvider = "clusterid-data-provider", description = "P0-Verify the number of applications displayed in the chargeback report")
    public void TC_CB_29_dataValidation(String clusterId) {

        test = extent.startTest("TC_CB_29_dataValidation " + clusterId, "Verify the number of applications displayed in the chargeback report");
        test.assignCategory(" Cluster - Impala Chargeback");
        ChargeBackImpala chargeBackImpala = new ChargeBackImpala(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);
        picker = new DatePicker(driver);
        chargeBackImpala.selectImpalaChargeback();

        // Select the cluster
        test.log(LogStatus.INFO, "Select clusterId : " + clusterId);
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);

        chargeBackImpala.selectImpalaType("Impala");
        waitExecuter.sleep(2000);

        waitExecuter.sleep(1000);
        picker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        picker.selectLast30Days();
        waitExecuter.sleep(3000);
        chargeBackImpala.clickOnGroupBySearchBox();
        chargeBackImpala.selectGroupBy(GroupByOptions.REAL_USER);
        waitExecuter.sleep(1000);


        test.log(LogStatus.PASS, "Jobs count displayed.");
        System.out.println("Jobs count displayed");
        try {
            String donutchart = chargeBackImpala.getdonutchartHeader();
            String donutchartvalue = donutchart.replaceAll("[^\\d-]", "");
            Assert.assertTrue(chargeBackImpala.getdonutchartHeader().matches(donutchart),
                    "verify the total Jobs donut chart in graph heading");
            test.log(LogStatus.PASS, "verify the total Jobs donut chart in graph heading" + donutchart);

            String impalaqueriestable = chargeBackImpala.getImpalatableHeader();
            String impalaqueriestablevalue = impalaqueriestable.replaceAll("[^\\d-]", "");
            Assert.assertEquals(donutchartvalue, impalaqueriestablevalue, "Jobs Count does not matches.");
            Assert.assertTrue(chargeBackImpala.getImpalatableHeader().matches(impalaqueriestable),
                    "verify the total Jobs donut chart in graph heading");
            test.log(LogStatus.PASS, "verify the total Impala queries table in graph heading" + impalaqueriestable);

            String chargebacktable = chargeBackImpala.getchargebacktableHeader();
            test.log(LogStatus.PASS, "verify the total Chargeback table grouped by user chart in graph heading" + chargebacktable);
        } catch (NullPointerException | NoSuchElementException te) {
            throw new AssertionError("Total Chargeback table grouped by user chart in graph heading is not verified");
        }
    }
}