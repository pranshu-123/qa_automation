package com.qa.testcases.cluster.impala.resources;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Birender Kumar
 */
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
        //Select cluster id
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        executer.waitUntilPageFullyLoaded();
        //Select date
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        executer.sleep(1000);
        datePicker.selectLast30Days();
        executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Select Date from DatePicker.");

        Impala impala = new Impala(driver);
        executer.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
        executer.sleep(3000);
        impalaPageObject.groupByDropdownButton.click();
        impalaPageObject.groupByUserList.click();
        executer.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Select User in Group by option.");

        //1. Click on Queries graph at that point where Impala Queries Table should get populated.
        test.log(LogStatus.INFO, "Navigate different section in queries graph");
        GraphUtils graphUtils = new GraphUtils();
        graphUtils.navigateDifferentPointOnGraphGetTextClickCheckImpalaTbl(driver, impalaPageObject.queryHighChartContainer);
        test.log(LogStatus.PASS,"Successfully read the impalaQueries header text, after click on queries graph.");

        //TBD
        //Impala Queries table is populated and validate its Header columns
//        impala.getImpalaJobsTableRecord();
//        //Impala Table Header Text: 24 Impala queries running between  26-08-2020 05:30:00 and 27-08-2020 05:30:00
//        System.out.println("Impala Table Header Text: "+impala.getImpalaQueriesTableHeaderText());
//        test.log(LogStatus.INFO, "Impala Queries table is populated.");
//        Assert.assertTrue(impala.validateHeaderColumnNameInImpalaQueriesTable(), "Mismatch in Impala Table header column.");

    }

}
