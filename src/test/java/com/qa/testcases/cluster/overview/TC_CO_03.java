package com.qa.testcases.cluster.overview;

import com.qa.base.BaseClass;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CO_03 extends BaseClass {

  @Test(dataProvider = "clusterid-data-provider")
  public void TC_CO_03_verifyCustomRangeInDatepicker(String clusterId) {
    test = extent.startTest("TC_CO_03_verifyCustomRangeInDatepicker: "+clusterId, "Verify Custom Range in datepicker filter ");
    test.assignCategory("4620 - Cluster Overview");

    HomePage homePage = new HomePage(driver);
    homePage.selectMultiClusterId(clusterId);

    // Click on datepicker button
    DatePicker datePicker = new DatePicker(driver);
    datePicker.clickOnDatePicker();

    // Click on custom range
    datePicker.selectCustomRange();
    Assert.assertTrue(datePicker.datePickerPageObject.customRangeStartDate.isDisplayed(),
      "Start Date is not displayed");

    test.log(LogStatus.PASS, "Start Date field is successfully verified in custom date range");

    Assert.assertTrue(datePicker.datePickerPageObject.customRangeEndDate.isDisplayed(),
      "End Date is not displayed");
    test.log(LogStatus.PASS, "End Date field is successfully verified in custom date range");
  }
}
