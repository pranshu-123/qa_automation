package com.qa.scripts.clusters.yarn;

import com.qa.pagefactory.clusters.YarnPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Briender Kumar
 */

public class Yarn {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private YarnPageObject yarnPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     * @param driver
     * - WebDriver instance
     */
    public Yarn(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        yarnPageObject = new YarnPageObject(driver);
    }

    public void verifyGroupByDropDownIsPresent(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDropdownButton);
        yarnPageObject.groupByDropdownButton.click();
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByUser);
    }

    public void verifyYarnResourceHeaderisDisplayed(){
        System.out.println(yarnPageObject.clusterResourcesTab.getText());
        waitExecuter.waitUntilElementClickable(yarnPageObject.clusterResourcesTab);
        JavaScriptExecuter.clickOnElement(driver, yarnPageObject.clusterResourcesTab);
        waitExecuter.waitUntilElementPresent(yarnPageObject.getResourcesPageHeader);
        Log.info("Yarn Page Header is: "+yarnPageObject.getResourcesPageHeader.getText());
    }

    public void clickOnGroupByDropDown(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDropdownButton);
        yarnPageObject.groupByDropdownButton.click();
    }

    public void selectApplicationType(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByApplicationType);
        yarnPageObject.groupByApplicationType.click();
        waitExecuter.sleep(2000);
    }

    public void selectUser(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByUser);
        yarnPageObject.groupByUser.click();
        waitExecuter.sleep(2000);
    }

    public void selectQueue(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByQueue);
        yarnPageObject.groupByQueue.click();
        waitExecuter.sleep(2000);
    }

    public void selectDept(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByDept);
        yarnPageObject.groupByDept.click();
        waitExecuter.sleep(2000);
    }

    public void selectProject(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByProject);
        yarnPageObject.groupByProject.click();
        waitExecuter.sleep(2000);
    }

    public void selectRealUser(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByRealUser);
        yarnPageObject.groupByRealUser.click();
        waitExecuter.sleep(2000);
    }

    public void selectDbs(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupBydbs);
        yarnPageObject.groupBydbs.click();
        waitExecuter.sleep(2000);
    }

    public void selectInputTables(){
        waitExecuter.waitUntilElementPresent(yarnPageObject.groupByInputTables);
        yarnPageObject.groupByInputTables.click();
        waitExecuter.sleep(2000);
    }

    public List<String> getAllDefaultSelectedFilterElements(){
        yarnPageObject.filterInput.click();
        System.out.println("Default filter elements size: "+yarnPageObject.defaultSelectedFilterElements.size());
        Log.info("Default filter elements size: "+yarnPageObject.defaultSelectedFilterElements.size());

        List<String> listOfAllFilterElements = new ArrayList<String>();
        if(yarnPageObject.defaultSelectedFilterElements.size() > 0){
            for(WebElement e : yarnPageObject.defaultSelectedFilterElements){
                Log.info("Filter elements: "+e.getText() );
                listOfAllFilterElements.add(e.getText());
            }
        }
        return listOfAllFilterElements;
    }

    public List<String> getAllFilterElements(){
        yarnPageObject.filterInput.click();
        System.out.println("Default filter elements size: "+yarnPageObject.filterElements.size());
        Log.info("Default filter elements size: "+yarnPageObject.filterElements.size());

        List<String> listOfAllFilterElements = new ArrayList<String>();
        if(yarnPageObject.filterElements.size() > 0){
            for(WebElement e : yarnPageObject.filterElements){
                Log.info("Filter elements: "+e.getText() );
                listOfAllFilterElements.add(e.getText());
            }
        }
        return listOfAllFilterElements;
    }

    public boolean verifyFilterElements(){
        yarnPageObject.filterInput.click();
        System.out.println("Default filter elements size: "+yarnPageObject.filterElements.size());
        Log.info("Default filter elements size: "+yarnPageObject.filterElements.size());

        List<String> listOfAllFilterElements = new ArrayList<String>();

        if(yarnPageObject.filterElements.size() > 0){
            for(int i=0 ; i <yarnPageObject.filterElements.size() ; i++){
                Log.info("Filter elements: "+yarnPageObject.filterElements.get(i).getText() );
                listOfAllFilterElements.add(yarnPageObject.filterElements.get(i).getText());
            }
        }

        List<String> defineListOfYarnApp = Arrays.asList("MAPREDUCE","SPARK","TEZ");
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

}
