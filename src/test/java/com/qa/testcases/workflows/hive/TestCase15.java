package com.qa.testcases.workflows.hive;

import com.qa.base.BaseClass;
import com.qa.enums.ApplicationEnum;
import com.qa.enums.WorkflowEnums;
import com.qa.pagefactory.ApplicationPageObject;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.WorkFlowsPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.Login;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.qa.validations.Workflow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Ankur Jaiswal
 * Verify Spark workflow details in Unravel UI.
 */

public class TestCase15 extends BaseClass {
  @Test
  public void validateSparkWorkflow() {
    // Login to the unravel ui
    Login login = new Login(driver);
    login.loginToApp();

    WaitExecuter executer = new WaitExecuter(driver);
    //Click on application tab
    HomePage homePage = new HomePage(driver);
    homePage.clickOnApplications();
    // Go to workflow tab
    ApplicationPageObject applicationPageObject = new ApplicationPageObject(driver);
    applicationPageObject.workflowsTab.click();
    DatePickerPageObject pickerPageObject = new DatePickerPageObject(driver);
    executer.waitUntilElementPresent(pickerPageObject.dateRange);
    executer.sleep(1000);
    // Select last 7 days in date range
    pickerPageObject.dateRange.click();
    executer.sleep(1000);
    JavaScriptExecuter.clickOnElement(driver,pickerPageObject.last7Days);
    // Find spark workBaseClassflow and click on that
    WorkFlowsPageObject workFlowsPageObject = new WorkFlowsPageObject(driver);
    executer.sleep(1000);
    List<WebElement> tableRows = workFlowsPageObject.workflowTable.findElements(By.xpath("//tr"));

    //Validate whether data exist in workflow table
    Workflow.validateValueExistInTable(tableRows);

    executer.sleep(1000);
    executer.waitUntilNumberOfWindowsToBe(2);

    String workflowID = "";
    String parentWindow = driver.getWindowHandle();

    for (String window : driver.getWindowHandles()) {
      if (!parentWindow.equals(window)) {
        driver.switchTo().window(window);
        executer.sleep(5000);
        workflowID = workFlowsPageObject.workflowID.getText();
      }
    }
    driver.switchTo().window(parentWindow);
    homePage.searchIn(workflowID);

    tableRows = applicationPageObject.applicationTable.findElements(By.xpath("//tr"));
    Boolean isApplicationPresent = false;
    for (WebElement row: tableRows) {
      isApplicationPresent = true;
      Assert.assertTrue(row.findElements(By.xpath("//td")).get(ApplicationEnum.APP_NAME_ID.getIndex())
        .getText().trim().contains(workflowID), "Different application Id is displayed.");
    }
    Assert.assertTrue(isApplicationPresent, "No application is not present for the workflow");
  }

}
