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
        //MouseActions.clickOnElement(driver, topXPageObject.modalRunButton);
        MouseActions.clickOnElement(driver, topXPageObject.runButton);
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

    /**
     * Click on user Filter on TopX page
     */
    public void clickOnUserFilter() {
        actions.performActionWithPolling(topXPageObject.usersSelectTextField, UserAction.CLICK);
    }

    /**
     * Click on Real User Filter on TopX page
     */
    public void clickOnRealUserFilter() {
        actions.performActionWithPolling(topXPageObject.realUsersSelectTextField, UserAction.CLICK);
    }

    /**
     * Click on Queue Filter on TopX page
     */
    public void clickOnQueueFilter() {
        actions.performActionWithPolling(topXPageObject.queuesSelectTextField, UserAction.CLICK);
    }

    /**
     * Get users list from users filter
     */
    public List<WebElement> getUsersList() {
        return topXPageObject.usersList;
    }

    /**
     * Get users list from users filter
     */
    public List<WebElement> getFilterDropDowns() {
        return topXPageObject.topXFilterDropDown;
    }

    /**
     * Get input parameters row list
     */
    public List<WebElement> getInputParamsRowList() {
        return topXPageObject.inputParamsRowList;
    }

    /**
     * Returns the list of checkbox present on tags page
     */
    public List<WebElement> getTagsCheckbox() {
        return topXPageObject.tagsCheckbox;
    }

    /**
     * Returns the last element of input textbox of tags section
     */
    public WebElement getLastInputTextboxField() {
        return topXPageObject.tagsLastInputTextboxField;
    }

    /**
     * Click on tags checkbox if not selected
     * @param checkbox
     */
    public void selectTagsCheckbox(WebElement checkbox) {
        List<WebElement> checkboxFooters = checkbox.findElements(topXPageObject.tagsFooter);
        if (checkboxFooters.size() == 0) {
            actions.performActionWithPolling(checkbox, UserAction.CLICK);
        }
    }

    /**
     * This method clear the filter present on impala page
     */
    public void clearFilter() {
        final List<WebElement> filterItems = topXPageObject.filterRemoveElements;
        while (filterItems.size() != 0) {
            JavaScriptExecuter.clickOnElement(driver, filterItems.get(0));
            waitExecuter.waitUntilPageFullyLoaded();
        }
    }
}
