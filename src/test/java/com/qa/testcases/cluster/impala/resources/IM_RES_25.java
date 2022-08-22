package com.qa.testcases.cluster.impala.resources;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.ImpalaPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.impala.Impala;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

@Marker.ImpalaResources
@Marker.All
public class IM_RES_25 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(IM_RES_25.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description ="P0-Verify the UI should open the Impala details page.")
    public void IM_RES_25_verifyusertotheapplicationdetailspage(String clusterId) {
        test = extent.startTest("IM_RES_25.verifyusertotheapplicationdetailspage (" + clusterId + ")", "Verify UI should open the Impala details page.");
        test.assignCategory(" Cluster/Impala Resources");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HomePage homePage = new HomePage(driver);
        Impala impala = new Impala(driver);

        ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);

        //Select impala tab
        test.log(LogStatus.INFO, "Go to resource page");
        LOGGER.info("Select impala from dropdown");
        impala.selectImpalaResource();
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        LOGGER.info("Select cluster : " + clusterId);
        // Select the cluster
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);

        impala.selectImpalaType("Impala");
        waitExecuter.sleep(3000);


        try {
            DatePicker datePicker = new DatePicker(driver);
            datePicker.clickOnDatePicker();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            datePicker.selectLast30Days();
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            test.log(LogStatus.INFO, "Select Date from DatePicker.");

            waitExecuter.waitUntilElementClickable(impalaPageObject.groupByDropdownButton);
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            MouseActions.clickOnElement(driver, impalaPageObject.groupByDropdownButton);
            MouseActions.clickOnElement(driver, impalaPageObject.groupByQueueList);
            waitExecuter.waitUntilElementClickable(impalaPageObject.resourceUsagePointer);
            JavaScriptExecuter.scrollViewWithYAxis(driver, -100);

            int scrollY = 150;
            JavaScriptExecuter.scrollViewWithYAxis(driver, scrollY);
            //    scrollY = scrollY + datePicker.getDatePickerYPosition();
            //TBD
            //1. Click on Memory Consumption at that point where Impala Queries Table should get populated.


      /*  //Impala Queries table is populated and validate its Header columns
        impala.getImpalaJobsTableRecord();
        //Impala Table Header Text: 24 Impala queries running between  26-08-2020 05:30:00 and 27-08-2020 05:30:00
        System.out.println("Impala Table Header Text: "+impala.getImpalaQueriesTableHeaderText());
        test.log(LogStatus.INFO, "Impala Queuries table is populated.");
        Assert.assertTrue(impala.validateHeaderColumnNameInImpalaQueriesTable(), "Mismatch in Impala Table header column.");

        impala.selecttable();
        executer.sleep(1000);
        test.log(LogStatus.PASS, "verify Impala details page.");*/
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            test.log(LogStatus.INFO, "Selecting the queue: " + ex + " in filter.");
        }
    }
    }


