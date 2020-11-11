package com.qa.scripts.clusters;

import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import java.util.List;

import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ankur Jaiswal
 */
public class TopX {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private TopXPageObject topXPageObject;
    private UserActions actions;

    public void closeConfirmationMessageNotification() {
        if (topXPageObject.confirmationMessageElementClose.size() > 0) {
            waitExecuter.waitUntilElementClickable(topXPageObject.confirmationMessageElementClose.get(0));
            JavaScriptExecuter.clickOnElement(driver, topXPageObject.confirmationMessageElementClose.get(0));
        }
    }

    public void clickOnRunButton() {
        try {
            MouseActions.clickOnElement(driver, topXPageObject.runButton);
        } catch (TimeoutException te) {
            MouseActions.clickOnElement(driver, topXPageObject.runNowButton);
        }
    }

    public void clickOnModalRunButton() {
        MouseActions.clickOnElement(driver, topXPageObject.modalRunButton);
    }

    public String getConfirmationMessageContent() {
        return topXPageObject.confirmationMessageElement.getText();
    }

    public void setTopXNumber(String number) {
        waitExecuter.waitUntilElementPresent(topXPageObject.topXNumber);
        waitExecuter.sleep(1000);
        topXPageObject.topXNumber.clear();
        JavaScriptExecuter.clearTextField(driver, topXPageObject.topXNumber);
        actions.performActionWithPolling(topXPageObject.topXNumber,
            UserAction.SEND_KEYS, number);
    }

    public TopX(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        topXPageObject = new TopXPageObject(driver);
        actions = new UserActions(driver);
    }
    
    public void selectUserInScheduleReport() {
	  List<WebElement> listOfUsers = topXPageObject.dropdownOptions;
	  actions.performActionWithPolling(topXPageObject.usersDropdown, UserAction.CLICK);
	  actions.performActionWithPolling(listOfUsers.get(0), UserAction.CLICK);
  }
  
  public void selectRealUser() {
	  List<WebElement> listOfUsers = topXPageObject.dropdownOptions;
      actions.performActionWithPolling(topXPageObject.readUsersDropdown, UserAction.CLICK);
      actions.performActionWithPolling(listOfUsers.get(0), UserAction.CLICK);
  }
  
  public void selectQueue() {
	  List<WebElement> listOfQueue = topXPageObject.dropdownOptions;
      actions.performActionWithPolling(topXPageObject.queuesDropdown, UserAction.CLICK);
      actions.performActionWithPolling(listOfQueue.get(0), UserAction.CLICK);
  }
  
  public void assignEmail(String email) {
	  actions.performActionWithPolling(topXPageObject.emailNotification, UserAction.CLICK);
	  actions.performActionWithPolling(topXPageObject.emailNotification, UserAction.SEND_KEYS,
          email);
  }
  
  public void assignScheduleName(String scheduleName) {
	  actions.performActionWithPolling(topXPageObject.scheduleNameTextbox, UserAction.CLICK);
	  actions.performActionWithPolling(topXPageObject.scheduleNameTextbox,
          UserAction.SEND_KEYS, scheduleName);
  }

  public List<WebElement> getClustersList() {
    return topXPageObject.clusterList;
  }

  public WebElement getConfirmationMessage() {
    return topXPageObject.confirmationMessageElement;
  }
}
