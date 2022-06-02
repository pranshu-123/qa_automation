package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_01 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_01.class.getName());

    @Test
       public void TC_DBX_AA_01_validateAutoActionLandingPage() {
        test = extent.startTest("TC_DBX_AA_01_validateAutoActionLandingPage", "Validate Auto Action Landing Page");
        test.assignCategory("DBX Auto Action");
    	Log.startTestCase("TC_DBX_AA_01_validateAutoActionLandingPage");
        DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
        dbxAutoAction.navigateToAutoAction();
        dbxAutoAction.validateAutoActionsPageElements();
        logger.info("Auto Action page elements validated");
        test.log(LogStatus.PASS, "Verified Auto Action Page Elements.");
       
    }
}
