package com.qa.scripts;

import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Ankur Jaiswal
 * This class contains all activity performed on home page.
 */

public class HomePage {
    private final TopPanelPageObject topPanel;
    private final HomePageObject homePageObject;
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private CommonPageObject commonPageObject;
    private final UserActions userActions;

    /**
     * Constructor which initialize all necessary things to perform action
     *
     * @param driver : WebDriver instance on which action to be performed
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        topPanel = new TopPanelPageObject(driver);
        homePageObject = new HomePageObject(driver);
        waitExecuter = new WaitExecuter(driver);
        userActions = new UserActions(driver);
    }

    /**
     * Method to click on application tab present on home page
     */
    public void clickOnApplications() {
        topPanel.applicationHome.click();
    }

    /**
     * Method to search any app name, user, table on cluster on home page
     *
     * @param searchData : Data you want to search home page
     * @return Nothing
     */
    public void searchIn(String searchData) {
        topPanel.searchTopButton.sendKeys(searchData);
        topPanel.searchTopButton.submit();
    }

    public List<WebElement> getClusterListFromDropdown() {
        return homePageObject.clusterList;
    }

    public void clickOnClusterDropDown() {
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
    }

    public String getHomePageUrl() {
        waitExecuter.waitUntilElementPresent(homePageObject.unravelLogo);
        return driver.getCurrentUrl();
    }

    public void kpiDisplays() {
        Assert.assertTrue(homePageObject.kpiNodes.isDisplayed(), "Kpi Nodes not displayed on overview page.");
        Assert.assertTrue(homePageObject.kpiVcores.isDisplayed(), "Kpi Vcores not displayed on overview page.");
        Assert.assertTrue(homePageObject.kpiMemory.isDisplayed(), "Kpi Memory not displayed on overview page.");
    }

    public void checkDefaultNodesKpiChkBox() {
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementPresent(homePageObject.activeChkBox);
        Assert.assertTrue(homePageObject.activeChkBox.isSelected(), "Active check box is not checked.");
        Assert.assertTrue(homePageObject.badChkBox.isSelected(), "Active check box is not checked.");
    }

    public void clickActiveChkBox() {
        //homePageObject.activeChkBox.click();
        JavaScriptExecuter.clickOnElement(driver, homePageObject.activeChkBox);

    }

    public void clickBadChkBox() {
        //homePageObject.badChkBox.click();
        JavaScriptExecuter.clickOnElement(driver, homePageObject.badChkBox);
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
    public void selectMultiClusterId(String clusterId) {
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        waitExecuter.waitUntilElementClickable(commonPageObject.clusterDropdown);
        waitExecuter.sleep(8000);
        userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
        waitExecuter.sleep(2000);

        try {userActions.performActionWithPolling(commonPageObject.clusterSearchBox, UserAction.SEND_KEYS,
                clusterId);}
        catch (NoSuchElementException e){
            waitExecuter.sleep(2000);
            userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
            waitExecuter.sleep(2000);
            userActions.performActionWithPolling(commonPageObject.clusterSearchBox, UserAction.SEND_KEYS,
                    clusterId);
        }
        userActions.performActionWithPolling(commonPageObject.clusterSearchFirstField, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(commonPageObject.clusterDropdown);
    }

    //click on cluster drop down all cluster tab workload,cluster,impala,yarn
    public void selectMultiClusterIdClusterPage(String clusterId) {
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        waitExecuter.waitUntilElementClickable(commonPageObject.clusterDropdownPage);
        waitExecuter.sleep(3000);
        userActions.performActionWithPolling(commonPageObject.clusterDropdownPage, UserAction.CLICK);
        userActions.performActionWithPolling(commonPageObject.clusterSearchBox, UserAction.SEND_KEYS,
                clusterId);
        userActions.performActionWithPolling(commonPageObject.clusterSearchFirstField, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(commonPageObject.clusterDropdownPage);
    }

    /* Get the list of clusters from UI */
    public List<String> getListOfClusters(List<WebElement> clusterList) {
        int clusterSize = clusterList.size();
        List<String> listOfClusters = new ArrayList<>();

        if (clusterSize > 0) {
            for (int i = 0; i < clusterList.size(); i++) {
                String clusterNames = clusterList.get(i).getText();
                listOfClusters.add(clusterNames);
            }
        }
        return listOfClusters;
    }

    public void clickOnNodesGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        homePageObject.nodesGraphDownloadMenu.click();
    }

    public void clickOnVCoresGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, homePageObject.vcoresGraphDownloadMenu);
    }

    public void clickOnMemoryGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, homePageObject.memoryGraphDownloadMenu);
    }

    public void clickOnRunningGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, homePageObject.runningGraphDownloadMenu);
    }

    public void clickOnByStatusGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, homePageObject.bystatusGraphDownloadMenu);
    }

    public void clickOnInefficientEventsGraphDownloadMenu() {
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, homePageObject.inefficienteventsGraphDownloadMenu);
    }

    public void downloadNodesGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listnodesGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listnodesGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listnodesGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void downloadVCoresGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listvcoresGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listvcoresGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listvcoresGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void downloadMemoryGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listmemoryGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listmemoryGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listmemoryGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void downloadRunningGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listrunningGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listrunningGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listrunningGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void downloadByStatusGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listbystatusGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listbystatusGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listbystatusGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void downloadInefficientEventsGraphAsFile(String asFile) {
        waitExecuter.sleep(3000);
        int graphDownloadMenuCount = homePageObject.listinefficienteventsGraphDownloadMenu.size();
        if (graphDownloadMenuCount > 0) {
            for (int i = 0; i < graphDownloadMenuCount; i++) {
                if (homePageObject.listinefficienteventsGraphDownloadMenu.get(i).getText().equals(asFile)) {
                    homePageObject.listinefficienteventsGraphDownloadMenu.get(i).click();
                    waitExecuter.sleep(15000);
                }
            }
        }
    }

    public void navigateToHomePage() {
        userActions.performActionWithPolling(topPanel.unravelLogo, UserAction.CLICK);
    }

}