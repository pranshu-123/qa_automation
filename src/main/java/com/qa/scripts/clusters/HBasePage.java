package com.qa.scripts.clusters;

import com.qa.pagefactory.clusters.HBasePageObject;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;

public class HBasePage {
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private HBasePageObject hBasePageObject;
    private UserActions actions;

    public HBasePage(WebDriver driver){
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        hBasePageObject = new HBasePageObject(driver);
        actions = new UserActions(driver);
    }

    public String getHBaseHeader(){
        waitExecuter.waitUntilElementPresent(hBasePageObject.hbaseHeader);
        return hBasePageObject.hbaseHeader.getText().trim();
    }


}
