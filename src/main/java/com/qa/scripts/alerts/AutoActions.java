package com.qa.scripts.alerts;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.pagefactory.alerts.NewAutoActionPolicyPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Birender Kumar
 */
public class AutoActions {

    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private AutoActionsPageObject autoActionsPageObject;
    private NewAutoActionPolicyPageObject newAutoActionPolicyPageObject;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AutoActions.class.getName());

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public AutoActions(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        autoActionsPageObject = new AutoActionsPageObject(driver);
        newAutoActionPolicyPageObject = new NewAutoActionPolicyPageObject(driver);
    }

    public void setupForAutoActionsPage(){
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.alerts);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.alerts);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.alerts);
        waitExecuter.waitUntilElementPresent(autoActionsPageObject.autoActionComponentHeader);
    }


    public String getAutoActionsHeader(){
        waitExecuter.sleep(3000);
        logger.info("Auto Actions component Header: "+ autoActionsPageObject.autoActionComponentHeader.getText());
        return autoActionsPageObject.autoActionComponentHeader.getText();
    }

    public void clickOnNewAutoActionBtn(){
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, autoActionsPageObject.newAutoActionBtn);
    }

    public boolean validateNewAutoActionPolicyPageDisplayed(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilUrlContains("/autoactions/add");
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.newAutoActionPolicyHeader);
        if(newAutoActionPolicyPageObject.newAutoActionPolicyHeader.getText().equals("New AutoAction Policy")){
            return true;
        }
        return false;
    }

    public void clickOnTriggerConditionBtn(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.triggerConditionsBtn);
    }

    public void clickOnTriggerConditionBtn2(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.triggerConditionsBtn2);
    }

    public void clickOnnJoinTxtBtn(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.joinTxtBtn);
    }


    public void selectTriggerCondition(String triggerConditionAppType){
        waitExecuter.sleep(1000);
        int allTriggerCondition = newAutoActionPolicyPageObject.selectTriggerConditions.size();
        logger.info("allTriggerCondition count: "+ allTriggerCondition);
        for(int i=0; i< allTriggerCondition ; i++){
            if(newAutoActionPolicyPageObject.selectTriggerConditions.get(i).getText().equals(triggerConditionAppType)){
                MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.selectTriggerConditions.get(i));
                break;
            }
        }
    }

    public void selectORANDOptions(String optionType){
        waitExecuter.sleep(1000);
        int allOptions = newAutoActionPolicyPageObject.getSelectORANDOptions.size();
        logger.info("allOptions count:"+ allOptions);
        for(int i=0; i< allOptions ; i++){
            String actualOption = newAutoActionPolicyPageObject.getSelectORANDOptions.get(i).getText();
            if(actualOption.equals(optionType)){
                MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.getSelectORANDOptions.get(i));
                break;
            }
        }
    }

    public void selectTriggerConditionMetric(String triggerConditionAppType){
        waitExecuter.sleep(1000);
        int allTriggerCondition = newAutoActionPolicyPageObject.selectTriggerConditions.size();
        for(int i=0; i< allTriggerCondition ; i++){
            if(newAutoActionPolicyPageObject.selectTriggerConditions.get(i).getText().equals(triggerConditionAppType)){
                MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.selectTriggerConditions.get(i));
            }
        }
    }

    public void clickOnSaveBtn(){
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.saveBtn);
    }

    public void enterNewAutoActionPolicyDetails(String policyName, String triggerCondition){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
        clickOnTriggerConditionBtn();
        selectTriggerCondition(triggerCondition);
        waitExecuter.sleep(2000);
    }

    public void enterNewAutoActionPolicyDetails(String policyName, String triggerCondition, String triggerValue){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
        clickOnTriggerConditionBtn();
        selectTriggerCondition(triggerCondition);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.triggerConditionValue.sendKeys(triggerValue);
    }

    public void enterRuleSetInPolicyWithOption(String triggerCondition, String triggerValue, String optionType){
        waitExecuter.sleep(2000);
        clickOnTriggerConditionBtn2();
        waitExecuter.sleep(2000);
        selectTriggerCondition(triggerCondition);
        waitExecuter.sleep(2000);
        clickOnnJoinTxtBtn();
        waitExecuter.sleep(2000);
        selectORANDOptions(optionType);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.triggerConditionValue2.sendKeys(triggerValue);
    }

    public void enterNewAutoActionPolicyDetails(String policyName, String triggerCondition, String triggerValue, String policyDescrption){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
        clickOnTriggerConditionBtn();
        selectTriggerCondition(triggerCondition);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.triggerConditionValue.sendKeys(triggerValue);
        newAutoActionPolicyPageObject.policyDescription.sendKeys(policyDescrption);

    }

    public void selectActions(String inputAction){
        waitExecuter.sleep(2000);
        List<WebElement> webElements = newAutoActionPolicyPageObject.listOfActions;
        for(WebElement actions : webElements){
            if(actions.getText().equals(inputAction)){
                actions.click();
                return;
            }
        }
    }

    public void enterEmail(String inputAction, String email){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.actionEle);
        newAutoActionPolicyPageObject.actionEle.sendKeys(email.toLowerCase());
        waitExecuter.waitUntilElementClickable(newAutoActionPolicyPageObject.addEmailEleBtn);
        MouseActions.clickOnElement(driver,newAutoActionPolicyPageObject.addEmailEleBtn);
    }

    public void enterEmail(String inputAction, String email1, String email2){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.actionEle);
        newAutoActionPolicyPageObject.actionEle.sendKeys(email1.toLowerCase());
        waitExecuter.waitUntilElementClickable(newAutoActionPolicyPageObject.addEmailEleBtn);
        newAutoActionPolicyPageObject.actionEle.sendKeys(email2.toLowerCase());
        waitExecuter.waitUntilElementClickable(newAutoActionPolicyPageObject.addEmailEleBtn);
        MouseActions.clickOnElement(driver,newAutoActionPolicyPageObject.addEmailEleBtn);
    }

    public void enterHttpPostUrl(String inputAction, String httpPostUrl){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.actionEleToHttpPost);
        newAutoActionPolicyPageObject.actionEleToHttpPost.sendKeys(httpPostUrl.toLowerCase());
        waitExecuter.waitUntilElementClickable(newAutoActionPolicyPageObject.addUrlBtn);
        MouseActions.clickOnElement(driver,newAutoActionPolicyPageObject.addUrlBtn);
    }

    public void enterPostToSlackUrl(String inputAction, String postToSlackUrl, String postToSlackToken){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.actionElePostToSlack);
        newAutoActionPolicyPageObject.actionElePostToSlack.sendKeys(postToSlackUrl);
        waitExecuter.waitUntilElementClickable(newAutoActionPolicyPageObject.addWebhookUrlBtn);
        MouseActions.clickOnElement(driver,newAutoActionPolicyPageObject.addWebhookUrlBtn);

        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.webHookTokenEle);
        newAutoActionPolicyPageObject.webHookTokenEle.sendKeys(postToSlackToken.toLowerCase());
    }

    public void enterMoveAppToQueueName(String inputAction, String queueName){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.queueNameEle);
        newAutoActionPolicyPageObject.queueNameEle.sendKeys(queueName);
    }

    public void selectKillAppAction(String inputAction){
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(inputAction);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.killAppChkBoxEle);
    }

    public boolean verifyErrorMsg(String errorMsg){
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.fatalMsgTextEle);
        String fatalMsgText = newAutoActionPolicyPageObject.fatalMsgTextEle.getText();
        String expectedErrMsgText = errorMsg.toString();

        if(fatalMsgText.contains(expectedErrMsgText)){
            return true;
        }
        return false;
    }

    public void selectMetric(String inputMetric){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.metricCombo);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.metricComboSearch.sendKeys(inputMetric);
        waitExecuter.sleep(5000);
        //Assert.assertTrue(newAutoActionPolicyPageObject.listOfMetric.isEmpty(), "No Metric value found in combo box.");
        List<WebElement> webElements = newAutoActionPolicyPageObject.listOfMetric;
        if(webElements.get(0).getText().equals(inputMetric)){
            MouseActions.clickOnElement(driver, webElements.get(0));
        }else{
            logger.info("No Metric value found.");
        }
    }

    public void createNewAutoActionPolicy(Map<String,String> mapPolicyData){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(mapPolicyData.get("PolicyName"));
        clickOnTriggerConditionBtn();
        selectTriggerCondition(mapPolicyData.get("TriggerCondition"));

        Select selectAppType = new Select(newAutoActionPolicyPageObject.selectAppType);
        selectAppType.selectByValue(mapPolicyData.get("ApplicationType"));

        waitExecuter.sleep(2000);

        selectMetric(mapPolicyData.get("Metric"));

        waitExecuter.sleep(2000);

        newAutoActionPolicyPageObject.triggerConditionValue.sendKeys(mapPolicyData.get("Value"));
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.actionButton);
        waitExecuter.sleep(2000);
        selectActions(mapPolicyData.get("Actions"));
    }

    public void validateBanner(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(autoActionsPageObject.messageBanner);
        String message = autoActionsPageObject.messageBanner.getText();
        if(message.equals("There is a problem in saving the auto action .Please try again.")){
            waitExecuter.sleep(2000);
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.closeNewAutoAction);
        }
    }

    public boolean validateAutoActionAdded(String policyName){
        waitExecuter.sleep(1000);
        int rowCount = autoActionsPageObject.firstColumnElementsAATable.size();
        for(int i=0 ; i< rowCount; i++){
            if(autoActionsPageObject.firstColumnElementsAATable.get(i).getText().equals(policyName)){
                return true;
            }
        }
        return false;
    }

    public boolean validateBannerForNoValue(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.messageBanner);
        String errorMessage = newAutoActionPolicyPageObject.messageBanner.getText();
        if(errorMessage.contains("\"value\" property is not set")){
            waitExecuter.sleep(2000);
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.closeNewAutoAction);
            return true;
        }
        return false;
    }

    public void closeDefaultRefineScope(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.iconX_RefineScope);
        newAutoActionPolicyPageObject.iconX_RefineScope.click();
    }

    public void clickOnRefineScope(){
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver,newAutoActionPolicyPageObject.refineScopeBtn);
    }

    public void selectRefineScope(String scope){
        waitExecuter.sleep(1000);
        int allScopeCount = newAutoActionPolicyPageObject.selectRefineScopeList.size();
        logger.info("Scope count: "+ allScopeCount);
        for(int i=0; i<allScopeCount-1 ; i++){
            if(newAutoActionPolicyPageObject.selectRefineScopeList.get(i).getText().equals(scope)){
                waitExecuter.sleep(1000);
                MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.selectRefineScopeList.get(i));
            }
        }
    }

    public boolean validateDefaultAllUserScopeChkBox(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.allUserChkBox);
        if(newAutoActionPolicyPageObject.allUserChkBox.isSelected()){
            return true;
        }
        return false;
    }

    public boolean validateDefaultOnlyScopeChkBox(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.onlyUserChkBox);
        if(newAutoActionPolicyPageObject.onlyUserChkBox.isSelected()){
            return true;
        }
        return false;
    }

    public boolean validateDefaultAlwaysScopeChkBox(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.alwaysChkBox);
        if(newAutoActionPolicyPageObject.alwaysChkBox.isSelected()){
            return true;
        }
        return false;
    }

    public void clickScopeChkBox(String chkBoxName){
        waitExecuter.sleep(2000);
        if(chkBoxName.equals("only")){
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.onlyUserChkBox);
        }else if(chkBoxName.equals("except")){
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.exceptUserChkBox);
        }else if(chkBoxName.equals("all")){
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.allUserChkBox);
        }else if(chkBoxName.equals("daily")){
            MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.dailyChkBox);
        }
        else{
            logger.info("No Check Box found with name: "+ chkBoxName);
        }
    }

    public void clickOnRunHeader(){
        if(autoActionsPageObject.headerRunColumn.isDisplayed()){
            MouseActions.clickOnElement(driver, autoActionsPageObject.headerRunColumn);
        }else{
            logger.info("AutoAction Table with header is not displayed.");
        }
    }

    /**
     * Method to get all policy name which are triggered auto actions alerts.
     */
    public void getTriggeredAAs(){
        List<WebElement> listRunCounts = autoActionsPageObject.listRunCount;
        Assert.assertFalse(listRunCounts.isEmpty(), "The run count is empty from AutoAction Table.");
        List<String> totalTriggeredPolicyNames = new ArrayList<>();
        int numOfRows = autoActionsPageObject.listRunCount.size();
        logger.info(" Total num of rows in AAs table: "+numOfRows);
        waitExecuter.sleep(2000);
        boolean triggerFlag = false;
        for(int i=0 ; i< numOfRows ; i++ ){
            waitExecuter.sleep(2000);
            String runCount = autoActionsPageObject.listRunCount.get(i).getText().trim();
            String Count = "((?<=[a-zA-Z])(?=[0-9]))|((?<=[0-9])(?=[a-zA-Z]))";
            String CountVal = runCount.split("/")[0].trim().split(Count)[0];
            logger.info("total count is " + CountVal);
            waitExecuter.sleep(2000);
            totalTriggeredPolicyNames.add(autoActionsPageObject.listPolicyNames.get(i).getText().trim());
            triggerFlag = true;
        }

        {
            if(triggerFlag){
                logger.info("Trigger Policy are: "+totalTriggeredPolicyNames);

                for(String triggerPolicy: totalTriggeredPolicyNames){
                    logger.info("Triggered Policy is: "+triggerPolicy);

                }
            }else {
                logger.info("No Triggered Policy found");
            }}
    }
}