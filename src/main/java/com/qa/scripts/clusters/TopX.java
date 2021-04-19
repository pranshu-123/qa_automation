package com.qa.scripts.clusters;

import com.qa.enums.UserAction;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import java.util.List;
import java.util.logging.Logger;

import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author Ankur Jaiswal
 */
public class TopX {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private TopXPageObject topXPageObject;
    private UserActions actions;
    private static final Logger LOGGER = Logger.getLogger(TopX.class.getName());

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

    /**
     * Select multi c
     * TODO - Need to consolidate this. Multiple report are having same implementation
     * @param clusterId
     */
    public void selectCluster(String clusterId) {
        waitExecuter.waitUntilElementClickable(topXPageObject.clusterDropdown);
        actions.performActionWithPolling(topXPageObject.clusterDropdown, UserAction.CLICK);
        actions.performActionWithPolling(topXPageObject.clusterSearchbox, UserAction.CLICK);
        actions.performActionWithPolling(topXPageObject.clusterSearchbox, UserAction.SEND_KEYS, clusterId);
        actions.performActionWithPolling(topXPageObject.select1stClusterOption, UserAction.CLICK);
    }

  public WebElement getConfirmationMessage() {
    return topXPageObject.confirmationMessageElement;
  }

    /**
     * Click on user Filter on TopX page
     */
    public void clickOnUserFilter() {
        waitExecuter.waitUntilElementClickable(topXPageObject.usersSelectTextField);
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

    /**
     * Method to click the report names listed in the Report Archive Page with report name
     */
    public void clickOnReportName(ReportsArchiveScheduledPageObject reportPageObj, String name) {
        List<WebElement> reportNameList = reportPageObj.reportNames;
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
        for (int i = 0; i < reportNameList.size(); i++) {
            String reportName = reportNameList.get(i).getText().trim();
            System.out.println("reportName: " + reportName);
            if (reportName.equals(name)) {
                LOGGER.info("The report name is " + reportName);
                int index = i + 1;
                String iconXpath = "//table/tbody/tr[" + index + "]/td[4]/div/span/span[contains(@class,'icon-calendar')]";
                //table/tbody/tr[1]/td[4]/div/span/span[contains(@class,'icon-expand')]
                //System.out.println("iconXpath: "+iconXpath);
                WebElement iconElement = driver.findElement(By.xpath(iconXpath));
                waitExecuter.waitUntilElementPresent(iconElement);
                iconElement.click();
                waitExecuter.waitUntilPageFullyLoaded();
                break;
            }
        }
    }

    /**
     * Close topX modal
     */
    public void closeModalIfExists() {
        if (topXPageObject.closeModalButton.size() > 0) {
            actions.performActionWithPolling(topXPageObject.closeModalButton.get(0),
                UserAction.CLICK);
        }
    }

    /**
     * Get the latest executed report
     */
    public boolean clickOnLatestReportIfExist() {
        if (topXPageObject.lastExecutionReportButton.size() > 0) {
            actions.performActionWithPolling(topXPageObject.lastExecutionReportButton.get(0)
                , UserAction.CLICK);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if report successfully started
     * @return
     */
    public boolean checkIfReportSuccessfullyStarted() {
        try {
            if (getConfirmationMessageContent().equalsIgnoreCase("REPORT HAS BEEN STARTED. BUT NOT ABLE TO " +
                "ACCESS THE STATUS.")) {
                return false;
            }
        } catch (Exception exception) {
        }
        return true;
    }

    /**
     * Click on the latest report
     */
    public boolean validateLatestReport(String key, String value) {
        if (clickOnLatestReportIfExist()) {
            for (WebElement row : getInputParamsRowList()) {
                if (row.findElement(By.xpath("td[1]")).getText().equalsIgnoreCase(key)) {
                    Assert.assertEquals(row.findElement(By.xpath("td[2]")).getText(), value,
                        "Incorrect value is displayed");
                    return true;
                }
            }
        } else {
            Assert.fail("Unable to click on latest report");
        }
        return false;
    }
}
