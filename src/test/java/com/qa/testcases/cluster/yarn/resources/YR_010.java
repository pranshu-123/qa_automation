package com.qa.testcases.cluster.yarn.resources;

import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.YarnPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.yarn.Yarn;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class YR_010 extends BaseClass {

    /**
     * Verify if on top left "Group by" tab dropdown must be present, and Should contain feilds,
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields(String clusterId) {
        test = extent.startTest("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields: " + clusterId,
                "Verify if on top left \"Group by\" tab dropdown must be present, and Should contain fields.");
        test.assignCategory("4620 Cluster - Yarn Resources");
        Log.startTestCase("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields");

        //Initialize Yarn Page Object
        YarnPageObject yarnPageObject = new YarnPageObject(driver);
        System.out.println(yarnPageObject.clusterResourcesTab.getText());
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilElementClickable(yarnPageObject.clusterResourcesTab);
        JavaScriptExecuter.clickOnElement(driver, yarnPageObject.clusterResourcesTab);
        waitExecuter.waitUntilElementPresent(yarnPageObject.getResourcesPageHeader);
        Log.info("Yarn Page Header is: "+yarnPageObject.getResourcesPageHeader.getText());

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        Log.info("ClusterId is selected: "+clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        Log.info("DatePicker is selected for last30 Days");

        Yarn yarn = new Yarn(driver);
        yarn.verifyGroupByDropDownIsPresent();
        Log.info("Group By DropDown is verified");

        test.log(LogStatus.INFO, "Group By DropDown is verified");
        Log.endTestCase("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields");

    }
}
