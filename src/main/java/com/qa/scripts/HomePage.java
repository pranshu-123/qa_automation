package com.qa.scripts;

import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

/**
 * @author Ankur Jaiswal
 * This class contains all activity performed on home page.
 */

public class HomePage {
  private final TopPanelPageObject topPanel;
  private final HomePageObject homePageObject;
  private final WaitExecuter waitExecuter;
  private final WebDriver driver;
  /**
   * Constructor which initialize all necessary things to perform action
   * @param driver : WebDriver instance on which action to be performed
   */
  public HomePage(WebDriver driver) {
    this.driver = driver;
    topPanel = new TopPanelPageObject(driver);
    homePageObject = new HomePageObject(driver);
    waitExecuter = new WaitExecuter(driver);
  }

  /**
   * Method to click on application tab present on home page
   */
  public void clickOnApplications(){
    topPanel.applicationHome.click();
  }

  /**
   * Method to search any app name, user, table on cluster on home page
   * @param searchData : Data you want to search home page
   * @return Nothing
   */
  public void searchIn(String searchData){
    topPanel.searchTopButton.sendKeys(searchData);
    topPanel.searchTopButton.submit();
  }

  public List<WebElement> getClusterListFromDropdown() {
    return homePageObject.clusterList;
  }

  public void clickOnClusterDropDown() {
    waitExecuter.sleep(3000);
    CommonPageObject commonPageObject = new CommonPageObject(driver);
    commonPageObject.clusterDropdown.click();
  }

  public String getHomePageUrl(){
    waitExecuter.waitUntilElementPresent(homePageObject.unravelLogo);
    return driver.getCurrentUrl();
  }

  public void kpiDisplays(){
    Assert.assertTrue(homePageObject.kpiNodes.isDisplayed(), "Kpi Nodes not displayed on overview page.");
    Assert.assertTrue(homePageObject.kpiVcores.isDisplayed(), "Kpi Vcores not displayed on overview page.");
    Assert.assertTrue(homePageObject.kpiMemory.isDisplayed(), "Kpi Memory not displayed on overview page.");
  }

  public void checkDefaultNodesKpiChkBox(){
    waitExecuter.sleep(2000);
    waitExecuter.waitUntilElementPresent(homePageObject.activeChkBox);
    Assert.assertTrue(homePageObject.activeChkBox.isSelected(), "Active check box is not checked.");
    Assert.assertTrue(homePageObject.badChkBox.isSelected(), "Active check box is not checked.");
  }

  public void clickActiveChkBox(){
    //homePageObject.activeChkBox.click();
    JavaScriptExecuter.clickOnElement(driver,homePageObject.activeChkBox);

  }
  public void clickBadChkBox(){
    //homePageObject.badChkBox.click();
    JavaScriptExecuter.clickOnElement(driver,homePageObject.badChkBox);
  }

  public void clickTotalAvailable() {
    //homePageObject.TotalAvailable.click();
    JavaScriptExecuter.clickOnElement(driver, homePageObject.AvailableChkBox);

  }

  public void clickTotalAllocated() {
    //homePageObject.TotalAllocated.click();
    JavaScriptExecuter.clickOnElement(driver, homePageObject.AllocatedChkBox);
  }

  public void clickSuccess() {
    //homePageObject.SuccessChkBox.click();
    JavaScriptExecuter.clickOnElement(driver, homePageObject.SuccessChkBox);

  }

  public void clickFailed() {
    //homePageObject.FailedChkBox.click();
    JavaScriptExecuter.clickOnElement(driver, homePageObject.FailedChkBox);

  }

  //click on cluster drop down
  public void selectMultiClusterId(String clusterId){
    waitExecuter.sleep(2000);
    CommonPageObject commonPageObject = new CommonPageObject(driver);
    MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
    waitExecuter.sleep(2000);
    System.out.println("Size of cluster in dropdown: "+commonPageObject.clustersList.size());

    waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
    commonPageObject.clusterSearchBox.sendKeys(clusterId);
    commonPageObject.clusterSearchFirstField.click();
    waitExecuter.sleep(2000);
  }


}
