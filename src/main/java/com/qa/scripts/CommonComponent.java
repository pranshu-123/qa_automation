package com.qa.scripts;

import com.qa.pagefactory.CommonPageObject;
import com.qa.utils.MouseActions;
import org.openqa.selenium.WebDriver;

public class CommonComponent {

  public static void closeModalIfExists(WebDriver driver) {
    CommonPageObject commonPageObject = new CommonPageObject(driver);
    if (commonPageObject.closeModalButton.size() > 0) {
      MouseActions.clickOnElement(driver, commonPageObject.closeModalButton.get(0));
    }
  }
}
