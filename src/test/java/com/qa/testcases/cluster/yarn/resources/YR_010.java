package com.qa.testcases.cluster.yarn.resources;

import com.qa.annotations.Marker;
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

@Marker.YarnResources
@Marker.All
public class YR_010 extends BaseClass {

    /**
     * Verify if on top left "Group by" tab dropdown must be present, and Should contain feilds,
     */
    @Test(dataProvider = "clusterid-data-provider",description="P0- Verify that the top-left group by tab contains a dropdown and should contain fields")
    public void YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields(String clusterId) {
        test = extent.startTest("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields: " + clusterId,
                "Verify if on top left \"Group by\" tab dropdown must be present, and Should contain fields.");
        test.assignCategory(" Cluster - Yarn Resources");
        Log.startTestCase("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        YarnPageObject yarnPageObject = new YarnPageObject(driver);

        //Initialize Yarn Page Object
        Yarn yarn = new Yarn(driver);
        yarn.verifyYarnResourceHeaderisDisplayed();
        Log.info("Yarn Resource Header is displayed.");
        test.log(LogStatus.INFO, "Yarn Resource Header is displayed.");

        //Select cluster id
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();
        Log.info("ClusterId is selected: "+clusterId);
        test.log(LogStatus.INFO, "Cluster Id selected"+clusterId);

        waitExecuter.waitUntilPageFullyLoaded();
        yarn.selectImpalaType("Yarn");
        waitExecuter.waitUntilPageFullyLoaded();
        //Select date
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        Log.info("DatePicker is selected for last30 Days");
        test.log(LogStatus.INFO, "Date is selected from DatePicker");

        //Verify GroupBy drop down and its elements
        waitExecuter.waitUntilElementClickable(yarnPageObject.groupByDropdownButton);
        waitExecuter.sleep(5000);
        yarnPageObject.groupByDropdownButton.click();
        int allGroupByDropDownElements = yarnPageObject.getGroupByDropdownElements.size();
        System.out.println("Elements in Group By DropDown: "+allGroupByDropDownElements);
        Log.info("Group By DropDown is verified, total elements found :"+allGroupByDropDownElements);
        test.log(LogStatus.INFO, "Group By DropDown is verified, total elements found :"+allGroupByDropDownElements);
        Log.endTestCase("YR_010_verifyYarnResourcePageForGroupByDropDownAndItFields");

    }
}
