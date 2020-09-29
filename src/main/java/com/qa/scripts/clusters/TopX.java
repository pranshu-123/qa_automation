package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import java.util.List;
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
        topXPageObject.topXNumber.sendKeys(number);
    }

    public TopX(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        topXPageObject = new TopXPageObject(driver);
    }
    
    public void selectUserInScheduleReport() {
	  List<WebElement> listOfUsers = topXPageObject.dropdownOptions;
	  topXPageObject.usersDropdown.click();
	  waitExecuter.sleep(1000);
	  listOfUsers.get(0).click();
  }
  
  public void selectRealUser() {
	  List<WebElement> listOfUsers = topXPageObject.dropdownOptions;
	  topXPageObject.readUsersDropdown.click();
	  waitExecuter.sleep(1000);
	  listOfUsers.get(0).click();
  }
  
  public void selectQueue() {
	  List<WebElement> listOfQueue = topXPageObject.dropdownOptions;
	  topXPageObject.queuesDropdown.click();
	  waitExecuter.sleep(1000);
	  listOfQueue.get(0).click();
  }
  
  public void assignEmail(String emaiId) {
	  topXPageObject.emailNotification.click();
	  waitExecuter.sleep(1000);
	  topXPageObject.emailNotification.sendKeys(emaiId);
  }
  
  public void assignScheduleName(String scheduleName) {
	  topXPageObject.scheduleNameTextbox.click();
	  waitExecuter.sleep(1000);
	  topXPageObject.scheduleNameTextbox.sendKeys(scheduleName);
  }

  public List<WebElement> getClustersList() {
    return topXPageObject.clusterList;
  }

  public WebElement getConfirmationMessage() {
    return topXPageObject.confirmationMessageElement;
  }
}
