package com.qa.scripts.migration;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
public class ServicesAndVersionsCompatibility {

    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject;
    private SubTopPanelModulePageObject subTopPanelModulePageObject;
    private static final Logger LOGGER = Logger.getLogger(ServicesAndVersionsCompatibility.class.getName());

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public ServicesAndVersionsCompatibility(WebDriver driver){
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        servicesAndVersionsCompatibilityPageObject = new ServicesAndVersionsCompatibilityPageObject(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
    }

    public void setupServicesAndVersionsCompatibilityPage(){
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.migrationTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.migrationTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.migrationTab);
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.servicesVersionMigrationTab);

    }

    public void clickOnServicesAndVersionMigrationTab(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.servicesVersionMigrationTab);
    }

    public void closeMessageBanner(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.closeMsgBanner);
    }

    public void clickOnRunButton(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runBtn);
    }

    public void clickOnRunNewButton(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runNewBtn);
    }

    public void selectCloudProduct(String cloudProductName){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.cloudProductDropDown);
        servicesAndVersionsCompatibilityPageObject.cloudProductSearchBox.sendKeys(cloudProductName);
        servicesAndVersionsCompatibilityPageObject.cloudProductSearchFirstField.click();
    }

    public boolean checkCloudProductsAvailable(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.cloudProductDropDown);
        if(servicesAndVersionsCompatibilityPageObject.noCloudProductResult.isDisplayed()){
            return false;
        }
        return true;
    }

    public void clickOnRunModalButton(){
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runModalBtn);
    }

}
