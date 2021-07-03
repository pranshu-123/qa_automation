package com.qa.scripts.clusters.yarn;

import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.YarnPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Briender Kumar
 * This class contains all Yarn related action methods
 */

public class Yarn {
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private YarnPageObject yarnPageObject;
    private static final Logger LOGGER = Logger.getLogger(Yarn.class.getName());
    List<String> listOfAllFilterElements = new ArrayList<String>();
    private final UserActions userActions;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public Yarn(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        yarnPageObject = new YarnPageObject(driver);
        userActions = new UserActions(driver);
    }

    /* Check for Group Drop Down */
    public void verifyGroupByDropDownIsPresent(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDropdownButton);
        MouseActions.clickOnElement(driver, yarnPageObject.groupByDropdownButton);
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByUser);
    }

    /* Check for Yarn Resource header is displayed */
    public void verifyYarnResourceHeaderisDisplayed(){
        LOGGER.info("Cluster Resources Tab text: "+yarnPageObject.clusterResourcesTab.getText());
        waitExecuter.waitUntilElementClickable(yarnPageObject.clusterResourcesTab);
        JavaScriptExecuter.clickOnElement(driver, yarnPageObject.clusterResourcesTab);
        waitExecuter.waitUntilElementPresent(yarnPageObject.getResourcesPageHeader);
        LOGGER.info("Yarn Page Header is: "+yarnPageObject.getResourcesPageHeader.getText());
    }

    //click on cluster drop down
    public void selectImpalaType(String impalaType) {
        // Click on Impala chargeback dropdown
        userActions.performActionWithPolling(yarnPageObject.yarnResourceType, UserAction.CLICK);
        List<WebElement> userList = yarnPageObject.selectYarn;
        String usernameSelected = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getText().equals(impalaType)) {
                usernameSelected = userList.get(i).getText();
                LOGGER.info("Selected username from dropdown " + usernameSelected);
                waitExecuter.waitUntilElementClickable(userList.get(i));
                userActions.performActionWithPolling(userList.get(i), UserAction.CLICK);
                waitExecuter.sleep(2000);
            }
        }
    }

    /* Click on Group dropdown */
    public void clickOnGroupByDropDown(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDropdownButton);
        waitExecuter.sleep(5000);
        yarnPageObject.groupByDropdownButton.click();
    }

    /* Select Application Type */
    public void selectApplicationType(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByApplicationType);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByApplicationType.click();
    }

    /* Select User */
    public void selectUser(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByUser);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByUser.click();
    }

    /* Select Queue */
    public void selectQueue(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByQueue);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByQueue.click();

    }

    /* Select Department */
    public void selectDept(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDept);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByDept.click();

    }

    /* Select Project */
    public void selectProject(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByProject);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByProject.click();
    }

    /* Select Real User */
    public void selectRealUser(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByRealUser);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByRealUser.click();

    }

    /* Select DBS */
    public void selectDbs(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupBydbs);
        waitExecuter.sleep(2000);
        yarnPageObject.groupBydbs.click();
    }

    /* Select InputTable */
    public void selectInputTables(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByInputTables);
        waitExecuter.sleep(2000);
        yarnPageObject.groupByInputTables.click();

    }

    /* Get list of default filter elements */
    public List<String> getAllDefaultSelectedFilterElements(){
        waitExecuter.sleep(3000);
        yarnPageObject.filterInput.click();
        List<String> listOfAllDefaultFilterElements = new ArrayList<String>();
        if(yarnPageObject.defaultSelectedFilterElements.size() > 0){
            for(WebElement e : yarnPageObject.defaultSelectedFilterElements){
                Log.info("Filter elements: "+e.getText() );
                listOfAllDefaultFilterElements.add(e.getText());
            }
        }
        return listOfAllDefaultFilterElements;
    }

    /* Get list of all filter elements */
    public List<String> getAllFilterElements(){
        waitExecuter.sleep(3000);
        yarnPageObject.filterInput.click();
        waitExecuter.sleep(1000);
        if(yarnPageObject.filterElements.size() > 0){
            for(WebElement e : yarnPageObject.filterElements){
                Log.info("Filter elements: "+e.getText() );
                listOfAllFilterElements.add(e.getText());
            }
        }
        return listOfAllFilterElements;
    }

    /* Verify filter elements */
    public boolean verifyFilterElements(){
        List<String> defineListOfYarnApp = Arrays.asList("MAPREDUCE","SPARK","TEZ","yarn-service");
        boolean boolYarnApp = defineListOfYarnApp.containsAll(listOfAllFilterElements);
        return boolYarnApp;
    }

    /**
     * This method clear the filter present on impala page
     */
    public void clearFilter() {
        final List<WebElement> filterItems = yarnPageObject.filterRemoveElements;
        while (filterItems.size() != 0) {
            JavaScriptExecuter.clickOnElement(driver, filterItems.get(0));
            waitExecuter.waitUntilPageFullyLoaded();
        }
    }

    /* Check for Vcores Graph */
    public Boolean isVCoresGraphPresent() {
        if (yarnPageObject.vcoresSVGGraph.isDisplayed()) {
            if (yarnPageObject.vcoresTotalAllocatedChkBox.isDisplayed()) {
                waitExecuter.sleep(1000);
                String vcoreTotalAllocatedText = yarnPageObject.vcoresTotalAllocatedChkBox.getText();
                LOGGER.info("The VCores TotalAllocated graph is being displayed for nodes: " + vcoreTotalAllocatedText);
                if (yarnPageObject.vcoresTotalAvailableChkBox.isDisplayed()) {
                    waitExecuter.sleep(1000);
                    String vcoreTotalAvailableText = yarnPageObject.vcoresTotalAvailableChkBox.getText();
                    LOGGER.info("The VCores TotalAvailable graph is being displayed for nodes: " + vcoreTotalAvailableText);

                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* Check for Memory Graph */
    public Boolean isMemoryGraphPresent() {
        if(yarnPageObject.memorySVGGraph.isDisplayed()){
            if( yarnPageObject.memoryTotalAllocatedChkBox.isDisplayed()){
                waitExecuter.sleep(1000);
                String memoryTotalAllocatedText = yarnPageObject.memoryTotalAllocatedChkBox.getText();
                LOGGER.info("The Memory TotalAllocated graph is being displayed for nodes: " + memoryTotalAllocatedText);
                if(yarnPageObject.memoryTotalAvailableChkBox.isDisplayed()){
                    waitExecuter.sleep(1000);
                    String memoryTotalAvailableText = yarnPageObject.memoryTotalAvailableChkBox.getText();
                    LOGGER.info("The Memory TotalAvailable graph is being displayed for nodes: " + memoryTotalAvailableText);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }


    /* Check for Vcores App graph */
    public Boolean isVCoresApplicationGraphPresent(List<String> allDefaultFilterElement) {

        List<WebElement> parentVCoresNodeList = yarnPageObject.vcoreApplicationChkBoxList;
        waitExecuter.sleep(2000);
        if (parentVCoresNodeList.size() > 0) {
            List<String> vcoresNodeListText = new ArrayList<String>();
            for (int i = 0; i < parentVCoresNodeList.size(); i++) {
                String text = parentVCoresNodeList.get(i).getText();
                vcoresNodeListText.add(text);
            }
            LOGGER.info("The vcores graph is being displayed for nodes: " + vcoresNodeListText);

            if(compareList(allDefaultFilterElement,vcoresNodeListText)){
                return true;
            }
            return false;
        }
        return  false;
    }

    /* Check for Memory App graph */
    public Boolean isMemoryApplicationGraphPresent(List<String> allDefaultFilterElement) {

        List<WebElement> parentMemoryNodeList = yarnPageObject.memoryApplicationChkBoxList;
        waitExecuter.sleep(2000);
        if (parentMemoryNodeList.size() > 0) {
            List<String> memoryNodeListText = new ArrayList<String>();
            for (int i = 0; i < parentMemoryNodeList.size(); i++) {
                String text = parentMemoryNodeList.get(i).getText();
                memoryNodeListText.add(text);
            }
            LOGGER.info("The memory graph is being displayed for nodes: " + memoryNodeListText);

            if(compareList(allDefaultFilterElement,memoryNodeListText)){
                return true;
            }
            return false;
        }
        return  false;
    }

    /* Check for comparison two list */
    public  boolean compareList(List ls1, List ls2){
        return ls1.containsAll(ls2) && ls1.size() == ls2.size() ? true :false;
    }

}
