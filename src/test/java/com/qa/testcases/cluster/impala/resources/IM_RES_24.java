package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
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

import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
@Marker.ImpalaResources
@Marker.All
public class IM_RES_24  extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_24.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the memory chart should displays the top-five hosts")
    public void verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip(String clusterId) {
        test = extent.startTest("IM_RES_24.verifyGroupByFilterForUserHoverMemoryGraphCompareImpalaTblToolTip (" + clusterId + ")", "Verify if more than 5 hosts exist, the memory chart displays the top-5 hosts .");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Impala impala = new Impala(driver);
        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        //Select cluster id
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        //Select date
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        test.log(LogStatus.INFO, "Select Date from DatePicker.");

        waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
        impalaPageObject.groupByUserList.click();
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
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
