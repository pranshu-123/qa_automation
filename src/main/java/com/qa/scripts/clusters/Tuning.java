package com.qa.scripts.clusters;

import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Tuning {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private TuningPageObject tuningPageObject;
    private static final Logger LOGGER = Logger.getLogger(Tuning.class.getName());
    private UserActions userActions = new UserActions(driver);

    public Tuning(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        tuningPageObject = new TuningPageObject(driver);
    }

    public void closeConfirmationMessageNotification() {
        if (tuningPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(tuningPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, tuningPageObject.confirmationMessageElementClose.get(0));
        }
    }

    public void clickOnRunButton() {
        try {
            MouseActions.clickOnElement(driver, tuningPageObject.runButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, tuningPageObject.runButton);
        }
    }

    public void clickOnModalRunButton() {
        MouseActions.clickOnElement(driver, tuningPageObject.modalRunButton);
    }

    public List<String> getClusterOptions(CommonPageObject commonPageObject) {
        List<String> list = new ArrayList<>();
        for (WebElement element : commonPageObject.clustersList) {
            list.add(element.getText());
        }
        return list;
    }

    public void closeNewReport(){
        try {
            MouseActions.clickOnElement(driver, tuningPageObject.closeNewReport);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, tuningPageObject.closeNewReport);
        }
    }

    public void clickOnScheduleButton() {
        try {
            MouseActions.clickOnElement(driver, tuningPageObject.scheduleButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, tuningPageObject.scheduleButton);
        }
    }

    public void createScheduleWithName(String name){
        waitExecuter.waitUntilElementPresent(tuningPageObject.scheduleName);
        userActions.performActionWithPolling(tuningPageObject.scheduleName, UserAction.SEND_KEYS, name);
    }

    public void createScheduleWithNameAndEmail(String name, String email){
        waitExecuter.waitUntilElementPresent(tuningPageObject.scheduleName);
        userActions.performActionWithPolling(tuningPageObject.scheduleName, UserAction.SEND_KEYS, name);
        userActions.performActionWithPolling(tuningPageObject.email, UserAction.SEND_KEYS, email);
        waitExecuter.waitUntilElementPresent(tuningPageObject.addEmail);
        MouseActions.clickOnElement(driver, tuningPageObject.addEmail);
    }

    public void createScheduleWithNameAndMultiEmail(String name, List<String> multiEmail){
        waitExecuter.waitUntilElementPresent(tuningPageObject.scheduleName);
        userActions.performActionWithPolling(tuningPageObject.scheduleName, UserAction.SEND_KEYS, name);
        for(String email: multiEmail){
            userActions.performActionWithPolling(tuningPageObject.email, UserAction.SEND_KEYS, email);
            waitExecuter.waitUntilElementPresent(tuningPageObject.addEmail);
            MouseActions.clickOnElement(driver, tuningPageObject.addEmail);
        }
    }

    public void createSchedule(Map<String, String> scheduleMap ){
        for(Map.Entry<String, String> e: scheduleMap.entrySet()){
            if(e.getKey().equalsIgnoreCase("NAME")) {
                tuningPageObject.scheduleName.sendKeys(e.getValue());
            }
        }
    }

    public void clickOnModalScheduleButton(){
        try {
            MouseActions.clickOnElement(driver, tuningPageObject.modalScheduleButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, tuningPageObject.modalScheduleButton);
        }
    }

    public void verifyScheduleSuccessMsg(String successMsg){
        waitExecuter.waitUntilElementPresent(tuningPageObject.scheduleSuccessMsg);
        Assert.assertEquals(tuningPageObject.scheduleSuccessMsg.getText(), successMsg, "The Schedule success " +
                "message mismatch");
    }

    public void verifyScheduleToRun(){
        List<String> expectedSchedule = new ArrayList<>(Arrays.asList(PageConstants.TuningScheduleRun.SCHEDULE_RUN));
        waitExecuter.waitUntilElementPresent(tuningPageObject.scheduleToRun);
        Select scheduleTorunDropDown = new Select(tuningPageObject.scheduleToRun);
        List <WebElement> elementScheduleTorunDropDown = scheduleTorunDropDown.getOptions();
        if(elementScheduleTorunDropDown.size() > 0 ){
            for(int i=0; i<elementScheduleTorunDropDown.size(); i++){
                WebElement e = elementScheduleTorunDropDown.get(i);
                LOGGER.info("Schedule date: "+ e.getText());
                Assert.assertEquals(e.getText().trim(), expectedSchedule.get(i), "Mismatch in schedule run date.");
            }
        }
    }
}