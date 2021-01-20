package com.qa.scripts;

import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;

/**
 * @author Ankur Jaiswal
 * This is common class used across all components
 */
public class CommonComponent {

  /**
   * This method with close the pop up modal if exist on web page
   * @param driver WebDriver instance
   */
  public static void closeModalIfExists(WebDriver driver) {
    CommonPageObject commonPageObject = new CommonPageObject(driver);
    UserActions actions = new UserActions(driver);
    if (commonPageObject.closeModalButton.size() > 0) {
      actions.performActionWithPolling(commonPageObject.closeModalButton.get(0), UserAction.CLICK);
    }
    else if(commonPageObject.closeAppModalButton.size() > 0) {
      actions.performActionWithPolling(commonPageObject.closeAppModalButton.get(0), UserAction.CLICK);
    }
  }
}
