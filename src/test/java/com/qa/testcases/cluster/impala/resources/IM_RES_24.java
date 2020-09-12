package com.qa.testcases.cluster.impala.resources;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IM_RES_24  extends BaseClass {

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip(String clusterId) {
        test = extent.startTest("IM_RES_22.verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory("4620 - Cluster/Impala Resources");
        WaitExecuter executer = new WaitExecuter(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        // Click on Impala tab
        executer.waitUntilElementClickable(topPanelPageObject.impalaTab);
        JavaScriptExecuter.clickOnElement(driver, topPanelPageObject.impalaTab);
        executer.waitUntilElementPresent(impalaPageObject.getImpalaPageHeader);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        executer.waitUntilPageFullyLoaded();

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast30Days();
        executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Select Date from DatePicker.");

        Impala impala = new Impala(driver);
        impala.selectUserInGroupBy();
        test.log(LogStatus.INFO, "Select Queue in Group by option.");

        //TBD
        //1. Click on Memory Consumption at that point where Impala Queries Table should get populated.


        //Impala Queries table is populated and validate its Header columns
        impala.getImpalaJobsTableRecord();
        //Impala Table Header Text: 24 Impala queries running between  26-08-2020 05:30:00 and 27-08-2020 05:30:00
        System.out.println("Impala Table Header Text: "+impala.getImpalaQueriesTableHeaderText());
        test.log(LogStatus.INFO, "Impala Queuries table is populated.");
        Assert.assertTrue(impala.validateHeaderColumnNameInImpalaQueriesTable(), "Mismatch in Impala Table header column.");

    }

}
