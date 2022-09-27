package com.qa.scripts.emr.Cost;
import com.qa.scripts.DatePicker;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.qa.enums.UserAction;
import com.qa.utils.actions.UserActions;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import com.qa.pagefactory.emr.Cost.ChargebackPageObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Chargeback {
    private static final Logger LOGGER = Logger.getLogger(Chargeback.class.getName());

    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final ChargebackPageObject chargebackPageObject;
    private final DatePicker datePicker;
    private final UserActions userActions;
    public Chargeback(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        chargebackPageObject = new ChargebackPageObject(driver);
        datePicker = new DatePicker(driver);
        userActions=new UserActions(driver);
    }

    public void NavigateToCostTab(String tab) {
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(chargebackPageObject.costChargeBackTab);
        try {
            if(tab.equalsIgnoreCase("Trends")) {
                chargebackPageObject.costTrendsTab.click();
            }
            else if(tab.equalsIgnoreCase("Chargeback")) {
                chargebackPageObject.costChargeBackTab.click();
            }
            else {
                chargebackPageObject.costBudgetTab.click();
            }
        }
        catch(ElementClickInterceptedException e) {
            e.printStackTrace();
        }
    }
    public void ValidatePieChartGraph(String[] headers) {
        waitExecuter.sleep(4500);
        List<String> list = chargebackPageObject.graphsHeader.stream()
                .map(graph -> graph.getText()).collect(Collectors.toList());
        for(String s : headers) {
            list.contains(s);
        }
        chargebackPageObject.pieGraph.stream()
                .forEach(graph -> graph.isDisplayed());
        LOGGER.info("Pie Chart is displyed");
    }

    public void ValidateResultSetIsDisplayedWithValues() {
        Assert.assertTrue(chargebackPageObject.cost.isDisplayed(),"Cost graph is not displayed");
        Assert.assertTrue(chargebackPageObject.ec2.isDisplayed(),"Ec2 graph is not displayed");
        Assert.assertTrue(chargebackPageObject.emr.isDisplayed(),"EMR graph is not displayed");
        Assert.assertTrue(chargebackPageObject.ebs.isDisplayed(),"EBS graph is not displayed");
        Assert.assertTrue(chargebackPageObject.clustercount.isDisplayed(),"Cluster Count graph is not displayed");
        LOGGER.info("Result set is populated with 5 different required graphs");
    }

    public void ClickOnGivenDateRange(String date) {
        String e1="//div//button[contains (text(),'%s')]";
     WebElement e2= driver.findElement(By.xpath(String.format(e1,date)));
        waitExecuter.waitUntilElementClickable(e2);
        userActions.performActionWithPolling(e2, UserAction.CLICK);
        waitExecuter.sleep(1000);
    }

    public void DropdownValidation(String[] values){
        chargebackPageObject.ScopeDropdownButton.click();
        waitExecuter.sleep(1000);
        List<String> scopevalues=chargebackPageObject.ScopeDropdownvalues.stream()
                .map(graph -> graph.getText()).collect(Collectors.toList());
        for(String s : values) {
            Assert.assertTrue(scopevalues.contains(s),"Dropdown values are not correct");
            ;
        }

    }

    public void SelectTags() {
        waitExecuter.waitUntilElementPresent(chargebackPageObject.selectTags);
      chargebackPageObject.selectTags.click();
        waitExecuter.waitUntilElementClickable(chargebackPageObject.ScopeDropdownButton);
    }

    public void SelectGlobal() {
        waitExecuter.waitUntilElementPresent(chargebackPageObject.selectGlobal);
        chargebackPageObject.selectGlobal.click();
        waitExecuter.waitUntilElementClickable(chargebackPageObject.ScopeDropdownButton);
    }

    public void SelectCBbyTag(String[] values) {
        waitExecuter.waitUntilElementClickable(chargebackPageObject.selectCBbyTagKey);
        chargebackPageObject.selectCBbyTagKey.click();
        waitExecuter.sleep(1000);
        List<String> scopevalues=chargebackPageObject.CBbyTagKeyDdn.stream()
                .map(graph -> graph.getText()).collect(Collectors.toList());
        for(String s : values) {
            Assert.assertTrue(scopevalues.contains(s),"Dropdown values are not correct");
            ;
        }
    }

    public void SelectTenureinCb_byTag() {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.selectCBbyTenure);
        chargebackPageObject.selectCBbyTenure.click();
    }

    public void SelectPurposeinCb_byTag() {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.selectCBbyPurpose);
        chargebackPageObject.selectCBbyPurpose.click();
    }

    public void SelectNameinCb_byTag() {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.selectCBbyName);
        chargebackPageObject.selectCBbyName.click();
    }
    public void TagsMultiSelect(String[] values){
        chargebackPageObject.MultiselectTags.click();
        List<String> scopevalues=chargebackPageObject.MultiselectTagsValues.stream()
                .map(graph -> graph.getText()).collect(Collectors.toList());

     scopevalues.replaceAll(e -> e.replaceAll("[^a-zA-Z]",""));
        for(String s : values) {
            Assert.assertTrue(scopevalues.contains(s),"Dropdown values are not correct");
            ;
        };

    }

    public void validateViewTrends(){
        chargebackPageObject.viewTrends.click();
        Assert.assertTrue( chargebackPageObject.viewTrendsCost.isDisplayed());
        Assert.assertTrue( chargebackPageObject.viewTrendsCount.isDisplayed());
    }

    public void validateViewClusters(){
        chargebackPageObject.viewClusters.click();
        Assert.assertTrue( chargebackPageObject.viewClusterHeader.isDisplayed());
        Assert.assertTrue( chargebackPageObject.viewClusterValidation.isDisplayed());

    }

    public void NavigateCreateBudget(){
        chargebackPageObject.ActionsButton.click();
        waitExecuter.waitUntilElementClickable(chargebackPageObject.CreateBudget);
        chargebackPageObject.CreateBudget.click();
    }

    public void ValidateCreateBudget(String[] values) {
        Assert.assertTrue(chargebackPageObject.CreateBudgetHeader1.isDisplayed());
        Assert.assertTrue(chargebackPageObject.CreateBudgetHeader2.isDisplayed());
        List<String> Titlevalues = chargebackPageObject.TitleList.stream()
                .map(graph -> graph.getText()).collect(Collectors.toList());

        for (String s : values) {
            Assert.assertTrue(Titlevalues.contains(s), "Title Values are nto correct");
            ;
        }
    }
    public void TenureSelect() throws InterruptedException {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.MultiselectTagTenure);
        Thread.sleep(2000);
        chargebackPageObject.MultiselectTagTenure.click();

        };

    public void PurposeSelect() throws InterruptedException {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.MultiselectTagPurpose);
        Thread.sleep(2000);
        chargebackPageObject.MultiselectTagPurpose.click();

    };

    public void NameSelect() throws InterruptedException {
        JavaScriptExecuter.scrollOnElement(driver, chargebackPageObject.MultiselectTagName);
        Thread.sleep(2000);
        chargebackPageObject.MultiselectTagName.click();

    };


}
