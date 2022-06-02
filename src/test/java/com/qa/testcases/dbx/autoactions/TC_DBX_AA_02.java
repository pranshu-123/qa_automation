package com.qa.testcases.dbx.autoactions;

import java.util.logging.Logger;
import org.testng.annotations.Test;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.databricks.autoaction.DbxAutoAction;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;

@Marker.DbxAutoAction
public class TC_DBX_AA_02 extends BaseClass {
	private static final java.util.logging.Logger logger = Logger.getLogger(TC_DBX_AA_02.class.getName());

	@Test
	public void TC_DBX_AA_02_validateAutoActionTemplatePage() {
		test = extent.startTest("TC_DBX_AA_02_validateAutoActionTemplatePage", "Validate Auto Action Policy Template page");
		test.assignCategory("DBX Auto Action");
		Log.startTestCase("TC_DBX_AA_02_validateAutoActionTemplatePage");
		DbxAutoAction dbxAutoAction = new DbxAutoAction(driver);
		dbxAutoAction.navigateToAutoAction();
		dbxAutoAction.navigateToNewTemplate();
		logger.info("Navigated to Template Page");
		dbxAutoAction.validateNewTemplatePage();
		dbxAutoAction.close();
		logger.info("Auto Action new template page elements validated");
		test.log(LogStatus.PASS, "Verified Auto Action template Page Elements.");

	}
}
