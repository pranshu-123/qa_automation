package com.qa.scripts.clusters.yarn;

import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.ChargebackYarnPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ChargeBackYarn {

    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private ChargebackYarnPageObject chargebackYarnPageObject;
    private static final Logger LOGGER = Logger.getLogger(ChargeBackYarn.class.getName());
    private final UserActions userActions;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver
     *            - WebDriver instance
     */
    public ChargeBackYarn(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        chargebackYarnPageObject = new ChargebackYarnPageObject(driver);
        userActions = new UserActions(driver);
    }

    /**
     * This method used to select yarn in chargeback drowdown displayed at
     * chargeback page. First it click on chargeback tab which navigates to
     * chargeback page then it select impala.
     */
    public void selectYarnChargeback() {
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        // Click on Chargeback tab
        waitExecuter.waitUntilElementClickable(chargebackYarnPageObject.clusterChargeBackTab);
        JavaScriptExecuter.clickOnElement(driver, chargebackYarnPageObject.clusterChargeBackTab);
        // Click on chargeback dropdown
       /* waitExecuter.waitUntilElementPresent(chargebackYarnPageObject.chargeBackDropdownOptionsButton);
        driver.navigate().refresh();
        waitExecuter.waitUntilElementPresent(chargebackYarnPageObject.chargeBackDropdownOptionsButton);
        JavaScriptExecuter.clickOnElement(driver, chargebackYarnPageObject.chargeBackDropdownOptionsButton);
        // Selecting the impala option
        waitExecuter.sleep(3000);
        JavaScriptExecuter.clickOnElement(driver, chargebackYarnPageObject.chargeBackDropdownYarnOption);*/
    }


    public void selectTriggerCondition(String chargeBackType){
        waitExecuter.sleep(1000);
        int allTriggerCondition = chargebackYarnPageObject.chargeBackDropdownsButton.size();
        LOGGER.info("Click on chargeback dropdown: "+ allTriggerCondition);
        for(int i=0; i< allTriggerCondition ; i++){
            if(chargebackYarnPageObject.chargeBackDropdownsButton.get(i).getText().equals(chargeBackType)){
                MouseActions.clickOnElement(driver, chargebackYarnPageObject.chargeBackDropdownsButton.get(i));
                break;
            }
        }
    }

    //click on cluster drop down
    public void selectChargeBackType(String selectChargeBack) {
        waitExecuter.waitUntilElementClickable(chargebackYarnPageObject.chargeBackDropdownButton);
        waitExecuter.sleep(2000);
        userActions.performActionWithPolling(chargebackYarnPageObject.chargeBackDropdownButton, UserAction.CLICK);
        userActions.performActionWithPolling(chargebackYarnPageObject.chargeBackSearchBox, UserAction.SEND_KEYS,
                selectChargeBack);
        waitExecuter.waitUntilPageFullyLoaded();
        userActions.performActionWithPolling(chargebackYarnPageObject.chargeBackField, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(chargebackYarnPageObject.chargeBackDropdownButton);

    }


    /**
     * This method is use to return all results grouped by user table rows.
     *
     * @return - results grouped by user table rows
     */
    public List<WebElement> getResultsGroupedByTableRecords() {
        return chargebackYarnPageObject.resultsGroupedByTableRecords;
    }

    public List<WebElement> getResultsGroupedByTableHeaderNames(){
        return chargebackYarnPageObject.resultsGroupedByTableHeaderNames;
    }

    /**
     * This method is use to return all yarn jobs table rows.
     *
     * @return - Yarn jobs table rows
     */
    public List<WebElement> getYarnJobsTableRecord() {
        return chargebackYarnPageObject.yarnJobsTableRecords;
    }

    public List<WebElement> getYarnJobsTableHeaderNames(){
        return chargebackYarnPageObject.yarnJobsTableHeaderNames;
    }

    /**
     * @return Heading of the page
     */
    public String getHeading() {
        return chargebackYarnPageObject.chargeBackPageHeading.getText();
    }

    /* Get the list of clusters from UI */
    public int getListOfClusters(List<WebElement> clusterList) {
        LOGGER.info("Number of clusters available are : " + clusterList.size());
        int clusterSize = clusterList.size();
        if (clusterSize > 0) {
            for (int i = 0; i < clusterList.size(); i++) {
                String clusterNames = chargebackYarnPageObject.listOfClusters.get(i).getText();
                LOGGER.info("The cluster name is : " + clusterNames);
            }
        }
        return clusterSize;
    }

    public void checkForListOfClusterAvailable(){
        // Click on cluster dropdown
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(chargebackYarnPageObject.containerDropdownArrow);
        chargebackYarnPageObject.containerDropdownArrow.click();
        // Get the list of clusters
        Assert.assertNotNull(getListOfClusters(chargebackYarnPageObject.listOfClusters), "There are no cluster available");
    }

    public void clickOnGroupBySearchBox(){
        // Click on yarn chargeback group by combobox
        waitExecuter.sleep(1000);
        //chargebackYarnPageObject.groupBySearchBox.click();
        JavaScriptExecuter.clickOnElement(driver,chargebackYarnPageObject.groupBySearchBox);
    }

    public void verifyNumberOfOptionsInGroupBy(){
        // Verify the number of options available in group by options
        waitExecuter.sleep(1000);
        Assert.assertTrue(chargebackYarnPageObject.listOfGroupByOptions.size()>0,"GroupBy option " +
                "is not available");
//        Assert.assertEquals(chargebackYarnPageObject.listOfGroupByOptions.size(), 12,
//                "Available options should be 12 but present are " + chargebackYarnPageObject.listOfGroupByOptions.size());
    }

    public void selectOptionsInGroupBy(String optionName){
        // Get the list of webelements of groupby options
        List<WebElement> listOfWebElemnts = chargebackYarnPageObject.listOfGroupByOptions;
        // Iterate Webelement list to get the value of each element
        for (int i = 0; i < listOfWebElemnts.size(); i++) {
            System.out.println(listOfWebElemnts.get(i).getText());
            if(listOfWebElemnts.get(i).getText().contains(optionName)){
                listOfWebElemnts.get(i).click();
                waitExecuter.sleep(2000);
            }
        }
    }

    /* Validate groupBy options */
    public Boolean validateGroupByOptions() {
        // Get the list of webelements of groupby options
        List<WebElement> listOfWebElemnts = chargebackYarnPageObject.listOfGroupByOptions;
        // Empty array to add displayed group by options
        ArrayList<String> listOfGroupByOptions = new ArrayList<String>();
        // Iterate Webelement list to get the value of each element
        for (int i = 0; i < listOfWebElemnts.size(); i++) {
            listOfGroupByOptions.add(listOfWebElemnts.get(i).getText());
        }
        //List<String> definedGroupByOption = Arrays.asList("Application Type", "User", "Real User","Queue", "dept", "project",
        //        "realUser","dbs", "inputTables", "priority", "team","outputTables");
        LOGGER.info("Actual ist of options" + listOfGroupByOptions);
        //Boolean compareGroupByOptions = listOfGroupByOptions.equals(definedGroupByOption);
        Boolean compareGroupByOptions = listOfGroupByOptions.isEmpty();
        //LOGGER.info("Expected list of options" + definedGroupByOption);
        return compareGroupByOptions;

    }

    //validate column name in GroupBy Table, i.e chargeBack table
    public Boolean validateHeaderCoulmnNameInGroupByTable(){
        System.out.println("Size of Headers in Group By Table: "+ getResultsGroupedByTableHeaderNames().size());
        //Get List of groupby table column names
        List<WebElement> listOfGroupByTableColumnNames = getResultsGroupedByTableHeaderNames();
        //Empty array to add group by table coumn name
        ArrayList<String> listOfGroupByColumnNames = new ArrayList<String>();

        // Iterate Webelement list to get the value of each element
        for(int i=0; i<=listOfGroupByTableColumnNames.size()-1; i++){
            listOfGroupByColumnNames.add(listOfGroupByTableColumnNames.get(i).getText());
        }
        List<String> definedGroupByColumnNames = Arrays.asList("Job Count", "CPU Hours", "Memory Hours");
        LOGGER.info("Actual ist of options: " + listOfGroupByColumnNames);
        Boolean boolColumnNames = listOfGroupByColumnNames.containsAll(definedGroupByColumnNames);
        //boolean boolColumnNames = listOfGroupByColumnNames.stream().anyMatch(element -> definedGroupByColumnNames.contains(element));
        return boolColumnNames;
    }

    public Boolean validateHeaderColumnNameInYarnJobsTable(){
        System.out.println("Size of Headers in Yarn Jobs Table: "+getYarnJobsTableHeaderNames().size());
        List<WebElement> listOfYarnJobsTableHeaderNames = getYarnJobsTableHeaderNames();

        ArrayList<String> listOfYarnJobsColumnNames = new ArrayList<String>();

        for(int i=0; i <listOfYarnJobsTableHeaderNames.size()-1; i++){
            listOfYarnJobsColumnNames.add(listOfYarnJobsTableHeaderNames.get(i).getText());
        }
        List<String> definedYarnJobsColumnNames = Arrays.asList("Type", "State", "User", "Real User", "App Name / ID", "Start Time", "Finished Time", "Queue",
                "Memory MB Seconds", "VCore Seconds");
        Boolean boolColumnNames = listOfYarnJobsColumnNames.equals(definedYarnJobsColumnNames);
        return boolColumnNames;
    }

    public void clickOnIconGearUnChekckTypeChkBox(){
        waitExecuter.waitUntilElementPresent(chargebackYarnPageObject.iconGear);
        JavaScriptExecuter.scrollOnElement(driver, chargebackYarnPageObject.iconGear);
        chargebackYarnPageObject.iconGear.click();
        waitExecuter.sleep(2000);
        chargebackYarnPageObject.iconGearTypeChkBox.click();
    }

}