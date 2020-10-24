package com.qa.scripts;

import com.qa.pagefactory.CommonPageObject;
import com.qa.utils.MouseActions;
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
    if (commonPageObject.closeModalButton.size() > 0) {
      MouseActions.clickOnElement(driver, commonPageObject.closeModalButton.get(0));
    }
  }
}
