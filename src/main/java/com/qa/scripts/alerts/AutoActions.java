package com.qa.scripts.alerts;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.alerts.AutoActionsPageObject;
import com.qa.pagefactory.alerts.NewAutoActionPolicyPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Birender Kumar
 */
public class AutoActions {

    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private AutoActionsPageObject autoActionsPageObject;
    private NewAutoActionPolicyPageObject newAutoActionPolicyPageObject;

    Logger logger = LoggerFactory.getLogger(AutoActions.class);

    /**
     * Constructer to initialize wait, driver and necessary objects
     * @param driver
     *            - WebDriver instance
     */
    public AutoActions(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        autoActionsPageObject = new AutoActionsPageObject(driver);
        newAutoActionPolicyPageObject = new NewAutoActionPolicyPageObject(driver);
    }

    public void setupForAutoActionsPage(){
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.alerts);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelComponentPageObject.alerts);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.alerts);
    }


    public String getAutoActionsHeader(){
        waitExecuter.sleep(1000);
        logger.info("Auto Actions component Header: "+ autoActionsPageObject.autoActionComponentHeader.getText());
        return autoActionsPageObject.autoActionComponentHeader.getText();
    }

    public void clickOnNewAutoActionBtn(){
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, autoActionsPageObject.newAutoActionBtn);
    }

    public boolean validateNewAutoActionPolicyPageDisplayed(){
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilUrlContains("autoaction/add");
        waitExecuter.waitUntilElementPresent(newAutoActionPolicyPageObject.newAutoActionPolicyHeader);
        if(newAutoActionPolicyPageObject.newAutoActionPolicyHeader.getText().equals("New Auto Action Policy")){
            return true;
        }
        return false;
    }

    public void clickOnTriggerConditionBtn(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.triggerConditionsBtn);
    }

    public void selectTriggerCondition(String triggerCondition){
        waitExecuter.sleep(1000);
        int allTriggerCondition = newAutoActionPolicyPageObject.selectTriggerConditions.size();
        for(int i=0; i< allTriggerCondition ; i++){
            if(newAutoActionPolicyPageObject.selectTriggerConditions.get(i).getText().equals(triggerCondition)){
                MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.selectTriggerConditions.get(i));
            }
        }
    }

    public void clickOnSaveBtn(){
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, newAutoActionPolicyPageObject.saveBtn);
    }

    public void enterNewAutoActionPolicyDetails(String policyName, String triggerConditon, String triggerValue){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
        clickOnTriggerConditionBtn();
        selectTriggerCondition(triggerConditon);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.triggerConditionValue.sendKeys(triggerValue);
    }

    public void enterNewAutoActionPolicyDetails(String policyName, String triggerConditon, String triggerValue, String policyDescrption){
        waitExecuter.sleep(1000);
        newAutoActionPolicyPageObject.policyName.sendKeys(policyName);
        clickOnTriggerConditionBtn();
        selectTriggerCondition(triggerConditon);
        waitExecuter.sleep(2000);
        newAutoActionPolicyPageObject.triggerConditionValue.sendKeys(triggerValue);
        newAutoActionPolicyPageObject.policyDescription.sendKeys(policyDescrption);
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

}
