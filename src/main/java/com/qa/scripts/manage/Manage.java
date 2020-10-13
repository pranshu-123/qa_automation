package com.qa.scripts.manage;

import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Birender Kumar
 */
public class Manage {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private ManagePageObject managePageObject;

    public Manage(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        managePageObject = new ManagePageObject(driver);
    }

    public String validateDaemonHeader(){
        System.out.println("Daemons Header found:"+managePageObject.daemonsHeader.getText());
        return managePageObject.daemonsHeader.getText();
    }

    public Boolean validateAllTabsPresent(){
        System.out.println("Number of tabs on manage: "+managePageObject.allManageTabList.size());
        List<String> allTabsOnManagePage = new ArrayList<String>();
        for(int i=0; i<managePageObject.allManageTabList.size(); i++){
            allTabsOnManagePage.add(managePageObject.allManageTabList.get(i).getText());
        }
        System.out.println("Actual tabs: "+ allTabsOnManagePage );
        String[] expectedTabsOnManagePage = {"Daemons","Stats", "Run Diagnostics", "Monitoring"};

        Boolean boolAllTabs=true;

        for(String strTab: expectedTabsOnManagePage){
           if(!allTabsOnManagePage.contains(strTab)){
               boolAllTabs= false;
           }
        }

        return boolAllTabs;
    }

}
